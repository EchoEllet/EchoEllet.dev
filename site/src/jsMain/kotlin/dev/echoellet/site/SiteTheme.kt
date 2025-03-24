package dev.echoellet.site

import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.link

private val CHARCOAL get() = Color.rgb(0x424b54)
private val ALABASTER get() = Color.rgb(0xe8ebe4)
private val RAISIN_BLACK get() = Color.rgb(0x1e1e24)
private val CHARCOAL_GRAY get() = Color.rgb(0x333333)
private val LIGHT_GRAY get() = Color.rgb(0xF5F5F5)
private val MY_BACKGROUND get() = Color.rgb(0x111111)
private val THIS_COLOR_IS_SAVED_FOR_LATER get() = Color.rgb(0x1E1E1E)

/**
 * @property nearBackground A useful color for a container that should slightly differentiate itself from the background.
 */
class SitePalette(
    val background: Color,
    val nearBackground: Color,
    val font: Color,
    val brand: Brand,
) {
    class Brand(
        val primary: Color,
        val accent: Color = Color.rgb(0xFF9900),
    )
}

private object SitePalettes {
    val light = SitePalette(
        nearBackground = Color.rgb(0xF0F0F0),
        brand = SitePalette.Brand(
            primary =  Color.rgb(0x0066cc), // Deep Blue
        ),
        background = Color.rgb(0xFAFAFA), // Light Background
        font = Color.rgb(0x333333),
    )
    val dark = SitePalette(
        nearBackground = Color.rgb(0x242424),
        brand = SitePalette.Brand(
            primary = Color.rgb(0x007FFF) // Bright Blue
        ),
        background = Color.rgb(0x1A1A1A), // Dark Background
        font = Colors.White,
    )
}

fun ColorMode.toSitePalette(): SitePalette = when (this) {
    ColorMode.LIGHT -> SitePalettes.light
    ColorMode.DARK -> SitePalettes.dark
}

@InitSilk
fun initTheme(ctx: InitSilkContext) {
    ctx.theme.palettes.light.apply {
        val sitePalette = SitePalettes.light

        background = sitePalette.background
        color = sitePalette.font
        link.apply {
            val linkColor = sitePalette.brand.primary
            default = linkColor; visited = linkColor
        }
    }
    ctx.theme.palettes.dark.apply {
        val sitePalette = SitePalettes.dark

        background = sitePalette.background
        color = sitePalette.font
        link.apply {
            val linkColor = sitePalette.brand.primary
            default = linkColor; visited = linkColor
        }
    }
}
