package dev.echoellet.site.home.hero

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.graphics.lightened
import com.varabyte.kobweb.compose.ui.modifiers.attr
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.columnGap
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.lineHeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.rowGap
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.textShadow
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.extendedBy
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import dev.echoellet.site.SubheadlineTextStyle
import dev.echoellet.site.common.components.widgets.LinkButton
import dev.echoellet.site.common.components.widgets.icons.GitHubIcon
import dev.echoellet.site.common.constants.ProjectInfo
import dev.echoellet.site.home.terminal.Terminal
import dev.echoellet.site.toSitePalette
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Text

val HeroContainerStyle = CssStyle {
    base { Modifier.fillMaxWidth().gap(2.cssRem) }
    Breakpoint.MD { Modifier.margin { top(20.vh) } }
}

val HeroButtonStyle = CssStyle {
    base { Modifier.width(300.px) }
    Breakpoint.MD { Modifier.width(150.px) }
}

val GitHubButtonStyle = HeroButtonStyle.extendedBy {
    val backgroundColor = if (colorMode.isDark) Colors.White else Colors.Black

    base {
        Modifier
            .backgroundColor(backgroundColor)
            .color(if (colorMode.isDark) Colors.Black else Colors.White)
    }

    hover {
        Modifier.backgroundColor(if (colorMode.isDark) backgroundColor.darkened() else backgroundColor.lightened())
    }
}

// TODO: Extract as primary button?
val ResumeButtonStyle = HeroButtonStyle.extendedBy {
    val backgroundColor = colorMode.toSitePalette().brand.primary

    base { Modifier.backgroundColor(backgroundColor).color(Colors.White) }

    hover {
        Modifier.backgroundColor(backgroundColor.lightened())
    }
}

@Composable
fun HeroSection() {
    Section {
        Div(attrs = HeroContainerStyle.toAttrs()) {
            val sitePalette = ColorMode.current.toSitePalette()

            Column(
                Modifier.gap(1.5.cssRem),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // TODO: Replace this text fully, extract some parts to the about section (might)

                H1(attrs = Modifier.textAlign(TextAlign.Center).toAttrs()) {
                    SpanText(
                        "Transforming ideas",
                        Modifier
                            .color(sitePalette.brand.primary)
                            .thenIf(ColorMode.current.isLight) {
                                Modifier.textShadow(0.px, 0.px, blurRadius = 0.08.cssRem, color = Colors.Gray)
                            },
                    )
                    Text(" into robust, user-centric software")
                }

                P(
                    attrs = SubheadlineTextStyle.toModifier()
                        .textAlign(TextAlign.Center)
                        .lineHeight(1.6)
                        .margin(0.px)
                        .fontSize(1.1.cssRem).toAttrs()
                ) {
                    val highlightModifier = Modifier.fontWeight(FontWeight.Bold)
                        .color(sitePalette.brand.accent)
                        .backgroundColor(if (ColorMode.current.isDark) Color.rgb(0x333333) else Color.rgb(0xf4f4f4))
                        .padding(2.px, 5.px)
                        .borderRadius(4.px)
                        .fontFamily("Courier New", "monospace")
                    SpanText("Dedicated Flutter developer", highlightModifier)
                    Text(" skilled in creating high-quality apps, packages, and platform-specific plugins. ")
                    Text("Focused on delivering fewer, well-executed features emphasizing ")
                    SpanText("clean, maintainable, and tested code", highlightModifier)
                    Text(". ")
                    Text("Committed to continuous improvement and collaborative progress.")
                }

                SimpleGrid(
                    numColumns(1, md = 2),
                    Modifier.margin(top = 4.px, bottom = 2.em)
                        .rowGap(1.cssRem).columnGap(16.px),
                ) {
                    LinkButton(
                        path = "/resume",
                        text = "Resume",
                        modifier = ResumeButtonStyle.toModifier()
                            .attr("download", "")
                    )

                    LinkButton(
                        path = ProjectInfo.GITHUB_ACCOUNT, text = "GitHub",
                        modifier = GitHubButtonStyle.toModifier(),
                    ) { GitHubIcon(modifier = Modifier.margin(right = 8.px), colorMode = ColorMode.current.opposite) }
                }

                Terminal()

            }
        }
    }
}
