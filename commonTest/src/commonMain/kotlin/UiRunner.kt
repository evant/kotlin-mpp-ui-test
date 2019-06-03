package me.tatarka.kotlinmultiplatformtest

import kotlin.reflect.KClass

lateinit var uiRunner: UiRunner

interface UiRunner {

    fun launchMain(block: Screen.() -> Unit)

    fun launchScreen(name: ScreenName, block: Screen.() -> Unit)
}

interface Screen {

    fun <V: View> find(id: ViewId, type: KClass<V>, block: V.() -> Unit)
}

inline fun <reified V: View> Screen.find(id: ViewId, noinline block: V.() -> Unit) {
    find(id, V::class, block)
}

inline fun <reified V: View> Screen.find(id: String, noinline block: V.() -> Unit) {
    find(ViewId(id), V::class, block)
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
