package org.mlayer.java;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(Main.class, "/");

        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                StringWriter sw = new StringWriter();
                try {
                    Template template = configuration.getTemplate("hello.ftl");

                    Map<String, Object> helloMap = new HashMap<String, Object>();
                    helloMap.put("name", "web app demo");

                    template.process(helloMap, sw);
                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                }
                return sw;
            }
        });
    }

    public static void test() {

    }
}
