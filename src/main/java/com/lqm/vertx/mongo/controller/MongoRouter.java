package com.lqm.vertx.mongo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lqm.vertx.mongo.common.MongoMapper;
import com.lqm.vertx.mongo.config.MongoConfig;
import com.lqm.vertx.mongo.model.MongoModel;
import com.lqm.vertx.mongo.model.MongoModelDo;
import com.lqm.vertx.mongo.repo.MongoRepo;
import de.undercouch.bson4jackson.BsonFactory;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MongoRouter {
    public static Router router(Vertx vertx) {
        Router router = Router.router(vertx);
        ObjectMapper objectMapper = new ObjectMapper(new BsonFactory());
        router.get("/query/mongo/").handler(ctx -> {
            MongoClient client = MongoConfig.getClient();
            client.find("liqiming", new JsonObject(), res -> {
                if (res.succeeded()) {
                    List<MongoModel> mongoModels = res.result().stream().map(json -> {
                        log.info("json: {}", json.encodePrettily());
                        return json.mapTo(MongoModelDo.class);

                    }).map(MongoMapper.INSTANCE::toMongoModel).toList();
                    ctx.json(mongoModels);
                } else {
                    res.cause().printStackTrace();
                    ctx.response().end("failed");
                }
            });
        });

        router.put("/insert/mongo/").handler(BodyHandler.create()).handler(ctx -> {
            MongoModel mongoModel = ctx.getBodyAsJson().mapTo(MongoModel.class);
            log.info("mongoModel: {}", mongoModel);
            new MongoRepo().insertNewOne(mongoModel, res->{
                if (res.succeeded()) {
                    ctx.response().end("success");
                } else {
                    ctx.response().end("failed");
                }

            });
        });

        router.post("/update/mongo/").handler(BodyHandler.create()).handler(ctx -> {
            MongoModel mongoModel = ctx.getBodyAsJson().mapTo(MongoModel.class);
            log.info("mongoModel: {}", mongoModel);
            new MongoRepo().updateOne(mongoModel, res->{
                if (res.succeeded()) {
                    ctx.response().end("success");
                } else {
                    ctx.response().end("failed");
                }

            });

        });
        return router;
    }
}
