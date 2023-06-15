package com.example.medfinder

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


class UserTest1 {

    @Test
    fun testUsername() {

        val user = User("kalpa chamathkara", "kalpachama@gmail.com", "123456", "0712343455", "kandy", "user")

        assertEquals("kalpa chamathkara", user.name)
        assertEquals("kalpachama@gmail.com", user.email)
        assertEquals("123456", user.uid)
        assertEquals("0712343455", user.phone)
        assertEquals("kandy", user.address)
        assertEquals("user", user.type)
    }

    @Test
    fun testUseremail() {

        val user = User("kalpa chamathkara", "kalpachama@gmail.com", "123456", "0712343455", "kandy", "user")

        assertEquals("kalpa chamathkara", user.name)
        assertEquals("kalpachama@gmail.com", user.email)
        assertEquals("123456", user.uid)
        assertEquals("0712343455", user.phone)
        assertEquals("kandy", user.address)
        assertEquals("user", user.type)
    }

    @Test
    fun testAllWrongProperties() {

        val user = User("kalpa chamathkara", "kalpachama@gmail.com", "123456", "0712343455", "kandy", "user")

        assertEquals("kalpaas chamathkara", user.name)
        assertEquals("kalpaddchama@gmail.com", user.email)
        assertEquals("123456", user.uid)
        assertEquals("07123643455", user.phone)
        assertEquals("manikhinna", user.address)
        assertEquals("pharmacy", user.type)
    }

    @Test
    fun testAllNUllProperties() {

        val user = User("", "", "", "", "", "")

        assertEquals("", user.name)
        assertEquals("", user.email)
        assertEquals("", user.uid)
        assertEquals("", user.phone)
        assertEquals("", user.address)
        assertEquals("", user.type)
    }
}

