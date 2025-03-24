package dev.echoellet.site.common.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.setVariable
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import dev.echoellet.site.CircleButtonVariant
import dev.echoellet.site.UncoloredButtonVariant
import org.jetbrains.compose.web.css.em

@Composable
fun IconButton(onClick: () -> Unit, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Button(
        onClick = { onClick() },
        Modifier.setVariable(ButtonVars.FontSize, 1.em).then(modifier),
        variant = CircleButtonVariant.then(UncoloredButtonVariant)
    ) {
        content()
    }
}
