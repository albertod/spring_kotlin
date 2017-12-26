package com.example.hrapp.controller

import com.example.hrapp.model.EmployeeDto
import com.example.hrapp.model.EmployeeUpdateReq
import com.example.hrapp.service.DepartmentService
import com.example.hrapp.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class EmployeeController {

    @Autowired
    lateinit var employeeService: EmployeeService

    @Autowired
    lateinit var departmentService: DepartmentService

    @PostMapping("/employee")
    fun createEmployee(@Valid @RequestBody employee: EmployeeDto) = employeeService.createEmployee(employee)
                .map { newEmployee -> ResponseEntity.status(HttpStatus.CREATED).body(newEmployee)}

    @GetMapping("/employee/{id}")
    fun getEmployee(@PathVariable("id") id: String) = employeeService.getEmployee(id).map { EmployeeDto.toDto(it) }

    @GetMapping("/employee")
    fun getEmployees(@RequestParam("minAge", required = false) minAge: Int?,
                     @RequestParam("minSalary", required = false) minSalary: Double?) =
            employeeService.getAllEmployees(minAge, minSalary)

    @GetMapping("/departments")
    fun getAllDepartments() = departmentService.getAllDepartments()

    @PutMapping("/employee/{id}")
    fun updateEmployee(@PathVariable id: String,
                       @RequestBody updateEmployee: EmployeeUpdateReq) = employeeService.updateEmployee(id, updateEmployee)
                .map { _ -> ResponseEntity.ok().build<String>()}

    @DeleteMapping("/employee/{id}")
    fun deleteEmployee(@PathVariable id: String): ResponseEntity<String> {
        val delete = employeeService.deleteEmployee(id).block()
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build<String >()
    }

}