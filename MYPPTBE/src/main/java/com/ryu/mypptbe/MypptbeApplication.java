package com.ryu.mypptbe;

import com.ryu.mypptbe.config.AppProperties;
import com.ryu.mypptbe.config.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({
        CorsProperties.class,
        AppProperties.class
})
public class MypptbeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MypptbeApplication.class, args);
    }

}
