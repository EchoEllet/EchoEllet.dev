package dev.kernelcraft.site.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.browser.dom.clearFocus
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.CssName
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.focus
import com.varabyte.kobweb.silk.style.selectors.link
import com.varabyte.kobweb.silk.style.selectors.visited
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import dev.kernelcraft.site.components.widgets.icons.LinkIcon
import kotlinx.browser.document
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.percent
import org.w3c.dom.HTMLElement

private const val HOVER_LINK_STYLE_NAME = "hover-link"

@InitSilk
fun initHeaderLinkHover(ctx: InitSilkContext) {
    (1..6).forEach { headingLevel ->
        ctx.stylesheet.registerStyleBase("h${headingLevel}:hover > .${HOVER_LINK_STYLE_NAME}") { Modifier.opacity(0.8) }
    }
}

@CssName(HOVER_LINK_STYLE_NAME)
val HoverLinkStyle = CssStyle {
    base {
        Modifier
            .opacity(0.percent)
            .transition(Transition.of("opacity", 100.ms))
            .fontSize(0.8.em)
    }
    link { Modifier.color(colorMode.toPalette().color) }
    visited { Modifier.color(colorMode.toPalette().color) }
    focus { Modifier.opacity(1) }
}

@Composable
fun HoverLink(path: String, modifier: Modifier = Modifier) {
    Link(path, HoverLinkStyle.toModifier().onClick {
        (document.activeElement as? HTMLElement)?.clearFocus()
    }.then(modifier)) { LinkIcon() }
}
