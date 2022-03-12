package me.vvcaw.vengine.core.elements

import me.vvcaw.vengine.core.animation.PropertyAnimation
import me.vvcaw.vengine.core.animation.util.Easing
import me.vvcaw.vengine.core.execution.Scene
import processing.core.PApplet

data class Ellipse(var x: Float, var y: Float) : Element {
    // This is the pointer to be passed to builder and to be later used for animation,
    // we do not use this instance, as it has mutated properties after the scripting process
    private var pointer: Ellipse? = null

    // Reference to current scene
    private var scene =
        Scene.getCurrentlyActiveScene() ?: throw IllegalStateException("Make sure that a scene is active!")

    /**
     * ## render
     *
     * Renders the object (Warning: This is not intended to be used while scripting and should never be called).
     *
     * @param app PApplet to render on.
     */
    override fun render(app: PApplet) {
        app.ellipse(x, y, 20F, 20F)
    }

    /**
     * ## animate
     *
     * Animates the objects values to the given new values. This animation function is **blocking**, therefore the whole animation waits until this animation is finished.
     *
     * @param changes List of changes to be animated.
     * @param duration Duration of animation.
     * @param easing Easing to use for animation.
     */
    fun <T> animate(
        vararg changes: PropertyPair<Ellipse, in T>, duration: Double = 1.0, easing: Easing = Easing.LINEAR
    ) {
        // Build blocking animation
        val animation = PropertyAnimation(changes.toList(), getOrCreatePointer(), duration, easing)

        // Update members, so that animation works when building the file
        changes.forEach { change ->

            // This works, as the PropertyPair and this method signature confirm, that the value is indeed valid
            @Suppress("UNCHECKED_CAST") change.property.set(this, change.value as T)
        }

        // Add builder to scene
        scene.addBlockingAnimation(animation)
    }

    // Get or create pointer for rendering in App class
    private fun getOrCreatePointer(): Ellipse {
        if (pointer == null) {
            pointer = this.copy()
        }
        return pointer as Ellipse
    }
}