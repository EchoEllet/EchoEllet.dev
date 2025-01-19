package dev.kernelcraft.site.components.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.UserSelect
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontStyle
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.userSelect
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.document.Toc
import com.varabyte.kobweb.silk.components.document.TocBorderedVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobwebx.markdown.MarkdownContext
import com.varabyte.kobwebx.markdown.markdown
import dev.kernelcraft.site.constants.ProjectInfo
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import kotlin.js.Date

// See https://github.com/bitspittle/bitspittle.dev/blob/main/site/src/jsMain/kotlin/dev/bitspittle/site/components/layouts/BlogLayout.kt

// TODO: This will only throws error when visiting that page or exporting the site, need a way to validate it.
//  see: https://github.com/varabyte/kobweb?tab=readme-ov-file#iterating-over-all-markdown-files
private fun MarkdownContext.requireField(name: String) =
    requireNotNull(this.field(name)) { "The '$name' field is required for this blog." }

private fun MarkdownContext.field(name: String) = this.frontMatter[name]?.singleOrNull()

@Composable
fun BlogLayout(content: @Composable () -> Unit) {
    val ctx = rememberPageContext()
    val mdCtx = ctx.markdown ?: error("BlogLayout expected to be only called from a Markdown file.")

    val title = mdCtx.requireField("title")
    val description = mdCtx.requireField("description")
    val tocMin = mdCtx.field("toc-min")?.toInt() ?: 2
    val tocMax = mdCtx.field("toc-max")?.toInt() ?: 3
    val author = mdCtx.field("author") ?: ProjectInfo.DEVELOPER_NAME
    val date = mdCtx.requireField("date")
    val updated = mdCtx.field("updated")

    MarkdownLayout(title, description) {
        H1(Modifier.margin(top = 0.px, bottom = 4.px).toAttrs()) { Text(title) }
        BlogMetadata(author, date, updated)
        Toc(
            Modifier.fillMaxWidth().margin(top = 16.px),
            variant = TocBorderedVariant,
            minHeaderLevel = tocMin,
            maxHeaderLevel = tocMax,
        )
        content()
    }
}

@Composable
private fun BlogMetadata(
    author: String,
    dateStr: String,
    updatedStr: String?
) {
    Div(Modifier.color(Color.rgb(if (ColorMode.current.isDark) 0xDDDDDD else 0x555555)).toAttrs()) {
        P(Modifier.margin(0.px).toAttrs()) {
            BlogDateText(dateStr)
            SpanText(" • ", Modifier.userSelect(UserSelect.None))
            SpanText(author, Modifier.fontStyle(FontStyle.Italic))
        }
        updatedStr?.let {
            P(Modifier.color(Color.rgb(if (ColorMode.current.isDark) 0xAAAAAA else 0x888888)).margin(0.px).toAttrs()) {
                Text("Updated ")
                BlogDateText(it)
            }
        }
    }
}

@Composable
private fun BlogDateText(dateStr: String) {
    val date = Date(dateStr)
    val options = dateLocaleOptions {
        month = "short"; day = "numeric"; year = "numeric"
        timeZone = "UTC"
    }
    Text(date.toLocaleString("en", options))
}
