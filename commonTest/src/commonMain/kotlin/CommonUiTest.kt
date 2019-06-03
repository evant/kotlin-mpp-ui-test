package me.tatarka.kotlinmultiplatformtest

import kotlin.test.Test
import kotlin.test.assertEquals

class CommonUiTest {

    @Test
    fun testUi() {
        launchMain {
            find<Text>("text") {
                assertEquals("Hello World!", text)
            }
        }
    }
}
