package cn.boommanpro;

import cn.boommanpro.config.ThymeLeafConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.context.Context;

import java.io.FileWriter;
import java.io.Writer;

@SpringBootApplication
public class ThymeleafTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafTemplateApplication.class, args);
    }
}
