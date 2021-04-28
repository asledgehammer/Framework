@file:Suppress("unused")

package com.asledgehammer.event

/**
 * TODO: Document.
 *
 * @author Jab
 */
class CancelableEvent : Event() {

    /**
     * TODO: Document.
     */
    var cancelled = false

    /**
     * TODO: Document.
     */
    fun cancel() {
        cancelled = true
    }
}
