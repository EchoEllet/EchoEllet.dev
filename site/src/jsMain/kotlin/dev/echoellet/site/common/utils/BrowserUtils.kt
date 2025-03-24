package dev.echoellet.site.common.utils

import kotlinx.browser.window
import org.w3c.dom.Navigator

/**
 * Returns the name of the browser from the user agent.
 * Supported browsers include Firefox, Chrome, Safari, Edge, and Internet Explorer.
 *
 * @return The browser name (e.g., "firefox", "chrome"), or `null` if not recognized.
 */
fun Navigator.browserName() =
    Regex("firefox|chrome|safari|edge|msie|trident").find(window.navigator.userAgent.lowercase())
        ?.value
