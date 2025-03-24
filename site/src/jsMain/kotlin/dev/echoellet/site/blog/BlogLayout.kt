package dev.echoellet.site.blog

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.document.Toc
import com.varabyte.kobweb.silk.components.document.TocBorderedVariant
import com.varabyte.kobwebx.markdown.MarkdownContext
import com.varabyte.kobwebx.markdown.markdown
import dev.echoellet.site.common.components.layouts.MarkdownLayout
import dev.echoellet.site.common.components.widgets.code.CodeHighlighting
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text

private fun MarkdownContext.requireField(name: String) =
    requireNotNull(this.field(name)) { "The '$name' field is required for this blog." }

private fun MarkdownContext.field(name: String) = this.frontMatter[name]?.singleOrNull()

@Composable
fun BlogLayout(content: @Composable () -> Unit) {
    val ctx = rememberPageContext()
    val mdCtx = ctx.markdown ?: error("BlogLayout expected to be only called from a Markdown file.")

    val blogPost = BlogPost(
        title = mdCtx.requireField("title"),
        description = mdCtx.requireField("description"),
        tocMin = mdCtx.field("toc-min")?.toInt() ?: BlogPost.DEFAULT_TOC_MIN,
        tocMax = mdCtx.field("toc-max")?.toInt() ?: BlogPost.DEFAULT_TOC_MAX,
        author = mdCtx.field("author") ?: BlogPost.DEFAULT_AUTHOR,
        date = mdCtx.requireField("date"),
        updated = mdCtx.field("updated"),
    )

    MarkdownLayout(blogPost.title, blogPost.description) {
        H1(Modifier.margin(top = 0.px, bottom = 4.px).toAttrs()) { Text(blogPost.title) }
        BlogMetadata(blogPost.author, blogPost.date, blogPost.updated)
        Toc(
            Modifier.fillMaxWidth().margin(top = 16.px),
            variant = TocBorderedVariant,
            minHeaderLevel = blogPost.tocMin,
            maxHeaderLevel = blogPost.tocMax,
        )
        content()
    }

    CodeHighlighting()
}
