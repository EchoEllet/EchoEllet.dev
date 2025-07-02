package dev.echoellet.site.blog

import dev.echoellet.site.common.constants.ProjectInfo

// Update MarkdownBlogPost in the build script if you modify this file so BlogPosts.kt is generated correctly.

data class BlogPost(
    val title: String,
    val description: String,
    val tocMin: Int = DEFAULT_TOC_MIN,
    val tocMax: Int = DEFAULT_TOC_MAX,
    val author: String = DEFAULT_AUTHOR,
    val date: String,
    val updated: String? = null
) {
    companion object {
        const val DEFAULT_TOC_MIN = 2
        const val DEFAULT_TOC_MAX = 3
        const val DEFAULT_AUTHOR = ProjectInfo.DEVELOPER_NAME
    }
}

data class BlogPostWithRoute(val blogPost: BlogPost, val route: String)
