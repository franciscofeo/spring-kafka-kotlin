package com.franciscofeo.kotlinkafkaexample.configuration.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaAdmin
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter
import org.springframework.kafka.support.converter.JsonMessageConverter

@Configuration
class PrivateKafkaConfiguration(
    @Qualifier("privateKafkaProperties")
    private val kafkaProperties: KafkaProperties
) {

    @Primary
    @Bean("privateKafkaTemplate")
    fun privateKafkaTemplate(
        @Qualifier("privateKafkaAdmin")
        kafkaAdmin: KafkaAdmin
    ): KafkaTemplate<String, String> =
        KafkaTemplate(privateProducerFactory()).apply {
            setKafkaAdmin(kafkaAdmin)
            setObservationEnabled(true)
            isAllowNonTransactional = true
        }

    @Primary
    @Bean("privateProducerFactory")
    fun privateProducerFactory(): ProducerFactory<String, String> = DefaultKafkaProducerFactory(
        kafkaProperties.buildProducerProperties(null)
    )

    @Bean("privateKafkaAdmin")
    fun privateKafkaAdmin(): KafkaAdmin = KafkaAdmin(kafkaProperties.buildAdminProperties(null))
        .apply { setClusterId("admin-private") }

    @Primary
    @Bean("privateConsumerFactory")
    fun privateConsumerFactory(): ConsumerFactory<String, String> = DefaultKafkaConsumerFactory(
        kafkaProperties.buildConsumerProperties(null)
    )

    @Primary
    @Bean("privateListenerContainerFactory")
    fun privateListenerContainerFactory(
        @Qualifier("privateKafkaTemplate")
        kafkaTemplate: KafkaTemplate<String, String>,
        objectMapper: ObjectMapper
    ): KafkaListenerContainerFactory<*> {
        val containerFactory = ConcurrentKafkaListenerContainerFactory<String, String>()
        val jsonMessageConverter = JsonMessageConverter(objectMapper)
        containerFactory.consumerFactory = privateConsumerFactory()
        containerFactory.containerProperties.ackMode = kafkaProperties.listener.ackMode
        containerFactory.setBatchMessageConverter(BatchMessagingMessageConverter(jsonMessageConverter))
        containerFactory.setRecordMessageConverter(jsonMessageConverter)
        containerFactory.containerProperties.isObservationEnabled = true
        return containerFactory
    }
}
