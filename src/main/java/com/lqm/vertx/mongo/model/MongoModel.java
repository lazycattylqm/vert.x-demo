package com.lqm.vertx.mongo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
public class MongoModel {

    @JsonAlias("_id")
    private String id;

    private int age;

    private String name;

    private String gender;

    public static MongoModel fromJson(JsonObject jsonObject, ObjectMapper mapper) throws JsonProcessingException {

        return mapper.readValue(jsonObject.encodePrettily(), MongoModel.class);
    }
}
