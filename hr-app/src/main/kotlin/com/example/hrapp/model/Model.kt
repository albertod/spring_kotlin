package com.example.hrapp.model

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

@Table
data class Employee (@PrimaryKey var id: String,
                     var name: String,
                     var age: Int,
                     var department: String,
                     var salary: Double)

data class EmployeeDto(@get:NotBlank val name: String,
                       @get:Positive val age: Int,
                       @get:NotBlank val department: String,
                       @get:Positive val salary: Double) {



    companion object {
        fun fromDto(dto: EmployeeDto) = Employee(UUID.randomUUID().toString(), dto.name, dto.age, dto.department, dto.salary)
        fun toDto(employee: Employee) = EmployeeDto(employee.name, employee.age, employee.department, employee.salary)
    }
}



data class EmployeeUpdateReq(@get:NotBlank val department: String?, @get:Positive val salary: Double?)