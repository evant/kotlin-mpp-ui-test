package me.tatarka.kotlinmultiplatformtest

import platform.XCTest.XCUIApplication
import platform.XCTest.XCUIElement
import kotlin.native.internal.test.testLauncherEntryPoint
import kotlin.test.assertEquals

class XCUIRunner : UiRunner {

    @Throws
    fun runKotlinTests() {
        uiRunner = this
        testLauncherEntryPoint(emptyArray())
    }

    override fun launchScreen(name: ScreenName, block: Screen.() -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun launchMain(block: Screen.() -> Unit) {
        val app = XCUIApplication()
        app.launch()
        block(XCUIScreen(app))
    }
}

class XCUIScreen(private val app: XCUIApplication) : Screen {
    override fun onView(id: ViewId): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLabel(id: ViewId): Label {
        return XCUILabel(app.staticTexts.matchingIdentifier(id.ios).element)
    }
}

class XCUILabel(private val element: XCUIElement) : Label {
    override fun hasText(text: String) {
        assertEquals(text, element.label)
    }
}


