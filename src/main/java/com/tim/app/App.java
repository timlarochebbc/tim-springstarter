package com.tim.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;


@SpringBootApplication(scanBasePackages = {"com.tim"})
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx){
        return args -> {

            System.out.println("Lets look at bean names");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for(String beanName : beanNames){
                System.out.println(beanName);
            }

        };
    }
}
