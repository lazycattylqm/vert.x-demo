package com.lqm.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import lombok.Data;

public class MainVertical extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        Router router = Router.router(vertx);
        router.route().handler(context -> {
            context.json(new TempResponse());
        });
        vertx.createHttpServer().requestHandler(router).listen(8888).onSuccess(server -> {
            System.out.println("Http server started on port " + server.actualPort());
        });
    }
}

@Data
class TempResponse {
    private String name;
    private String address;

    public TempResponse() {
        name = "lqm";
        address = "beijing";
    }

}
