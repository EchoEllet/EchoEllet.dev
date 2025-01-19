package dev.kernelcraft.site.components.widgets.icons

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.UserSelect
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.draggable
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.userSelect
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import dev.kernelcraft.site.constants.Res
import org.jetbrains.compose.web.css.cssRem

@Composable
private fun SvgIcon(src: String, description: String, modifier: Modifier) {
    Image(
        modifier = Modifier.size(1.125.cssRem)
            .draggable(false)
            .userSelect(UserSelect.None)
            .then(modifier),
        src = src,
        description = description,
    )
}

@Composable
fun GitHubIcon(
    modifier: Modifier = Modifier,
    colorMode: ColorMode = ColorMode.current
) {
    SvgIcon(
        modifier = modifier,
        src = if (colorMode.isDark) Res.GITHUB_MARK_WHITE else Res.GITHUB_MARK,
        description = "GitHub Invertocat Logo",
    )
}
