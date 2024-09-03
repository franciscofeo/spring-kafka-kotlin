package com.franciscofeo.kotlinkafkaexample

import com.franciscofeo.kotlinkafkaexample.entity.BaseEntity
import com.franciscofeo.kotlinkafkaexample.entity.Repository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Service(
    private val repository: Repository
) {

    @Transactional
    fun saveEntity() {
        logger.info("SAVING ENTITY IN DATABASE...")
        val entity = BaseEntity(someField = "SOME_VALUE")
        entity.registerEvent()
        repository.save(entity)
    }

    companion object{
        val logger = KotlinLogging.logger{}
    }
}