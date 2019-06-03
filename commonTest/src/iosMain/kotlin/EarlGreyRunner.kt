package me.tatarka.kotlinmultiplatformtest

import cocoapods.EarlGrey.EarlGrey
import cocoapods.EarlGrey.GREYActionBlock
import cocoapods.EarlGrey.GREYElementInteraction
import cocoapods.EarlGrey.grey_accessibilityID
import platform.UIKit.UILabel
import platform.XCTest.XCUIApplication
import platform.XCTest.XCUIElement
import kotlin.native.internal.test.testLauncherEntryPoint
import kotlin.reflect.KClass

object EarlGreyRunner : UiRunner {

    @Throws
    fun runKotlinTests() {
        uiRunner = this
        testLauncherEntryPoint(emptyArray())
    }

    override fun launchMain(block: Screen.() -> Unit) {
        launch(block)
    }

    override fun launchScreen(name: ScreenName, block: Screen.() -> Unit) {
        TODO()
    }

    private fun launch(block: Screen.() -> Unit) {
        XCUIApplication().launch()
        block(EarlGreyScreen())
    }
}

private class EarlGreyScreen : Screen {
    override fun <V : View> find(id: ViewId, type: KClass<V>, block: V.() -> Unit) {
        EarlGrey!!.selectElementWithMatcher(grey_accessibilityID(id.ios))
            .performAction(GREYActionBlock("", null) { element, errorOrNil ->
                val label = element as UILabel
                block(label as V)
                true
            })
    }
}
