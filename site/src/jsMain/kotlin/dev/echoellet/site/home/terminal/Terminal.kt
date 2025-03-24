package dev.echoellet.site.home.terminal

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.toAttrs
import dev.echoellet.site.home.terminal.body.TerminalBody
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div

@Composable
fun Terminal(modifier: Modifier = Modifier) {
    Div(
        attrs = Modifier
            .fillMaxWidth(85.percent)
            .maxWidth(800.px)
            .minHeight(200.px)
            .backgroundColor(Color.rgb(0x2D2D2D))
            .borderRadius(8.px)
            .overflow(Overflow.Hidden)
            .boxShadow(0.px, 4.px, 10.px, color = Color.rgba(0, 0, 0, 0.5f))
            .margin { bottom(24.px) }
            .then(modifier)
            .toAttrs()
    ) {
        TerminalHeader()
        TerminalBody()
    }
}
