package com.lqm.vertx.mongo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@Data
public class MongoModel {

    @BsonId
    @JsonIgnore
    @JsonAlias("_id")
    private ObjectId _id;

    private int age;

    private String name;

    private String gender;

    public static MongoModel fromJson(JsonObject jsonObject, ObjectMapper mapper) throws JsonProcessingException {

        return mapper.readValue(jsonObject.encodePrettily(), MongoModel.class);
    }
}
