package com.example.hrapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DepartmentService {

    @Autowired
    lateinit var employeeService: EmployeeService

    fun getAllDepartments() = employeeService.getAllEmployees()
            .map { it.department }
            .distinct()
}