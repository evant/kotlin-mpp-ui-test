@file:JvmName("EspressoTestRunner")

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import me.tatarka.kotlinmultiplatformtest.*

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
    override fun onView(id: ViewId): View {
        return EspressoView(Espresso.onView(ViewMatchers.withResourceName(id.android)))
    }
}

private class EspressoView(private val delegate: ViewInteraction) : View {
    override fun hasText(text: String) {
        delegate.check(matches(withText(text)))
    }
}
