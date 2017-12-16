package com.example.hrapp.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionController: ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException?, headers: HttpHeaders?, status: HttpStatus?, request: WebRequest?): ResponseEntity<Any> {
        val errors = ex?.bindingResult?.fieldErrors?.map { e -> "${e.field} : ${e.defaultMessage}" }?.toList()
        val apiError = ApiError(HttpStatus.BAD_REQUEST, errors)
        return super.handleExceptionInternal(ex, apiError, headers, status, request)
    }
}

data class ApiError(val status: HttpStatus, val errors: List<String>?)