package com.lqm.vertx.base;

import com.lqm.vertx.model.vo.TempResponse;
import io.vertx.ext.web.Router;

public class BaseRouterController {
    public static void registerRouter(Router router) {
        router.route("/temp/reg/").handler(context->{
           context.response().end("reg");
        });

        router.route("/").handler(context -> context.json(new TempResponse()));
    }
}
