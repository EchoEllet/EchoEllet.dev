package dev.kernelcraft.site.components.widgets.terminal.body

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.WordBreak
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.caretColor
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.contentEditable
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.minWidth
import com.varabyte.kobweb.compose.ui.modifiers.onFocusIn
import com.varabyte.kobweb.compose.ui.modifiers.onFocusOut
import com.varabyte.kobweb.compose.ui.modifiers.onKeyDown
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.wordBreak
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import dev.kernelcraft.site.components.widgets.terminal.TerminalCursor
import dev.kernelcraft.site.utils.browserName
import dev.kernelcraft.site.utils.selectContentsToEnd
import kotlinx.browser.window
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLElement

private enum class NavigateHistoryDirection { Forward, Backward }

// TODO: Don't store those this way.

private var historyIndex = -1
private val commandHistory = mutableListOf<String>()

private val currentCommand get() = commandHistory[historyIndex]

@Composable
fun TerminalInput(
    modifier: Modifier,
    onInput: (command: String) -> Unit,
) {
    Div(attrs = modifier.toAttrs()) {
        val hostname = window.navigator.browserName() ?: "linux"
        SpanText("user@${hostname}:~\$ ", modifier = Modifier.color(Color.rgb(0x419640)))

        var isTerminalInputFocused by remember { mutableStateOf(false) }

        SpanText(
            "",
            modifier = Modifier
                .minHeight(1.em) // TODO: Why is this needed?
                .minWidth(4.px) // TODO: Why is this needed?
                .backgroundColor(Colors.Transparent)
                .caretColor(Colors.Transparent) // Hide native caret.
                .outline(style = LineStyle.None)
                .border(style = LineStyle.None)
                .color(Colors.White)
                .onFocusOut { isTerminalInputFocused = false }
                .onFocusIn { isTerminalInputFocused = true }
                .contentEditable(true)
                .wordBreak(WordBreak.BreakAll) // Ensure long text wraps to the next line, preventing the parent container width from expanding.
                .onKeyDown { event ->
                    val terminalInputElement = (event.target as HTMLElement)

                    terminalInputElement.selectContentsToEnd() // Ensure selection is always at the end.

                    when (event.key) {
                        "Enter" -> {
                            event.preventDefault() // Disable the 'Enter' key from creating a new line.
                            submitCommand(terminalInputElement, onInput)
                        }

                        "ArrowUp" -> {
                            event.preventDefault() // Prevent caret movement.
                            navigateHistory(NavigateHistoryDirection.Backward, terminalInputElement)
                        }

                        "ArrowDown" -> {
                            event.preventDefault() // Prevent caret movement.
                            navigateHistory(NavigateHistoryDirection.Forward, terminalInputElement)
                        }

                        "Tab" -> {
                            event.preventDefault() // Disable the 'Enter' key from focusing on the next element in the site.

                            // TODO: Implement command auto-complete.
                        }
                    }
                }
                .attrsModifier { spellCheck(false); attr("autocomplete", "off") },
        )
        // TODO: The terminal cursor is here, keep the cursor right after the current text correctly, sync it too.

        if (isTerminalInputFocused) {
            TerminalCursor(modifier = Modifier.display(DisplayStyle.InlineBlock))
        }
    }
}

private fun submitCommand(terminalInputElement: HTMLElement, onInput: (command: String) -> Unit) {
    val commandText = terminalInputElement.innerText.trim()
    onInput(commandText)

    terminalInputElement.innerText = "" // Clear the input field.

    commandHistory.add(commandText)
    historyIndex = commandHistory.size
}

// TODO: Play Linux Terminal sound when it's not possible to navigate

private fun navigateHistory(direction: NavigateHistoryDirection, terminalInputElement: HTMLElement) {
    when (direction) {
        NavigateHistoryDirection.Backward -> {
            val canNavigate = historyIndex > 0
            if (!canNavigate) return

            historyIndex--
            terminalInputElement.innerText = currentCommand
        }

        NavigateHistoryDirection.Forward -> {
            val canNavigate = historyIndex < commandHistory.lastIndex
            if (!canNavigate) {
                // Reset input to blank.
                historyIndex = commandHistory.size
                terminalInputElement.innerText = ""
                return
            }

            historyIndex++
            terminalInputElement.innerText = currentCommand
        }
    }

    // Move selection to the end.
    require(terminalInputElement.contentEditable.toBooleanStrictOrNull() == true) { "Expect the terminal input element to be content editable." }

    // TODO: Avoid using asDynamic()
    terminalInputElement.selectContentsToEnd()
}