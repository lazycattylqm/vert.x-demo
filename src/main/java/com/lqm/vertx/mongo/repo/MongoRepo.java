package com.lqm.vertx.mongo.repo;

import com.lqm.vertx.mongo.config.MongoConfig;
import com.lqm.vertx.mongo.model.MongoModel;
import com.mongodb.lang.Nullable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.mongo.MongoClient;

public class MongoRepo {
    public void insertNewOne(MongoModel model, Handler<AsyncResult<String>> handler) {
        // insert new one
        MongoConfig.getClient().save("liqiming", model.toJson(), handler);
    }
}
