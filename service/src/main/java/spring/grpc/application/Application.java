package spring.grpc.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Ranjith @ 2 Jan' 2018
 */
@SpringBootApplication
@ComponentScan("spring.grpc")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
