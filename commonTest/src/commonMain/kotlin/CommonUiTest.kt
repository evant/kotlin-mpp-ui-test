package me.tatarka.kotlinmultiplatformtest

import kotlin.test.Test

class CommonUiTest {

    @Test
    fun uiTest() {
        launchMain {
            onView("text").hasText("Hello World!")
        }
    }
}
