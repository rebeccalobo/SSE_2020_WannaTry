package SpringApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


//@SpringBootApplication(scanBasePackages = {"com.SSE2020.WannaTry"})
@SpringBootApplication
@ComponentScan("com")
public class Example extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Example.class, args);
    }
}