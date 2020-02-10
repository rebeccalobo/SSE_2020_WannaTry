package SpringApp;

import SpringApp.controller.HomePageController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;




@SpringBootApplication
@ComponentScan(basePackageClasses = HomePageController.class)

public class Example {
    public static void main(String[] args) {
        SpringApplication.run(Example.class, args);
    }
}