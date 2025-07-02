package dev.echoellet.site.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.core.Page
import dev.echoellet.site.blog.BlogPostCard
import dev.echoellet.site.blog.BlogPosts
import dev.echoellet.site.common.components.layouts.PageLayout

@Page
@Composable
fun BlogPage() {
    // TODO: Complete, also update values if needed
    PageLayout("Blog") {
        Column {
            BlogPosts.values.forEach { BlogPostCard(it) }
        }
    }
}