package com.lqm.vertx.mongo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
public class MongoModelDo {
    @JsonAlias({"_id"})
    private String id;

    private int age;

    private String name;

    private String gender;


    public JsonObject toJson() {
        return JsonObject.mapFrom(this);
    }

    public JsonObject toQueryJson() {
        return new JsonObject().put("_id", id);
    }

    public JsonObject toUpdateJson() {
        JsonObject entries = new JsonObject();
        if (age > 0) {
            entries.put("age", age);
        }
        if (name != null && !name.isEmpty()) {
            entries.put("name", name);
        }
        if (gender != null && !gender.isEmpty()) {
            entries.put("gender", gender);
        }

        return new JsonObject().put("$set", entries);
    }

}
