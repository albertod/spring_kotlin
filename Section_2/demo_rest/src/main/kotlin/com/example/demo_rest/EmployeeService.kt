package com.example.demo_rest

import org.springframework.stereotype.Service

@Service
class EmployeeService {

    val employeeMap = hashMapOf(1 to "Alberto", 2 to "Peter", 3 to "John")

    fun findEmployee(id: Int) = employeeMap[id] ?: "Not_Found"

}