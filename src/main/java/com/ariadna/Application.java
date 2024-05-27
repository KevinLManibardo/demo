package com.ariadna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.ariadna.utils.DataFileLoader;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        loadDataFiles(context);
    }    
    
    private static void loadDataFiles(ConfigurableApplicationContext context) {
        DataFileLoader fr = context.getBean(DataFileLoader.class);
        fr.loadFiles("source", "./src/main/resources/files/sources", true);
        fr.loadFiles("event", "./src/main/resources/files/events", true);
    }
}