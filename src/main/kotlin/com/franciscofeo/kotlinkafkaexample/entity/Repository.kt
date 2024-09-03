package com.franciscofeo.kotlinkafkaexample.entity

import org.springframework.data.jpa.repository.JpaRepository

interface Repository: JpaRepository<BaseEntity, Long>