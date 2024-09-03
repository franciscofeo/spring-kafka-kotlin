package com.franciscofeo.kotlinkafkaexample.configuration.kafka

import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
class KafkaBrokerPropertiesConfiguration {

    @Primary
    @Bean("privateKafkaProperties")
    @ConfigurationProperties("spring.kafka.private")
    fun private() = KafkaProperties()
}
