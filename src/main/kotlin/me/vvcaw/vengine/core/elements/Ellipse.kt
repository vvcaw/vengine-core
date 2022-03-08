package me.vvcaw.vengine.core.elements

import me.vvcaw.vengine.core.animation.AnimationBuilder
import processing.core.PApplet

data class Ellipse(var test: String, var x: Double) : Element {
    // This is the pointer to be passed to builder and to be later used for animation,
    // we do not use this instance, as it has mutated properties after the scripting process
    private val pointer = this.copy()

    /**
     * ## render
     *
     * Renders the object (Warning: This is not intended to be used while scripting and should never be called).
     *
     * @param app PApplet to render on.
     */
    override fun render(app: PApplet) {
        app.ellipse(app.mouseX.toFloat(), app.mouseY.toFloat(), 20F, 20F)
    }

    /**
     * ## animate
     *
     * Animates the objects values to the given new values.
     *
     * @param changes list of changes to be animated.
     */
    fun <T> animate(vararg changes: PropertyPair<Ellipse, in T>): AnimationBuilder<Ellipse, T> {
        // Build animation
        val builder = AnimationBuilder(changes.toList(), pointer)

        // Update members, so that animation works when building the file
        changes.forEach { change ->

            // This works, as the PropertyPair and this method signature confirm, that the value is indeed valid
            @Suppress("UNCHECKED_CAST")
            change.property.set(this, change.value as T)
        }

        return builder
    }
}