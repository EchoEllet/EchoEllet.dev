package dev.echoellet.site.common.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.browser.dom.ElementTarget
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.setVariable
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.whiteSpace
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.overlay.PopupPlacement
import com.varabyte.kobweb.silk.components.overlay.Tooltip
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.color.ColorVar
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import dev.echoellet.site.common.components.widgets.icons.GitHubIcon
import dev.echoellet.site.common.constants.ProjectInfo
import dev.echoellet.site.toSitePalette
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Footer as HtmlFooter

val FooterStyle = CssStyle.base {
    Modifier
        .backgroundColor(colorMode.toSitePalette().nearBackground)
        .padding(topBottom = 1.5.cssRem, leftRight = 10.percent)
}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    HtmlFooter(attrs = FooterStyle.toModifier().then(modifier).toAttrs()) {
        Box(contentAlignment = Alignment.Center) {
            Row {
                GitHubIcon(Modifier.margin(right = 8.px, top = 4.px))

                Div(Modifier.textAlign(TextAlign.Center).toAttrs()) {
                    val sitePalette = ColorMode.current.toSitePalette()

                    Text("This site is ")

                    Link(
                        ProjectInfo.REPOSITORY_URL,
                        "open source",
                        Modifier.setVariable(ColorVar, sitePalette.brand.accent).whiteSpace(WhiteSpace.NoWrap),
                        variant = UncoloredLinkVariant
                    )
                    Text(" and built with ")
                    Link(
                        "https://github.com/varabyte/kobweb",
                        "Kobweb",
                        Modifier.color(Color.rgb(0x3C83EF)),
                        variant = UncoloredLinkVariant
                    )
                    Text(".")

                    Tooltip(
                        ElementTarget.PreviousSibling,
                        "An opinionated Kotlin web framework built on top of Compose HTML.",
                        placement = PopupPlacement.Top
                    )
                }
            }
        }
    }
}
