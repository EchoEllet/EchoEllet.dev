package dev.echoellet.site.home.terminal

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.AnimationIterationCount
import com.varabyte.kobweb.compose.css.PointerEvents
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.animation
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.pointerEvents
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.animation.toAnimation
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.StepPosition
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.dom.Span

// TODO: Finish the terminal cursor

val TerminalCursorBlinkKeyframes = Keyframes {
    each(0.percent, 50.percent) { Modifier.opacity(1) }
    each(50.01.percent, 100.percent) { Modifier.opacity(0) }
}

@Composable
fun TerminalCursor(modifier: Modifier = Modifier) {
    Span(
        attrs = Modifier
            .width(8.px)
            .height(16.px)
            .backgroundColor(Colors.White)
            .animation(
                TerminalCursorBlinkKeyframes.toAnimation(
                    duration = 1.s,
                    timingFunction = AnimationTimingFunction.steps(2, StepPosition.Start),
                    iterationCount = AnimationIterationCount.Infinite,
                )
            )
            .pointerEvents(PointerEvents.None)
            .then(modifier)
            .toAttrs()
    )
}