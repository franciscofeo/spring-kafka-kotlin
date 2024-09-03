package com.franciscofeo.kotlinkafkaexample.kafka

import com.franciscofeo.kotlinkafkaexample.entity.BaseEntity
import mu.KotlinLogging
import mu.withLoggingContext
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer {

    @KafkaListener(
        topics = ["topic-example"],
        containerFactory = "privateListenerContainerFactory"
    )
    fun consume(message: BaseEntity.BaseEvent)  {
        logger.info("MESSAGE RECEIVED! -> $message")
        logger.info { "----- END ------" }
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}