package com.example.medfinder

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class MedicineTest {

    @Test
    fun testmedicinePrescrptionName() {

        val medicine = medicine("Paracetamol","Pain reliever and fever reducer","12345", "https://example.com/paracetamol.jpg")

        assertEquals("Paracetamol", medicine.name)
        //assertEquals("Pain reliever and fever reducer", medicine.Description)
        //assertEquals("12345", medicine.uid)
        //assertEquals("https://example.com/paracetamol.jpg", medicine.img)
    }

    @Test
    fun testmedicinePrescrptionDescription() {

        val medicine = medicine("Paracetamol","Pain reliever and fever reducer","12345", "https://example.com/paracetamol.jpg")

        //assertEquals("Paracetamol", medicine.name)
        assertEquals("Pain reliever and fever reducer", medicine.Description)
        //assertEquals("12345", medicine.uid)
        //assertEquals("https://example.com/paracetamol.jpg", medicine.img)
    }

    @Test
    fun testmedicinePrescrptionAllproperties() {

        val medicine = medicine("Paracetamol","Pain reliever and fever reducer","12345", "https://example.com/paracetamol.jpg")

        assertEquals("Paracetamol", medicine.name)
        assertEquals("Pain reliever and fever reducer", medicine.Description)
        assertEquals("12345", medicine.uid)
        assertEquals("https://example.com/paracetamol.jpg", medicine.img)
    }

    @Test
    fun testmedicinePrescrptionNullproperties() {

        val medicine = medicine("","","", "")

        assertEquals("", medicine.name)
        assertEquals("", medicine.Description)
        assertEquals("", medicine.uid)
        assertEquals("", medicine.img)
    }

    @Test
    fun testmedicinePrescrptionWrongproperties() {

        val medicine = medicine("Paracetamoll","","12345", "https://example.com/paracetamol.png")

        assertEquals("Paracetamol", medicine.name)
        assertEquals("Pain reliever and fever reducer", medicine.Description)
        assertEquals("123456", medicine.uid)
        assertEquals("https://example.com/paracetamol.jpg", medicine.img)
    }
}