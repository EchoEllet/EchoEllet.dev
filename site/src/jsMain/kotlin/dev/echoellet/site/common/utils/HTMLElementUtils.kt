package dev.echoellet.site.common.utils

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.HTMLElement

fun HTMLElement.selectContentsToEnd() {
    val element = this
    require(element.contentEditable.toBooleanStrictOrNull() == true) { "Expected the element '${this.nodeName}' to be content editable." }

    val range = document.createRange()
    range.selectNodeContents(element)
    range.collapse(false)

    // TODO: Avoid using asDynamic()
    val selection = window.asDynamic().getSelection()
    selection.removeAllRanges()
    selection.addRange(range)
}

fun Document.getElementByIdOrThrow(elementId: String, lazyElementName: () -> String): Element =
    this.getElementById(elementId)
        ?: error("Could not find the ${lazyElementName()} element with the id '$elementId'.")
