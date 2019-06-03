package me.tatarka.kotlinmultiplatformtest

import cocoapods.EarlGrey.EarlGrey
import cocoapods.EarlGrey.GREYElementInteraction
import cocoapods.EarlGrey.grey_accessibilityID
import cocoapods.EarlGrey.grey_text
import platform.XCTest.XCUIApplication
import kotlin.native.internal.test.testLauncherEntryPoint

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
    override fun onLabel(id: ViewId): Label {
        return EarlGreyLabel(EarlGrey!!.selectElementWithMatcher(grey_accessibilityID(id.ios)))
    }

    override fun onView(id: ViewId): View {
        return EarlGreyView(EarlGrey!!.selectElementWithMatcher(grey_accessibilityID(id.ios)))
    }
}

private class EarlGreyView(private val delegate: GREYElementInteraction) : View {
}

private class EarlGreyLabel(private val delegate: GREYElementInteraction) : Label {
    override fun hasText(text: String) {
        delegate.assertWithMatcher(grey_text(text))
    }
}
