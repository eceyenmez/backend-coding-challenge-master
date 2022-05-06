package com.up42.codingchallenge

import com.up42.codingchallenge.exception.ImageNotFoundException
import com.up42.codingchallenge.services.FeatureService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import java.util.*


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FeaturesServiceTest {

    private val featureService = FeatureService()

    data class ExpectedFeature(
            val id: String,
            val timestamp: Long,
            val beginViewingDate: Long,
            val endViewingDate: Long,
            val missionName: String
    )

    @Test
    fun `should get feature list`() {

        val features = featureService.listFeatures()
        assertNotNull(features)
        assertInstanceOf(List::class.java,features)

    }

    @Test
    fun `should get Image`() {

        val imageByteArray = featureService.getImage(UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aea"))
        assertNotNull(imageByteArray)
        assertInstanceOf(ByteArray::class.java,imageByteArray)

    }

    @Test
    fun `should throw exception when Image not found`() {

        assertThrows<ImageNotFoundException> {
            featureService.getImage(UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aee"))
        }

    }


}