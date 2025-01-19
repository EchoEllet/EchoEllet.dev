package dev.kernelcraft.site.components.widgets.terminal.body

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.lineHeight
import com.varabyte.kobweb.compose.ui.modifiers.maxHeight
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.whiteSpace
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.selection
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.browser.document
import kotlinx.dom.appendText
import kotlinx.dom.clear
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul

val TerminalBodyTextSelectionStyle = CssStyle {
    selection {
        Modifier.backgroundColor(Color.rgb(0x3E4E59))
            .color(Color.rgb(0xE8E8E8))
    }
}

// TODO: Finish the terminal

// TODO: Make use of this instead of id: https://github.com/varabyte/kobweb#elementrefscope-and-raw-html-elements. Avoid requiring Modifier and passing the id.
private const val TERMINAL_OUTPUT_ID = "terminal-output"
private const val TERMINAL_INPUT_ID = "terminal-inline-input"

@Composable
fun TerminalBody(modifier: Modifier = Modifier) {
    val ctx = rememberPageContext()

    // TODO: Remove the Modifier if not used, also figure out why .then(modifier) is not at the end
    Div(
        attrs = TerminalBodyTextSelectionStyle.toModifier()
            .padding(15.px)
            .maxHeight(400.px)
            .overflow(Overflow.Auto)
            .fillMaxWidth()
            .cursor(Cursor.Text)
            .whiteSpace(WhiteSpace.PreWrap)
            .lineHeight(1.5)
            .then(modifier)
            .color(Colors.White) // TODO: The terminal is currently always dark
            .display(DisplayStyle.InlineBlock)
//            .backgroundColor(Colors.DarkGray) // TODO: Hide this, ensure to maximize the size first always even if the output is clear.
            // TODO: Request focus to input on click anywhere in the input.
//            .onClick { (document.getElementById(TERMINAL_INPUT_ID) as HTMLElement).focus() }
            .toAttrs()
    ) {
        TerminalOutput(Modifier.id(TERMINAL_OUTPUT_ID))
        TerminalInput(Modifier.id(TERMINAL_INPUT_ID)) { onInput(it, ctx) }
    }
}

@Composable
private fun TerminalOutput(modifier: Modifier) {
    Div(attrs = modifier.toAttrs()) {
        Text("Welcome to the ")
        Link("https://www.warp.dev/best-linux-terminal") { Text("Interactive Terminal") }
        Text(" \uD83C\uDF1F")
        Text(
            """

-------------------
| 𝗛𝗲𝗹𝗹𝗼, 𝗪𝗼𝗿𝗹𝗱!  |
-------------------

            """.trimIndent()
        )
        Ul {
            Li { Text("Do this") }
            Li { Text("Do that") }
            Li { Text("Here") }
        }
    }
}

private fun onInput(command: String, pageCtx: PageContext) {
    // TODO: Create a utility to require finding a element by id with error message.
    val terminalOutputElement = document.getElementById(TERMINAL_OUTPUT_ID)
        ?: error("Could not find the terminal output element with the id '$TERMINAL_OUTPUT_ID'.")

    val terminalInputElement = document.getElementById(TERMINAL_INPUT_ID)
        ?: error("Could not find the terminal input element with the id '$TERMINAL_INPUT_ID'.")

    val terminalInputElementClone = terminalInputElement.cloneNode(deep = true)
    val terminalInputCursor = terminalInputElementClone.lastChild
        ?: error("Could not find the terminal cursor of the clone input element.")

    terminalInputElementClone.removeChild(terminalInputCursor) // Remove the terminal cursor from the clone.
    terminalOutputElement.appendChild(terminalInputElementClone)

    if (command.isBlank()) {
        return
    }

    // TODO: This implementation is pretty broken, doesn't handle command with white spaces in quotes, doesn't handle options and args correctly.
    val commandParts = command.split(" ")
    val commandName = commandParts.firstOrNull()
    val commandArgs = commandParts.drop(1)

    when (commandName) {
        "clear" -> terminalOutputElement.clear()
        "help" -> {
            val ul = document.createElement("ul").apply {
                appendChild(document.createElement("li").apply { innerHTML = """Hello <a class="silk-link" href="/resume">Hi</a>""" })
            }
            terminalOutputElement.appendChild(ul)
        }
        "navigate" -> {
            pageCtx.router.tryRoutingTo(commandArgs.firstOrNull() ?: "/")
        }
        else -> {
            terminalOutputElement.appendText("${commandName.orEmpty()}: command not found")
        }
    }
}
