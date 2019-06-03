package me.tatarka.kotlinmultiplatformtest

import platform.XCTest.XCUIApplication
import kotlin.native.internal.test.testLauncherEntryPoint
import kotlin.reflect.KClass

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
    override fun <V: View> find(id: ViewId, type: KClass<V>, block: V.() -> Unit) {
        val element = if (type == TextElement::class) {
            TextElement(app.staticTexts.matchingIdentifier(id.ios).element)
        } else {
            Element(app.otherElements.matchingIdentifier(id.ios).element)
        }
        block(element as V)
    }
}
