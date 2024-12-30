package com.abrishmokie.JSMS.configuration;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public MustacheFactory mustacheFactory(){
        return new DefaultMustacheFactory();
    }

}
