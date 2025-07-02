package dev.echoellet.site.blog

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.UserSelect
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fontStyle
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.userSelect
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import kotlin.js.Date

@Composable
fun BlogMetadata(
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
