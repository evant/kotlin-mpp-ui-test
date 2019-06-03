package me.tatarka.kotlinmultiplatformtest

import platform.XCTest.XCUIElement

actual typealias View = Element
actual typealias Text = TextElement

actual inline val Text.text get() = e.label

open class Element(val e: XCUIElement)
open class TextElement(e: XCUIElement): Element(e)
