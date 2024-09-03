package com.franciscofeo.kotlinkafkaexample.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.domain.AbstractAggregateRoot

@Entity
data class BaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val someField: String
): AbstractAggregateRoot<BaseEntity>() {

    fun registerEvent() = super.registerEvent(BaseEvent(someField))

    data class BaseEvent(val someField: String)
}