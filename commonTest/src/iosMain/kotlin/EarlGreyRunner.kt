package me.tatarka.kotlinmultiplatformtest

//import cocoapods.EarlGrey.EarlGrey
//import cocoapods.EarlGrey.GREYElementInteraction
//import cocoapods.EarlGrey.grey_accessibilityID
//import cocoapods.EarlGrey.grey_text
import platform.XCTest.XCUIApplication

object EarlGreyRunner : UiRunner {

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

class EarlGreyScreen : Screen {
    override fun onView(id: ViewId): View {
//        return EarlGreyView(EarlGrey!!.selectElementWithMatcher(grey_accessibilityID(id.ios)))
        return object : View {
            override fun hasText(text: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }
}

//class EarlGreyView(private val delegate: GREYElementInteraction) : View {
//    override fun hasText(text: String) {
//        delegate.assertWithMatcher(grey_text(text))
//    }
//}