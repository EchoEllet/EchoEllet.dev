package dev.kernelcraft.site.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.core.Page
import dev.kernelcraft.site.components.layouts.PageLayout
import dev.kernelcraft.site.components.sections.home.HeroSection
import org.jetbrains.compose.web.css.percent

@Page
@Composable
fun HomePage() {
    PageLayout("Home") {
        Column(
            Modifier.width(100.percent),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeroSection()
        }
    }
}
