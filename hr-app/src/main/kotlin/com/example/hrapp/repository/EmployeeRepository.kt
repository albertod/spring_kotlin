package com.example.hrapp.repository

import com.example.hrapp.model.Employee
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository: ReactiveCassandraRepository<Employee, String>