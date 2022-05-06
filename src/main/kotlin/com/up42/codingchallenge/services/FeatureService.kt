package com.up42.codingchallenge.services

import FeatureCollection
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.up42.codingchallenge.exception.EmptyListException
import com.up42.codingchallenge.exception.ImageNotFoundException
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.util.*


@Service
class FeatureService {

    @Value("\${features.data.path}")
    var path: String = ""
    
    fun listFeatures(): List<FeatureCollection.Feature> =
            ClassPathResource(path).file.readText()
                    .let { jsonString ->
                        jacksonObjectMapper().readValue<List<FeatureCollection>>(jsonString)
                    }.flatMap {
                        it.features
                    }.map {
                        it.apply {
                            id = properties?.id
                            timestamp = properties?.timestamp
                            beginViewingDate = properties?.acquisition?.beginViewingDate
                            endViewingDate = properties?.acquisition?.endViewingDate
                            missionName = properties?.acquisition?.missionName
                        }
                    }.ifEmpty {
                        throw EmptyListException("Features list is empty")
                    }

    fun getImage(searchId: UUID): ByteArray {

        val feature = listFeatures()
                .filter { searchId == it.id }
                .ifEmpty {
                    throw ImageNotFoundException("Image with id: $searchId not found")
                }.first()

        return Base64.getDecoder().decode(feature.properties?.quicklook)

    }
}