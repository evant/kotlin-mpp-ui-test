package me.tatarka.kotlinmultiplatformtest

import kotlin.test.Test

class CommonUiTest {

    @Test
    fun testUi() {
        launchMain {
            onView("text").hasText("Hello World!")
        }
    }
}
