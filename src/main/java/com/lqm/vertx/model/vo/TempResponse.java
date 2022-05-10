package com.lqm.vertx.model.vo;

import lombok.Data;

@Data
public class TempResponse {
    private String name;
    private String address;

    public TempResponse() {
        name = "lqm";
        address = "beijing";
    }

}
