package SpringApp;

import SpringApp.controller.HomePageController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication(scanBasePackageClasses = HomePageController.class)
public class Example extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Example.class, args);
    }
}