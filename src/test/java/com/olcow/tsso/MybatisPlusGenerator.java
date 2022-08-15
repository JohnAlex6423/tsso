package com.olcow.tsso;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisPlusGenerator {

    @Test
    public void testSimple() {

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/tsso?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=Asia/Shanghai&amp&useSSL=false&allowPublicKeyRetrieval=true", "root", "root123").globalConfig(builder -> {
                    builder.author("zfb") // 设置作者
//							.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\peojects\\generator"); // 指定输出目录
                }).packageConfig(builder -> {
                    builder.parent("com.olcow") // 设置父包名
                            .moduleName("tsso"); // 设置父包模块名

                })
//				.strategyConfig(builder -> {
//					builder.addInclude("t_simple") // 设置需要生成的表名
//							.addTablePrefix("t_", "c_"); // 设置过滤表前缀
//				})
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
