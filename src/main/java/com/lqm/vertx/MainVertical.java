package com.lqm.vertx;

import com.lqm.vertx.model.vo.TempResponse;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.LoggerHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainVertical extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        Router router = Router.router(vertx);
        router.route("/").handler(context -> context.json(new TempResponse()));
        router.route("/get").handler(context -> context.response().end("get"));
        router.route(HttpMethod.POST, "/post").handler(LoggerHandler.create()).handler(BodyHandler.create()).handler(context -> {
            log.info("path");
            JsonObject bodyAsJson = context.getBodyAsJson();
            TempResponse tempResponse = bodyAsJson.mapTo(TempResponse.class);
            context.json(tempResponse);
        });
        vertx.createHttpServer().requestHandler(router).listen(8888).onSuccess(server -> {
            System.out.println("Http server started on port " + server.actualPort());
        });
    }
}

