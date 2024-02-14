package com.b3i.learn.java.spring.tutorial.restapi.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mm =  new ModelMapper();
        mm.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mm;
    }
}
