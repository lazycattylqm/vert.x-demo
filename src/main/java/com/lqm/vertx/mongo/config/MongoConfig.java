package com.lqm.vertx.mongo.config;

import com.google.common.io.Resources;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

@Data
@NoArgsConstructor
public class MongoConfig {
    private String host;
    private int port;
    private String username;
    private String password;
    private String db;

    private static MongoClient client;

    static {
        try {
            MongoConfig init = init();
            client = init.toMongoClient(Vertx.vertx());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static MongoClient getClient() {
        return client;
    }

    public static MongoConfig init() throws URISyntaxException {
        URL mongoResource = Resources.getResource("mongo.yaml");
        File file = new File(mongoResource.toURI().getPath());
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return new Yaml().loadAs(fileInputStream, MongoConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new MongoConfig();
    }

    private JsonObject mapToJsonConfig() {
        Map<String, Object> configMap =
                Map.of("db_name", db, "host", host, "port", port, "username", username, "password", password,
                        "useObjectId", true);
        JsonObject entries = new JsonObject(configMap);
        return entries;
    }

    private MongoClient toMongoClient(Vertx vertx) {
        return MongoClient.createShared(vertx, mapToJsonConfig());
    }
}
