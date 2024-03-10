package com.study.sos_backend;

import com.study.sos_backend.config.JpaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({JpaConfig.class})
@SpringBootApplication
public class SosBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SosBackendApplication.class, args);
    }

}
