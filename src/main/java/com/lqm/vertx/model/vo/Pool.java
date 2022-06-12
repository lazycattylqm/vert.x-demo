package com.lqm.vertx.model.vo;

import io.vertx.sqlclient.PoolOptions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pool {
    private int max;


    public PoolOptions createPoolOptions() {
        return new PoolOptions().setMaxSize(max);
    }
}
