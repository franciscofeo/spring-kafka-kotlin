package com.franciscofeo.kotlinkafkaexample.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import com.franciscofeo.kotlinkafkaexample.Service.Companion.logger
import com.franciscofeo.kotlinkafkaexample.kafka.BaseKafkaProducer.BaseMessage
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class PrivateKafkaProducer(
    @Qualifier("privateKafkaTemplate")
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    fun produce(message: BaseMessage) {
        logger.info { "TransactionIdPrefix: ${kafkaTemplate.producerFactory.transactionIdPrefix}" }
        logger.info { "Transaction Capable: ${kafkaTemplate.producerFactory.transactionCapable()}" }
        logger.info { "TransactionIdPrefix: ${kafkaTemplate.transactionIdPrefix}" }
        logger.info { "AllowNonTransactional: ${kafkaTemplate.isAllowNonTransactional}" }
        logger.info { "InTransaction: ${kafkaTemplate.inTransaction()}" }
        BaseKafkaProducer(kafkaTemplate, objectMapper).produce(message)
        logger.info { "InTransaction: ${kafkaTemplate.inTransaction()}" }
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}
