package com.lqm.vertx.common;

import io.vertx.ext.web.RoutingContext;

public class RequestBodyParse<T> {
    public static <T> T parse(RoutingContext context, Class<T> clazz) {
        return context.getBodyAsJson().mapTo(clazz);
    }
}
