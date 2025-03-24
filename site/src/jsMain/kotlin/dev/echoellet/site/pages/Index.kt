package dev.echoellet.site.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.core.Page
import dev.echoellet.site.common.components.layouts.PageLayout
import dev.echoellet.site.home.hero.HeroSection
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
