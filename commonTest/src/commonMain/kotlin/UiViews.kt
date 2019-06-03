package me.tatarka.kotlinmultiplatformtest

expect open class View
expect open class Text : View

expect val Text.text: String
