package com.franciscofeo.kotlinkafkaexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinKafkaExampleApplication

fun main(args: Array<String>) {
    runApplication<KotlinKafkaExampleApplication>(*args)
}
