package me.tatarka.kotlinmultiplatformtest

import android.widget.TextView

actual typealias View = android.view.View
actual typealias Text = TextView

actual inline val Text.text get() = text.toString()

