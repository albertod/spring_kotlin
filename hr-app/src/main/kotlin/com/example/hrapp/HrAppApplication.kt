package com.example.hrapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HrAppApplication

fun main(args: Array<String>) {
    runApplication<HrAppApplication>(*args)
}