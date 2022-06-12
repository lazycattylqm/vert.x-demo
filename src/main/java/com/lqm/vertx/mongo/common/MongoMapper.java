package com.lqm.vertx.mongo.common;

import com.lqm.vertx.mongo.model.MongoModel;
import com.lqm.vertx.mongo.model.MongoModelDo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MongoMapper {
    MongoMapper INSTANCE = Mappers.getMapper(MongoMapper.class);

    //from MongoModel to mongo MongoModelDo
    MongoModelDo toMongoModelDo(MongoModel mongoModel);

    // from MongoModelDo to MongoModel
    MongoModel toMongoModel(MongoModelDo mongoModelDo);

}
