package me.tatarka.kotlinmultiplatformtest

import EspressoRunner
import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner

class MyTestRunner : AndroidJUnitRunner() {
    override fun onCreate(arguments: Bundle?) {
        uiRunner = EspressoRunner
        super.onCreate(arguments)
    }
}