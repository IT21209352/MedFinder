package com.example.medfinder

import org.junit.Assert.assertEquals
import org.junit.Test

class InventoryTest {

    @Test
    fun testDrugName() {
        val inventory = inventry("001", "Medicine", "10mg", "100", "10.99", "2023-05-12")
        //assertEquals("001", inventory.i_id)
        assertEquals("Medicine", inventory.name)
       // assertEquals("10mg", inventory.Dosage)
        //assertEquals("100", inventory.qty)
        //assertEquals("10.99", inventory.price)
        //assertEquals("2023-05-12", inventory.exp)
    }

    @Test
    fun testDrugDosage() {
        val inventory = inventry("002", "Medicine", "10mg", "100", "10.99", "2023-05-12")
        //assertEquals("001", inventory.i_id)
        //assertEquals("Medicine", inventory.name)
        assertEquals("10mg", inventory.Dosage)
        //assertEquals("100", inventory.qty)
        //assertEquals("10.99", inventory.price)
        //assertEquals("2023-05-12", inventory.exp)
    }

    @Test
    fun testDrugDosageQty() {
        val inventory = inventry("002", "Medicine", "10mg", "100", "10.99", "2023-05-12")
        //assertEquals("001", inventory.i_id)
        //assertEquals("Medicine", inventory.name)
        assertEquals("10mg", inventory.Dosage)
        assertEquals("100", inventory.qty)
        //assertEquals("10.99", inventory.price)
        //assertEquals("2023-05-12", inventory.exp)
    }

    @Test
    fun testDrugprice() {
        val inventory = inventry("002", "Medicine", "10mg", "100", "10.99", "2023-05-12")
        //assertEquals("001", inventory.i_id)
        //assertEquals("Medicine", inventory.name)
        //assertEquals("10mg", inventory.Dosage)
        //assertEquals("100", inventory.qty)
        assertEquals("10.99", inventory.price)
        //assertEquals("2023-05-12", inventory.exp)
    }

    @Test
    fun testDrugAllproperties() {
        val inventory = inventry("002", "Medicine", "10mg", "100", "10.99", "2023-05-12")
        assertEquals("002", inventory.i_id)
        assertEquals("Medicine", inventory.name)
        assertEquals("10mg", inventory.Dosage)
        assertEquals("100", inventory.qty)
        assertEquals("10.99", inventory.price)
        assertEquals("2023-05-12", inventory.exp)
    }

}