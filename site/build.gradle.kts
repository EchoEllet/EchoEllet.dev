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

group = "dev.kernelcraft.site"
version = "1.0-SNAPSHOT"

kotlin {
    configAsKobwebApplication("site", includeServer = false)

    js {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions.target = "es2015"
    }

    sourceSets {
        commonMain.dependencies { implementation(libs.compose.runtime) }

        jsMain.dependencies {
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
            // TODO: Update the description
            description.set("Powered by Kobweb")
        }
    }
    markdown { addHoverLinkIconsToHeadings() }
}

fun MarkdownBlock.addHoverLinkIconsToHeadings() {
    imports.addAll(
        "com.varabyte.kobweb.compose.ui.Modifier", "com.varabyte.kobweb.compose.ui.modifiers.margin",
        "org.jetbrains.compose.web.css.em"
    )

    val baseHeadingHandler = handlers.heading.get()
    handlers.heading.set { heading ->
        val result = baseHeadingHandler.invoke(this, heading)
        // ID is guaranteed to be created as a side effect of the base handler.
        val id = data.getValue(MarkdownHandlers.DataKeys.HeadingIds).getValue(heading)

        heading.appendChild(KobwebCall(".components.widgets.HoverLink(\"#$id\", Modifier.margin(left = 0.7.em))"))

        result
    }
}
