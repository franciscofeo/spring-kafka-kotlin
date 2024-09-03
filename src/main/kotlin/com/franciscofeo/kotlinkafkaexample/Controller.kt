package com.franciscofeo.kotlinkafkaexample

import com.franciscofeo.kotlinkafkaexample.entity.Repository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class Controller(
    private val service: Service
) {

    @PostMapping
    fun saveEntity() = service.saveEntity()
}