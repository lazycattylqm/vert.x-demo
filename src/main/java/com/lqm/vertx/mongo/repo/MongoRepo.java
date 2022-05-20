package com.lqm.vertx.mongo.repo;

import com.google.errorprone.annotations.Var;
import com.lqm.vertx.mongo.common.MongoMapper;
import com.lqm.vertx.mongo.config.MongoConfig;
import com.lqm.vertx.mongo.model.MongoModel;
import com.lqm.vertx.mongo.model.MongoModelDo;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClientDeleteResult;
import io.vertx.ext.mongo.MongoClientUpdateResult;

public class MongoRepo {
    public void insertNewOne(MongoModel model, Handler<AsyncResult<String>> handler) {
        // insert new one
        MongoModelDo mongoModelDo = MongoMapper.INSTANCE.toMongoModelDo(model);
        MongoConfig.getClient().save("liqiming", mongoModelDo.toJson(), handler);
    }

    public void updateOne(MongoModel model, Handler<AsyncResult<MongoClientUpdateResult>> handler) {
        // update one
        MongoModelDo mongoModelDo = MongoMapper.INSTANCE.toMongoModelDo(model);
        JsonObject queryJson = mongoModelDo.toQueryJson();
        JsonObject updateJson = mongoModelDo.toUpdateJson();
        MongoConfig.getClient().updateCollection("liqiming", queryJson, updateJson, handler);
    }

    public void deleteOnes(MongoModel model, Handler<AsyncResult<MongoClientDeleteResult>> handler) {
        MongoModelDo mongoModelDo = MongoMapper.INSTANCE.toMongoModelDo(model);
        JsonObject queryJson = mongoModelDo.toQueryJson();
        MongoConfig.getClient().removeDocuments("liqiming", queryJson, handler);
    }
}
