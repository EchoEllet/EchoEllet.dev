package dev.echoellet.site.common.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.AnimationIterationCount
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.animation
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.transform
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.animation.toAnimation
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.dom.Span

val LoadingCirclePopKeyframes = Keyframes {
    each(0.percent, 100.percent) { Modifier.transform { scale(1) } }
    50.percent { Modifier.transform { scale(1.125) } }
}

@Composable
fun LoadingCircle(modifier: Modifier = Modifier) {
    Span(
        Modifier.size(16.px)
            .backgroundColor(
                if (ColorMode.current.isDark) Color.rgb(0xEEEEEE)
                else Color.rgb(0x999999)
            )
            .borderRadius(r = 50.percent)
            .transition(
                Transition.of("width", 100.ms),
                Transition.of("height", 100.ms),
                Transition.of("border-radius", 100.ms)
            )
            .animation(
                LoadingCirclePopKeyframes.toAnimation(
                    duration = 1.35.s,
                    timingFunction = AnimationTimingFunction.EaseInOut,
                    iterationCount = AnimationIterationCount.Infinite,
                )
            )
            .then(modifier)
            .toAttrs()
    )
}
