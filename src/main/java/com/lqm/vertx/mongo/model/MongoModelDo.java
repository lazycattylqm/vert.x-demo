package com.lqm.vertx.mongo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class MongoModelDo {
    @JsonAlias({"_id"})
    private String id;

    private int age;

    private String name;

    private String gender;


}
