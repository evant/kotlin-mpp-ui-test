package me.tatarka.kotlinmultiplatformtest

import kotlin.test.Test

class CommonUiTest {

    @Test
    fun testUi() {
        launchMain {
            onLabel("text").hasText("Hello World!")
        }
    }
}
