package com.lqm.vertx.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MySqlConfig {
    private String host;
    private int port;
    private String username;
    private String password;
    private String database;
    private String driver;
    private Pool pool;
}
