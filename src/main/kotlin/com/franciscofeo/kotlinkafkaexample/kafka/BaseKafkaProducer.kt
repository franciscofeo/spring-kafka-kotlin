package com.franciscofeo.kotlinkafkaexample.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class BaseKafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    fun produce(message: BaseMessage) {
        logger.debug { "Publishing record. Topic: ${message.topic}, Key: ${message.key}" }

        val record: ProducerRecord<String, String> = ProducerRecord(
            message.topic,
            message.key,
            objectMapper.writeValueAsString(message.value)
        )

        kafkaTemplate.send(record).get()

        logger.debug { "Published record. Topic: ${message.topic}, Key: ${message.key}" }
    }

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    data class BaseMessage(
        val topic: String,
        val key: String,
        val value: Any
    )

}
