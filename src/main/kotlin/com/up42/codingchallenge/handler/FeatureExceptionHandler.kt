package com.up42.codingchallenge.handler

import com.up42.codingchallenge.exception.EmptyListException
import com.up42.codingchallenge.exception.ImageNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class FeatureExceptionHandler {

    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler
    fun handleEmptyListException(exc: EmptyListException): ResponseEntity<String> {

        logger.error("Features list empty")
        return ResponseEntity.notFound().build()

    }

    @ExceptionHandler
    fun handleImageNotFountException(exc: ImageNotFoundException): ResponseEntity<String> {

        logger.warn(exc.message)
        return ResponseEntity.notFound().build()

    }


}