package com.example.hrapp.service

import com.example.hrapp.model.EmployeeDTO
import com.example.hrapp.model.EmployeeUpdateReq
import com.example.hrapp.repository.EmployeeRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmployeeService {

    @Autowired
    lateinit var employeeDb: EmployeeRepo

    fun createEmployee(employeeDTO: EmployeeDTO) = employeeDb.save(EmployeeDTO.newEmployee(employeeDTO))

    fun getEmployee(id: String) = employeeDb.findById(id)

    fun getAllEmployees(minAge: Int? = null, minSalary: Double? = null)
            = employeeDb.findAll()
                .filter { it.age >= minAge ?: Int.MIN_VALUE}
                .filter { it.salary >= minSalary ?: Double.MIN_VALUE}

    fun updateEmployee(id: String, updateEmployee: EmployeeUpdateReq) =
        employeeDb.findById(id)
                .flatMap {
                    it.department = updateEmployee.department ?: it.department
                    it.salary = updateEmployee.salary ?: it.salary
                    employeeDb.save(it)
                }

    fun deleteEmployee(id: String) = employeeDb.deleteById(id)
}