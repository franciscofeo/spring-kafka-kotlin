package com.franciscofeo.kotlinkafkaexample

import com.franciscofeo.kotlinkafkaexample.kafka.BaseKafkaProducer.BaseMessage
import com.franciscofeo.kotlinkafkaexample.entity.BaseEntity.BaseEvent
import com.franciscofeo.kotlinkafkaexample.kafka.BaseKafkaProducer
import mu.KotlinLogging
import org.hibernate.event.spi.AbstractEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener
import java.util.*

@Component
class EventListener(
    private val producer: BaseKafkaProducer,
) {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = [BaseEvent::class])
    fun publish(event: BaseEvent) {
        logger.info("EVENT RECEIVED")
        logger.info("PRODUCING MESSAGE TO A KAFKA TOPIC")
        producer.produce(
            BaseMessage(
                topic = "topic-example",
                key = UUID.randomUUID().toString(),
                value = event
            )
        )
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}
