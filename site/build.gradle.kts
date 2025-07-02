import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import com.varabyte.kobwebx.gradle.markdown.MarkdownBlock
import com.varabyte.kobwebx.gradle.markdown.ext.kobwebcall.KobwebCall
import com.varabyte.kobwebx.gradle.markdown.handlers.MarkdownHandlers
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
}

group = "dev.echoellet.site"
version = "1.0-SNAPSHOT"

kotlin {
    configAsKobwebApplication("site", includeServer = false)

    js {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions.target = "es2015"
    }

    sourceSets {
        jsMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            implementation(libs.kobwebx.markdown)
        }
    }
}

kobweb {
    app {
        index {
            description.set("Portfolio, blog and personal site.")
        }
    }
    markdown {
        useCustomCodeBlock()
        addHoverLinkIconsToHeadings()
        generateBlogPostsDataFromMarkdownDirectory()
    }
}

fun MarkdownBlock.useCustomCodeBlock() {
    // TODO: See the TODO in CodeBlock.kt
//    handlers {
//        val widgetsPackage = "dev.echoellet.site.common.components.widgets"
//        code.set { code ->
//            "${widgetsPackage}.code.CodeBlock(\"\"\"${code.literal.escapeTripleQuotedText()}\"\"\", lang = ${
//                code.info.takeIf { it.isNotBlank() }?.let { "\"$it\"" }
//            })"
//        }
//    }
}

fun MarkdownBlock.addHoverLinkIconsToHeadings() {
    // TODO: Does adding these imports inside handlers.heading.set make a difference?
    imports.addAll(
        "com.varabyte.kobweb.compose.ui.Modifier", "com.varabyte.kobweb.compose.ui.modifiers.margin",
        "org.jetbrains.compose.web.css.em"
    )

    val baseHeadingHandler = handlers.heading.get()
    handlers.heading.set { heading ->
        val result = baseHeadingHandler.invoke(this, heading)
        // ID is guaranteed to be created as a side effect of the base handler.
        val id = data.getValue(MarkdownHandlers.DataKeys.HeadingIds).getValue(heading)

        heading.appendChild(KobwebCall(".common.components.widgets.HoverLink(\"#$id\", Modifier.margin(left = 0.7.em))"))

        result
    }
}

// Update BlogPost too in the source code if you modify this.
private data class MarkdownBlogPost(
    val route: String,
    val title: String,
    val description: String,
    val tocMin: Int?,
    val tocMax: Int?,
    val author: String?,
    val date: String,
    val updated: String?
) {
    private fun String?.insideDoubleQuotesOrNull() = if (this != null) "\"${this}\"" else null

    private fun toBlogPostClassCode(): String =
        """BlogPost(title = "$title", description = "$description", tocMin = $tocMin ?: BlogPost.DEFAULT_TOC_MIN, tocMax = $tocMax ?: BlogPost.DEFAULT_TOC_MAX, author = ${author.insideDoubleQuotesOrNull() ?: "BlogPost.DEFAULT_AUTHOR"}, date = "$date", updated = ${updated.insideDoubleQuotesOrNull()})"""

    fun toBlogPostWithRouteClassCode(): String =
        """BlogPostWithRoute(blogPost = ${toBlogPostClassCode()}, route = "$route")""".trimIndent()
}

fun MarkdownBlock.generateBlogPostsDataFromMarkdownDirectory() {

    val rootPackage = project.group as String
    val logger = logger

    process.set { markdownEntries ->
        val requiredFields = listOf("title", "description", "date")

        val markdownBlogPosts = markdownEntries
            // Filter markdown files that are not blog posts.
            // Example full qualified name: dev.kernelcraft.site.pages.blog._2025.TempPagedev.kernelcraft.site.pages.ResumePage
            .filter { markdownEntry -> markdownEntry.fqn.contains("pages.blog") }
            .map { markdownEntry ->
                val frontMatter = markdownEntry.frontMatter

                val hasMissingFields =
                    requiredFields.any { requiredField ->
                        val isMissing = frontMatter[requiredField]?.singleOrNull() == null
                        if (isMissing) {
                            logger.error("The field '$requiredField' is missing in ${markdownEntry.fqn}")
                        }
                        isMissing
                    }
                if (hasMissingFields) {
                    throw GradleException(
                        "Missing fields in the markdown blog file '${markdownEntry.filePath}'. " +
                                "See the output above."
                    )
                }

                fun requireField(fieldName: String): String = frontMatter[fieldName]?.single()
                    ?: error("The '${fieldName}' is a required field and it's null despite the check.")

                MarkdownBlogPost(
                    route = markdownEntry.route,
                    title = requireField("title"),
                    description = requireField("description"),
                    tocMin = frontMatter["tocMin"]?.single()?.toInt(),
                    tocMax = frontMatter["tocMax"]?.single()?.toInt(),
                    author = frontMatter["author"]?.single(),
                    date = requireField("date"),
                    updated = frontMatter["updated"]?.single()
                )
            }.sortedByDescending { it.date }

        // TODO: Include the generated file BlogPosts.kt in build directory?
        // TODO: Find a better way to reference the file.
        val blogPackage = "${rootPackage}.blog"
        val blogPostsKotlinFile = File("./site/src/jsMain/kotlin/${blogPackage.replace('.', '/')}/BlogPosts.kt")
        val blogPostsFileExists = blogPostsKotlinFile.exists()
        if (!blogPostsFileExists) {
            throw GradleException(
                "The blog posts Kotlin file does not exist: '${blogPostsKotlinFile.path}'\n" +
                        "Create the file or update the build scripts if it's moved somewhere else."
            )
        }

        blogPostsKotlinFile.writeText(buildString {
            appendLine(
                """
                    // THIS FILE IS GENERATED - Modify the build script to change it.
                    
                    package $blogPackage
                    
                    object BlogPosts {
                """.trimIndent()
            )

            append("    ")
            appendLine("val values = listOf<BlogPostWithRoute>(")
            appendLine(markdownBlogPosts.joinToString(separator = ", ") { it.toBlogPostWithRouteClassCode() })
            append("    ")
            appendLine(")")

            appendLine("}")
        })

        logger.info("Generated the blog posts data at: ${blogPostsKotlinFile.path}")
    }
}
