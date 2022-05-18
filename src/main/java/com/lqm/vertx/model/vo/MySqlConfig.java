package com.lqm.vertx.model.vo;

import io.vertx.core.Vertx;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
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

    private MySQLConnectOptions createMySqlConnectOptions() {
        MySQLConnectOptions mySQLConnection = new MySQLConnectOptions();
        mySQLConnection.setHost(host);
        mySQLConnection.setPort(port);
        mySQLConnection.setUser(username);
        mySQLConnection.setDatabase(database);
        mySQLConnection.setPassword(password);
        return mySQLConnection;
    }

    private PoolOptions createPoolOptions() {
        return pool.createPoolOptions();
    }

    public MySQLPool createMysqlPool(Vertx vertx) {
        // create mysql pool
        return MySQLPool.pool(vertx, createMySqlConnectOptions(), createPoolOptions());
    }


}
