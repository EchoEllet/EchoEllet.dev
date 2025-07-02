package dev.echoellet.site.common.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.alignSelf
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.flexWrap
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.core.AppGlobals
import com.varabyte.kobweb.core.isExporting
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.AlignSelf
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text
import kotlin.time.Duration

@Composable
fun TypingTextAnimation(
    fullText: String,
    typingSpeedDuration: Duration,
    delayDuration: Duration? = null,
    isTypingForward: Boolean = true,
    onTypingFinished: () -> Unit = {},
    content: @Composable (currentText: String) -> Unit = { Text(it) },
) {
    // For improved SEO, ensure the exported HTML contains the full text (disable the typing animation).
    var currentText by remember { mutableStateOf(fullText) }

    // Avoid applying the animation while exporting.
    if (!AppGlobals.isExporting) {
        LaunchedEffect(fullText, isTypingForward) {

            currentText = if (isTypingForward) "" else fullText // Starting text
            delayDuration?.let { delay(it) }

            val range = if (isTypingForward) 1..fullText.length else fullText.indices.reversed()
            for (i in range) {
                currentText = fullText.substring(0, i)
                delay(typingSpeedDuration)
            }
            onTypingFinished()
        }
    }

    content(currentText)
}

@Composable
fun TypingTextAnimationWithLoading(
    fullText: String,
    typingSpeedDuration: Duration,
    modifier: Modifier = Modifier,
    delayDuration: Duration? = null,
    isTypingForward: Boolean = true,
    onTypingFinished: () -> Unit = {},
    content: @Composable (currentText: String) -> Unit = { Text(it) },
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .display(DisplayStyle.Flex)
            .flexWrap(FlexWrap.Wrap)
            .alignItems(AlignItems.Center)
            .then(modifier)
    ) {
        var isAwaiting by remember { mutableStateOf(true) }

        TypingTextAnimation(
            fullText = fullText,
            typingSpeedDuration = typingSpeedDuration,
            delayDuration = delayDuration,
            isTypingForward = isTypingForward,
            onTypingFinished = { isAwaiting = false; onTypingFinished() },
        ) { currentText -> content(currentText) }

        // TODO: Need visual improvements, the loading circle location should be next to the text.
        if (isAwaiting) {
            LoadingCircle(
                modifier = Modifier.margin(left = 12.px)
                    .alignSelf(AlignSelf.Center)
            )
        }
    }
}
