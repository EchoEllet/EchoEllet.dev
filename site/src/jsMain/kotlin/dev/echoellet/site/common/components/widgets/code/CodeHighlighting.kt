package dev.echoellet.site.common.components.widgets.code

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import dev.echoellet.site.common.constants.Res
import kotlinx.browser.document
import org.jetbrains.compose.web.css.px
import org.w3c.dom.HTMLLinkElement
import org.w3c.dom.HTMLScriptElement

// TODO: Configure resources/highlight.js

@Composable
fun CodeHighlighting() {
    SetupHighlightJs()

    val colorMode = ColorMode.current

    LaunchedEffect(colorMode) {
        val styleElement = (document.querySelector("""link[title="hljs-style"]""") as HTMLLinkElement?) ?: run {
            val linkElement = (document.createElement("link") as HTMLLinkElement).apply {
                type = "text/css"
                rel = "stylesheet"
                title = "hljs-style"
            }.also { document.head!!.appendChild(it) }
            linkElement
        }
        styleElement.href = when (colorMode) {
            ColorMode.LIGHT -> Res.HIGHLIGHT_JS_LIGHT_CSS
            ColorMode.DARK -> Res.HIGHLIGHT_JS_DARK_CSS
        }
    }
}

@Composable
private fun SetupHighlightJs() {
    val ctx = rememberPageContext()

    LaunchedEffect(ctx.route) {
        val highlightJsSrc = Res.HIGHLIGHT_JS

        val scriptAdded = document.querySelector("""script[src="$highlightJsSrc"]""") != null
        if (scriptAdded) {
            HighlightJs.highlightAll()
            return@LaunchedEffect
        }

        val scriptElement = (document.createElement("script") as HTMLScriptElement).apply {
            src = highlightJsSrc
            async = true
        }
        scriptElement.onload = {
            HighlightJs.highlightAll()
        }
        scriptElement.onerror = { event, _, _, _, _ ->
            console.error("Failed to load highlight.js script from: $highlightJsSrc")
            console.error("Event:", event)
        }

        document.head!!.appendChild(scriptElement)
    }
}

// Only call this if highlight.js is loaded.
@JsName("hljs")
private external object HighlightJs {
    // TODO: Is this method still needed if we use custom code block and manually set the language?
    // https://highlightjs.readthedocs.io/en/latest/api.html#highlightall
    fun highlightAll()
}

@InitSilk
fun initHighlightJs(ctx: InitSilkContext) {
    ctx.stylesheet.registerStyleBase("code.hljs") { Modifier.borderRadius(8.px) }
}
