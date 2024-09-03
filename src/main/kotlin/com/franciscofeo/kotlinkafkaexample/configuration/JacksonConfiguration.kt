package com.franciscofeo.kotlinkafkaexample.configuration

import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY
import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES
import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
import com.fasterxml.jackson.databind.DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies.LOWER_CAMEL_CASE
import com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.TimeZone.getTimeZone

@Configuration
class JacksonConfiguration {

    @Bean
    fun defaultObjectMapper(): ObjectMapper = jacksonObjectMapper()
        .setPropertyNamingStrategy(LOWER_CAMEL_CASE)
        .setDefaultPropertyInclusion(NON_NULL)
        .registerModule(JavaTimeModule())
        .disable(FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY)
        .disable(FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(WRITE_DATES_AS_TIMESTAMPS)
        .enable(FAIL_ON_NULL_FOR_PRIMITIVES)
        .enable(READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
        .setTimeZone(getTimeZone("UTC"))
}
