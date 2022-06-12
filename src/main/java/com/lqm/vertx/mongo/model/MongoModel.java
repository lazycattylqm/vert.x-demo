package com.lqm.vertx.mongo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
public class MongoModel {

    private String id;

    private int age;

    private String name;

    private String gender;


    public JsonObject toJson() {
        return JsonObject.mapFrom(this);
    }

}
