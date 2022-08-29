package com.automatic.CodeGenerators;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class codegenerators {

    public static void main(String[] args) {
        // 模块名
        String modelName = "commode";
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/automatic-control?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true",
                        "root", "root")
                .globalConfig(builder -> {
                    builder.author("zgy") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://IDEA//储存//Automatic-control//src//main//java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.automatic.AutomaticControl") // 设置父包名
                            .moduleName(modelName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://IDEA//储存//Automatic-control//src//main//java//com//automatic//AutomaticControl//commode//mapper/xml")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder
                            .addInclude("commode_details", "commode_type", "commodity_table") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                            //.entityBuilder().logicDeletePropertyName("deleteFlag").enableLombok().idType(IdType.ASSIGN_ID).controllerBuilder().enableRestStyle();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
