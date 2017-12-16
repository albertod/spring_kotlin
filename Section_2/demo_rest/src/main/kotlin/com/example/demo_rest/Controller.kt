package com.example.demo_rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    @Autowired
    lateinit var employeeService: EmployeeService

    @GetMapping("/{id}")
    fun getName(@PathVariable("id") id: Int): String {
        return "${employeeService.findEmployee(id)}\n"
    }
}