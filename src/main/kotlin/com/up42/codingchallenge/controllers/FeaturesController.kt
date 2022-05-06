package com.up42.codingchallenge.controllers

import FeatureCollection
import com.up42.codingchallenge.services.FeatureService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/features")
class FeaturesController {

    @Autowired
    private lateinit var featureService: FeatureService

    @GetMapping
    fun getFeatures(): List<FeatureCollection.Feature> {

        return featureService.listFeatures()

    }

    @GetMapping("/{featureId}/quicklook")
    fun getImage(@PathVariable featureId: UUID): ResponseEntity<ByteArray> {

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(featureService.getImage(featureId))

    }


}

