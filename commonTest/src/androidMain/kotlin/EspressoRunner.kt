package me.tatarka.kotlinmultiplatformtest

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import kotlin.reflect.KClass
import kotlin.test.assertEquals

object EspressoRunner : UiRunner {
    override fun launchScreen(name: ScreenName, block: Screen.() -> Unit) {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent().setComponent(ComponentName(context, name.android))
        launch(intent, block)
    }

    override fun launchMain(block: Screen.() -> Unit) {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)!!
        launch(intent, block)
    }

    private fun launch(intent: Intent, block: Screen.() -> Unit) {
        ActivityScenario.launch<Activity>(intent).use {
            block(EspressoScreen())
        }
    }
}

private class EspressoScreen : Screen {
    override fun <V : View> find(id: ViewId, type: KClass<V>, block: V.() -> Unit) {
        Espresso.onView(ViewMatchers.withResourceName(id.android)).check { view, noViewFoundException ->
            if (view == null) {
                throw noViewFoundException!!
            }
            block(view as V)
        }
    }
}
