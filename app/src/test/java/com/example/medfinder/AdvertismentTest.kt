package com.example.medfinder

import org.junit.Assert.assertEquals
import org.junit.Test

class AdvertismentTest {

    @Test
    fun testAdvertismentWrongId() {
        val advertisment = advertisment("001", "Buy One Get One Free")
        assertEquals("002", advertisment.i_id)
        //assertEquals("Buy One Get One Free", advertisment.description)
    }

    @Test
    fun testAdvertismentId() {
        val advertisment = advertisment("001", "Buy One Get One Free")
        assertEquals("001", advertisment.i_id)
        //assertEquals("Buy One Get One Free", advertisment.description)
    }

    @Test
    fun testAdvertismentNullId() {
        val advertisment = advertisment("", "Buy One Get One Free")
        assertEquals("", advertisment.i_id)
        //assertEquals("Buy One Get One Free", advertisment.description)
    }

    @Test
    fun testAdvertismentdescription() {
        val advertisment = advertisment("001", "")
        assertEquals("001", advertisment.i_id)
        assertEquals("", advertisment.description)
    }

    @Test
    fun testAdvertismentNullProperties() {
        val advertisment = advertisment("", "")
        assertEquals("", advertisment.i_id)
        assertEquals("", advertisment.description)
    }

}