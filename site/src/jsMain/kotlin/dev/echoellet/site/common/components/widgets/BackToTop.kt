package dev.echoellet.site.common.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.Visibility
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.bottom
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.right
import com.varabyte.kobweb.compose.ui.modifiers.scale
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.visibility
import com.varabyte.kobweb.silk.components.icons.ArrowUpIcon
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.active
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import dev.echoellet.site.toSitePalette
import kotlinx.browser.window
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s
import org.w3c.dom.SMOOTH
import org.w3c.dom.ScrollBehavior
import org.w3c.dom.ScrollToOptions

// TODO: Decide when to use CssStyle or Modifier inline-style, update all code to ensure consistency, https://discord.com/channels/886036660767305799/1326628029321183352/1326641120486101142

// TODO: Finish, support light mode, keep it responsive, hover affect to scale it, review everything (bad design)

val BackToTopButtonStyle = CssStyle {
    val backgroundColor = colorMode.toSitePalette().brand.primary

    base {
        Modifier
            .position(Position.Fixed)
            .size(50.px)
            .bottom(20.px).right(20.px)
            .backgroundColor(backgroundColor)
            .transition(
                Transition.of("scale", 0.15.s), Transition.of("background-color", 0.2.s),
                Transition.of("opacity", 0.2.s), Transition.of("visibility", 0.2.s),
            )
            .color(Colors.White)
            .boxShadow(
                0.px, 4.px, 6.px,
                color = Colors.Black.copyf(alpha = 0.1f)
            )
    }

    hover {
        Modifier
            .scale(1.1)
            .backgroundColor(backgroundColor.darkened(0.2f))
    }
    active { Modifier.scale(0.95) }
}

@Composable
fun BackToTopButton() {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        window.addEventListener("scroll", {
            visible = window.scrollY > 1000
        })
    }

    IconButton(
        onClick = {
            window.scrollTo(
                ScrollToOptions(
                    top = 0.0,
                    behavior = ScrollBehavior.SMOOTH
                )
            )
        },
        BackToTopButtonStyle.toModifier()
            .opacity(if (visible) 1 else 0)
            .visibility(if (visible) Visibility.Visible else Visibility.Hidden)
    ) {
        ArrowUpIcon(Modifier.scale(1.4))
    }
}