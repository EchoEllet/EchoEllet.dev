package dev.echoellet.site.home.terminal

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.AlignItems
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.content
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.left
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.top
import com.varabyte.kobweb.compose.ui.modifiers.transform
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.active
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span

@Composable
fun TerminalHeader() {
    Div(
        attrs = Modifier
            .display(DisplayStyle.Flex)
            .alignItems(AlignItems.Center)
            .padding(10.px)
            .backgroundColor(Color.rgb(0x3b3b3b))
            .toAttrs()
    ) {
        TerminalHeaderWindowIcon(backgroundColor = Color.rgb(0xff5f56)) // Close
        TerminalHeaderWindowIcon(backgroundColor = Color.rgb(0xffbd2e)) // Minimize
        TerminalHeaderWindowIcon(backgroundColor = Color.rgb(0x27c93f)) // Maximize
    }
}

// TODO: Hover icon for the window icon, center the dot properly and improve the style name

val TerminalHeaderWindowIconHoverStyle = CssStyle {
    hover { Modifier.position(Position.Relative) }

    cssRule(":hover::before") {
        Modifier
            .content("")
            .position(Position.Absolute)
            .top(50.percent).left(50.percent)
            .backgroundColor(Colors.Black.copyf(alpha = 0.6f))
            .borderRadius(50.percent)
            .transform { translate((-50).percent, (-50).percent) }
            .size(4.px)
    }

    active { Modifier.opacity(0.8) }
}

@Composable
private fun TerminalHeaderWindowIcon(backgroundColor: Color) {
    Span(
        attrs = Modifier.display(DisplayStyle.InlineBlock)
            .size(12.px)
            .margin(right = 8.px)
            .borderRadius(50.percent)
            .backgroundColor(backgroundColor)
            .then(TerminalHeaderWindowIconHoverStyle.toModifier())
            .toAttrs()
    )
}
