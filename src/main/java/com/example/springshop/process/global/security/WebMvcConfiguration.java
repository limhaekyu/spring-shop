package com.example.springshop.process.global.security;

import com.example.springshop.process.global.util.NullSerializer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    public WebMvcConfiguration() {
        super();
    }

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(customObjectMapper()));
    }

    @Bean
    public ObjectMapper customObjectMapper(){

        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule());
        mapper.getSerializerProvider().setNullValueSerializer(new NullSerializer());
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

        return mapper;
    }
}
