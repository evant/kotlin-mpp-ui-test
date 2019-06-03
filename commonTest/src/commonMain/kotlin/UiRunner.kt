package me.tatarka.kotlinmultiplatformtest

lateinit var uiRunner: UiRunner

interface UiRunner {

    fun launchMain(block: Screen.() -> Unit)

    fun launchScreen(name: ScreenName, block: Screen.() -> Unit)
}

interface Screen {

    fun onView(id: ViewId): View

    fun onView(id: String) = onView(ViewId(id))

    fun onLabel(id: ViewId): Label

    fun onLabel(id: String) = onLabel(ViewId(id))
}

interface View

interface Label : View {

    fun hasText(text: String)
}

fun launchMain(block: Screen.() -> Unit) {
    uiRunner.launchMain(block)
}

fun launchScreen(name: ScreenName, block: Screen.() -> Unit) {
    uiRunner.launchScreen(name, block)
}

data class ScreenName(val android: String, val ios: String)

fun ScreenName(name: String) = ScreenName(android = name, ios = name)

data class ViewId(val android: String, val ios: String)

fun ViewId(id: String) = ViewId(android = id, ios = id)
