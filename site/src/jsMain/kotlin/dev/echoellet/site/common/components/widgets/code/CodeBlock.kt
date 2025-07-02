package dev.echoellet.site.common.components.widgets.code

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Code
import org.jetbrains.compose.web.dom.Pre
import org.jetbrains.compose.web.dom.Text

// TODO: Complete, add copy button, and line number and also configure inline code, complete Gradle configuration.

@Composable
fun CodeBlock(code: String, lang: String?) {
    Pre {
        Code(attrs = Modifier.classNames(lang?.let { "language-${it}" } ?: "nohighlight").toAttrs()) {
            Text(code)
        }
    }
}
