package com.lqm.vertx.mongo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lqm.vertx.mongo.config.MongoConfig;
import com.lqm.vertx.mongo.model.MongoModel;
import de.undercouch.bson4jackson.BsonFactory;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
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
                        return json.mapTo(MongoModel.class);

                    }).toList();
                    ctx.json(mongoModels);
                } else {
                    res.cause().printStackTrace();
                    ctx.response().end("failed");
                }
            });
        });
        return router;
    }
}
