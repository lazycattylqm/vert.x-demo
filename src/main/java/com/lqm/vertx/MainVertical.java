package com.lqm.vertx;

import com.lqm.vertx.model.vo.MySqlConfig;
import com.lqm.vertx.model.vo.TempResponse;
import com.lqm.vertx.model.vo.TestTableModel;
import com.lqm.vertx.util.MysqlConfigUtil;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.LoggerHandler;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MainVertical extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        FileSystem fileSystem = vertx.fileSystem();
        MySqlConfig mySqlConfig = MysqlConfigUtil.getMySqlConfig();
        MySQLPool mysqlPool = mySqlConfig.createMysqlPool(vertx);
        MysqlConfigUtil.setPool(mysqlPool);
        Router router = Router.router(vertx);
        router.route("/").handler(context -> context.json(new TempResponse()));
        router.route("/get/").handler(context -> context.response().end("get"));
        router.route(HttpMethod.POST, "/post")
                .handler(LoggerHandler.create())
                .handler(BodyHandler.create())
                .handler(context -> {
                    log.info("path");
                    JsonObject bodyAsJson = context.getBodyAsJson();
                    TempResponse tempResponse = bodyAsJson.mapTo(TempResponse.class);
                    context.json(tempResponse);
                });
        vertx.createHttpServer().requestHandler(router).listen(8888).onSuccess(server -> {
            System.out.println("Http server started on port " + server.actualPort());
        });
        router.route(HttpMethod.POST, "/upload/")
                .handler(BodyHandler.create().setUploadsDirectory("./uploads"))
                .handler(context -> {
                    context.fileUploads().forEach(fileUpload -> {
                        System.out.println(fileUpload.fileName());
                        System.out.println(fileUpload.uploadedFileName());
                        fileSystem.move(fileUpload.uploadedFileName(), "./uploads/" + fileUpload.fileName(), res -> {
                            log.info("success");
                        });
                        fileSystem.delete(fileUpload.uploadedFileName());
                    });
                    context.json("ok");
                });
        router.route("/download/").handler(context -> {
            context.response().sendFile("./uploads/png.png");
        });
        router.route("/query/").handler(BodyHandler.create()).handler(context -> {
            Future<RowSet<Row>> execute = MysqlConfigUtil.getMySqlPool().query("select * from test_table").execute();
            execute.map(rows -> {
                List<TestTableModel> testTableModels = new ArrayList<>();
                rows.forEach(row -> {
                    TestTableModel testTableModel = row.toJson().mapTo(TestTableModel.class);
                    testTableModels.add(testTableModel);
                });
                Future<List> future = Future.future(h -> h.complete(testTableModels));
                return future;
            }).map(testTableModels -> {
                context.json(testTableModels);
                return Future.succeededFuture();
            });

        });
    }
}

