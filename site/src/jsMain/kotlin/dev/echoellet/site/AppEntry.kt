package dev.echoellet.site

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.scrollBehavior
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.core.AppGlobals
import com.varabyte.kobweb.core.isExporting
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.loadFromLocalStorage
import com.varabyte.kobweb.silk.theme.colors.saveToLocalStorage
import com.varabyte.kobweb.silk.theme.colors.systemPreference
import kotlinx.browser.document
import org.jetbrains.compose.web.css.vh

private const val COLOR_MODE_KEY = "colorMode"

@InitSilk
fun initColorMode(ctx: InitSilkContext) {
    ctx.config.initialColorMode = ColorMode.loadFromLocalStorage(COLOR_MODE_KEY) ?: ColorMode.systemPreference

    // Fixing the production color flash issue, details: https://github.com/varabyte/kobweb/releases/tag/v0.20.5,
    // Script which runs at load time that needs to be kept in sync with `initialColorMode` above.
    // This code checks if the user's local color mode preference is different from what was exported by Kobweb, replacing it if
    // different to prevent a flash of color after the page loads.
    // See also: https://github.com/bitspittle/bitspittle.dev/commit/c771800a4ad4771cf7353620e0410518300b7553
    if (AppGlobals.isExporting) {
        document.head!!.appendChild(
            document.createElement("script").apply {
                textContent = """
            {
               const storedColor = localStorage.getItem('${COLOR_MODE_KEY}'); // 'LIGHT', 'DARK', or null
               const desiredColor = storedColor
                  ? `silk-${'$'}{storedColor.toLowerCase()}`
                  : (window.matchMedia('(prefers-color-scheme: dark)').matches ? 'silk-dark' : 'silk-light');
               const oppositeColor = desiredColor === 'silk-dark' ? 'silk-light' : 'silk-dark';
               document.documentElement.classList.replace(oppositeColor, desiredColor);
            }
         """.trimIndent()
            }
        )
    }
}

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    SilkApp {
        val colorMode = ColorMode.current
        LaunchedEffect(colorMode) { colorMode.saveToLocalStorage(COLOR_MODE_KEY) }

        Surface(
            SmoothColorStyle.toModifier()
                .minHeight(100.vh)
                .scrollBehavior(ScrollBehavior.Smooth)
        ) {
            content()
        }
    }
}
