package dev.echoellet.site.common.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gridRow
import com.varabyte.kobweb.compose.ui.modifiers.gridTemplateRows
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toAttrs
import dev.echoellet.site.common.components.sections.Footer
import dev.echoellet.site.common.components.sections.NavHeader
import dev.echoellet.site.common.components.widgets.BackToTopButton
import dev.echoellet.site.common.constants.ProjectInfo
import kotlinx.browser.document
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.fr
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.Node
import org.w3c.dom.asList

val PageContentStyle = CssStyle {
    base { Modifier.fillMaxSize().padding(leftRight = 2.cssRem, top = 4.cssRem) }
    Breakpoint.MD { Modifier.maxWidth(60.cssRem) }
}

@Composable
fun PageLayout(title: String, description: String? = null, content: @Composable ColumnScope.() -> Unit) {
    LaunchedEffect(title) {
        document.title = "${ProjectInfo.DISPLAY_NAME} - $title"
        description?.let {
            document.querySelector("""meta[name="description"]""")!!.setAttribute("content", description)
        }
    }

    Box(
        Modifier
            .fillMaxWidth()
            .minHeight(100.percent)
            .gridTemplateRows { size(1.fr); size(minContent) },
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier.fillMaxSize().gridRow(1),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NavHeader()
            Div(PageContentStyle.toAttrs()) {
                content()
            }
        }
        Footer(Modifier.fillMaxWidth().gridRow(2))
    }

    BackToTopButton()

    LaunchedEffect(Unit) {
        val message =
            " This HTML file is generated, for the project source code, refer to: ${ProjectInfo.REPOSITORY_URL} "
        val body = document.body ?: return@LaunchedEffect

        val existingComment = body.childNodes.asList().find {
            it.nodeType == Node.COMMENT_NODE && it.nodeValue == message
        }
        if (existingComment == null) {
            val comment = document.createComment(message)
            body.insertBefore(comment, body.firstChild)
        }
    }
}
