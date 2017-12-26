package com.example.hrapp.service

import com.example.hrapp.model.Employee
import com.example.hrapp.model.EmployeeDto
import com.example.hrapp.model.EmployeeUpdateReq
import com.example.hrapp.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class EmployeeService {

    @Autowired lateinit var employeeRepo: EmployeeRepository

    fun createEmployee(employee: EmployeeDto): Mono<Employee> = employeeRepo.save(EmployeeDto.fromDto(employee))

    fun getEmployee(id: String): Mono<Employee> = employeeRepo.findById(id)

    fun getAllEmployees(minAge: Int? = null, minSalary: Double? = null): Flux<Employee>
            = employeeRepo.findAll()
            .filter { it.age >= minAge ?: Int.MIN_VALUE}
            .filter { it.salary >= minSalary ?: Double.NEGATIVE_INFINITY }

    fun updateEmployee(id: String, updateEmployee: EmployeeUpdateReq): Mono<Employee> {
        return employeeRepo.findById(id)
                .flatMap {
                    it.department = updateEmployee.department ?: it.department
                    it.salary = updateEmployee.salary ?: it.salary
                    employeeRepo.save(it)
                }
    }

    fun deleteEmployee(id: String): Mono<Void> = employeeRepo.deleteById(id)
}