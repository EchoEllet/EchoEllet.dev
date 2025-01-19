package dev.kernelcraft.site.utils

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLElement

fun HTMLElement.selectContentsToEnd() {
    val element = this
    require(element.contentEditable.toBooleanStrictOrNull() == true) { "Expected the element '${this.nodeName}' to be content editable." }

    val range = document.createRange()
    range.selectNodeContents(element)
    range.collapse(false)

    val selection = window.asDynamic().getSelection()
    selection.removeAllRanges()
    selection.addRange(range)
}