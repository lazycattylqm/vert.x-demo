package com.lqm.vertx.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.lqm.vertx.model.vo.MySqlConfig;

import java.io.IOException;
import java.io.InputStream;

public class MysqlConfigUtil {
    private static final MySqlConfig MY_SQL_CONFIG;

    static {
        MY_SQL_CONFIG = MysqlConfigUtil.init();
    }

    public static MySqlConfig getMySqlConfig() {
        return MY_SQL_CONFIG;
    }

    public static MySqlConfig init() {
        try {
            InputStream resourceAsStream = MySqlConfig.class.getClassLoader().getResourceAsStream("mysql.yaml");
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            MySqlConfig mySqlConfig = mapper.readValue(resourceAsStream, MySqlConfig.class);
            return mySqlConfig;
        } catch (IOException e) {
            return new MySqlConfig();
        }
    }
}
