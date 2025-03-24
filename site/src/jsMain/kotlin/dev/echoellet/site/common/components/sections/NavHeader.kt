package dev.echoellet.site.common.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.browser.dom.ElementTarget
import com.varabyte.kobweb.compose.css.BackgroundClip
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.css.functions.clamp
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.css.functions.saturate
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.animation
import com.varabyte.kobweb.compose.ui.modifiers.backdropFilter
import com.varabyte.kobweb.compose.ui.modifiers.backgroundClip
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.backgroundImage
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.letterSpacing
import com.varabyte.kobweb.compose.ui.modifiers.onAnimationEnd
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.setVariable
import com.varabyte.kobweb.compose.ui.modifiers.top
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.translateX
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.CloseIcon
import com.varabyte.kobweb.silk.components.icons.HamburgerIcon
import com.varabyte.kobweb.silk.components.icons.MoonIcon
import com.varabyte.kobweb.silk.components.icons.SunIcon
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.overlay.Overlay
import com.varabyte.kobweb.silk.components.overlay.OverlayVars
import com.varabyte.kobweb.silk.components.overlay.PopupPlacement
import com.varabyte.kobweb.silk.components.overlay.Tooltip
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.animation.toAnimation
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.breakpoint.displayUntil
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.ColorPalettes
import dev.echoellet.site.common.components.widgets.IconButton
import dev.echoellet.site.common.constants.ProjectInfo
import dev.echoellet.site.toSitePalette
import org.jetbrains.compose.web.css.AnimationDirection
import org.jetbrains.compose.web.css.AnimationFillMode
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Header
import org.jetbrains.compose.web.dom.Nav
import org.jetbrains.compose.web.dom.Text

val NavHeaderStyle = CssStyle.base {
    Modifier.fillMaxWidth().padding(1.cssRem)
        .backdropFilter(saturate(180.percent), blur(5.px))
        .position(Position.Sticky)
        .top(0.percent)
        .boxShadow(
            spreadRadius = 1.px,
            color = if (colorMode.isDark) Colors.Gainsboro.copyf(alpha = 0.2f) else ColorPalettes.Gray._900.copyf(alpha = 0.2f),
        )
}

val NavLinkStyle = CssStyle {
    base { Modifier.transition(Transition.of("color", 200.ms)) }
    hover { Modifier.color(colorMode.toSitePalette().brand.primary) }
}

@Composable
private fun NavLink(path: String, text: String) {
    Link(path, text, variant = UndecoratedLinkVariant.then(UncoloredLinkVariant), modifier = NavLinkStyle.toModifier())
}

@Composable
private fun MenuItems() {
    // TODO: Avoid hardcoding links everywhere?
    NavLink("/", "Home")
    NavLink("/resume", "Resume")
    NavLink("/#contact", "Contact")
    NavLink("/blog", "Blog")
}

@Composable
private fun ColorModeButton() {
    var colorMode by ColorMode.currentState
    IconButton(onClick = { colorMode = colorMode.opposite }) {
        if (colorMode.isLight) MoonIcon() else SunIcon()
    }
    Tooltip(ElementTarget.PreviousSibling, "Toggle color mode", placement = PopupPlacement.BottomRight)
}

@Composable
private fun HamburgerButton(onClick: () -> Unit) {
    IconButton(onClick) { HamburgerIcon() }
}

@Composable
private fun CloseButton(onClick: () -> Unit) {
    IconButton(onClick) { CloseIcon() }
}

// Render the side menu until its close animation is complete to avoid an abrupt disappearance.
enum class SideMenuState {
    CLOSED,
    OPEN,
    CLOSING;

    fun close() = when (this) {
        CLOSED -> CLOSED
        OPEN -> CLOSING
        CLOSING -> CLOSING
    }
}

val SideMenuSlideInAnim = Keyframes {
    from {
        Modifier.translateX(100.percent)
    }

    to {
        Modifier
    }
}

@Composable
fun NavHeader() {
    Header(attrs = NavHeaderStyle.toModifier().toAttrs()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Link(
                "/",
                modifier = Modifier.fontWeight(FontWeight.Bold)
                    .letterSpacing(2.px)
                    .fontSize(2.cssRem)
                    .color(ColorMode.current.toSitePalette().brand.primary)
                    .letterSpacing(2.px)
                    .backgroundImage(
                        // TODO: Brand color is hardcoded, consider adjusting it for dark mode too
                        linearGradient(90.deg, Color.rgb(0x0066CC), Color.rgb(0x5c93ff))
                    )
                    .backgroundClip(BackgroundClip.Text)
                    .styleModifier { property("-webkit-text-fill-color", "transparent") },
                variant = UndecoratedLinkVariant
            ) {
                Text(ProjectInfo.DEVELOPER_NAME)
            }

            Spacer()

            Nav {
                Row(
                    Modifier.gap(1.5.cssRem).displayIfAtLeast(Breakpoint.MD),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MenuItems()
                    ColorModeButton()
                }

                Row(
                    Modifier
                        .fontSize(1.5.cssRem)
                        .gap(1.cssRem)
                        .displayUntil(Breakpoint.MD),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var menuState by remember { mutableStateOf(SideMenuState.CLOSED) }

                    ColorModeButton()
                    HamburgerButton(onClick = { menuState = SideMenuState.OPEN })

                    if (menuState != SideMenuState.CLOSED) {
                        SideMenu(
                            menuState,
                            close = { menuState = menuState.close() },
                            onAnimationEnd = {
                                if (menuState == SideMenuState.CLOSING) menuState = SideMenuState.CLOSED
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SideMenu(menuState: SideMenuState, close: () -> Unit, onAnimationEnd: () -> Unit) {
    Overlay(
        Modifier
            .setVariable(OverlayVars.BackgroundColor, Colors.Transparent)
            .onClick { close() }
    ) {
        key(menuState) { // Force recompute animation parameters when the close button is clicked
            Column(
                Modifier
                    .fillMaxHeight()
                    .width(clamp(8.cssRem, 33.percent, 10.cssRem))
                    .align(Alignment.CenterEnd)
                    // Close button will appear roughly over the hamburger button, so the user can close
                    // things without moving their finger / cursor much.
                    .padding(top = 1.cssRem, leftRight = 1.cssRem)
                    .gap(1.5.cssRem)
                    .backgroundColor(ColorMode.current.toSitePalette().nearBackground)
                    .animation(
                        SideMenuSlideInAnim.toAnimation(
                            duration = 200.ms,
                            timingFunction = if (menuState == SideMenuState.OPEN) AnimationTimingFunction.EaseOut else AnimationTimingFunction.EaseIn,
                            direction = if (menuState == SideMenuState.OPEN) AnimationDirection.Normal else AnimationDirection.Reverse,
                            fillMode = AnimationFillMode.Forwards
                        )
                    )
                    .borderRadius(topLeft = 2.cssRem)
                    .onClick { it.stopPropagation() }
                    .onAnimationEnd { onAnimationEnd() },
                horizontalAlignment = Alignment.End
            ) {
                CloseButton(onClick = { close() })
                Column(
                    Modifier.padding(right = 0.75.cssRem).gap(1.5.cssRem).fontSize(1.4.cssRem),
                    horizontalAlignment = Alignment.End
                ) {
                    MenuItems()
                }
            }
        }
    }
}
