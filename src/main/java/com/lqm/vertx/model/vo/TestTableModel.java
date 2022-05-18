package com.lqm.vertx.model.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Data;

@Data
public class TestTableModel {
    @JsonAlias("id")
    private int id;
    @JsonAlias("name")
    private String name;
    @JsonAlias("sub_id")
    private int subId;
}
