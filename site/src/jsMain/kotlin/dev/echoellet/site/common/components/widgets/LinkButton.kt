package dev.echoellet.site.common.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.AlignItems
import com.varabyte.kobweb.compose.css.JustifyItems
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.placeItems
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.textDecorationLine
import com.varabyte.kobweb.navigation.OpenLinkStrategy
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.LinkStyle
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.selectors.anyLink
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text

val LinkButtonVariant = LinkStyle.addVariant({ ButtonStyle.toModifier() }) {
    anyLink {
        Modifier.color(org.jetbrains.compose.web.css.Color.currentColor)
            .textDecorationLine(TextDecorationLine.None)
    }
    base {
        Modifier
            .display(DisplayStyle.Grid)
            .placeItems(AlignItems.Center, JustifyItems.Center)
            .textAlign(TextAlign.Center)
    }
}

@Composable
fun LinkButton(
    path: String,
    text: String,
    modifier: Modifier = Modifier,
    openInternalLinksStrategy: OpenLinkStrategy? = null,
    openExternalLinksStrategy: OpenLinkStrategy? = null,
    content: @Composable () -> Unit = {}
) {
    Link(
        path = path,
        variant = LinkButtonVariant,
        modifier = Modifier.minHeight(40.px).then(modifier),
        openInternalLinksStrategy = openInternalLinksStrategy,
        openExternalLinksStrategy = openExternalLinksStrategy,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            content()
            Text(text)
        }
    }
}
