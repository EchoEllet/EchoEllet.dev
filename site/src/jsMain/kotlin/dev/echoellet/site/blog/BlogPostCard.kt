package dev.echoellet.site.blog

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.backdropFilter
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.lineHeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

// TODO: Finish, support light mode

@Composable
fun BlogPostCard(blogPostWithRoute: BlogPostWithRoute) {
    val (blogPost, route) = blogPostWithRoute
    val colorMode = ColorMode.current

    Column(
        Modifier.padding(16.px)
            .margin(topBottom = 8.px)
            .borderRadius(15.px)
            .backgroundColor(Color.rgba(255, 255, 255, 0.015f))
            .border(1.px, LineStyle.Solid, Color.rgba(255, 255, 255, 0.12f))
            .backdropFilter(blur(2.px))
            .boxShadow(0.px, 4.px, 8.px, color = Color.rgba(0, 0, 0, 0.3f))
            .fillMaxWidth()
    ) {
        H4(Modifier.margin(topBottom = 0.px).toAttrs()) { Link(path = route, text = blogPost.title) }

        BlogMetadata(author = blogPost.author, dateStr = blogPost.date, updatedStr = null)

        P(
            Modifier.margin(bottom = 0.px, top = 4.px)
                .lineHeight(1.5)
                .fontSize(1.cssRem)
                .toAttrs()
        ) { Text(blogPost.description) }
    }
}