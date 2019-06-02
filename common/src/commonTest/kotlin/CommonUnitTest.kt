package me.tatarka.kotlinmultiplatformtest

import kotlin.test.Test
import kotlin.test.assertEquals

class CommonUnitTest {

    @Test
    fun testCommon() {
        assertEquals("Hello!", common())
    }
}