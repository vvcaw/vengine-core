package me.vvcaw.vengine.core.elements

import me.vvcaw.vengine.core.animation.BlockingAnimationBuilder
import me.vvcaw.vengine.core.execution.Scene
import processing.core.PApplet

data class Ellipse(var test: String, var x: Double) : Element {
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
        app.ellipse(app.mouseX.toFloat(), app.mouseY.toFloat(), 20F, 20F)
    }

    /**
     * ## animate
     *
     * Animates the objects values to the given new values. This animation function is **blocking**, therefore the whole animation waits until this animation is finished.
     *
     * @param duration Duration of animation.
     * @param changes List of changes to be animated.
     */
    fun <T> animate(
        vararg changes: PropertyPair<Ellipse, in T>,
        duration: Double = 1.0
    ): BlockingAnimationBuilder<Ellipse, T> {
        // Build blocking animation
        val builder = BlockingAnimationBuilder(changes.toList(), getOrCreatePointer(), duration)

        // Update members, so that animation works when building the file
        changes.forEach { change ->

            // This works, as the PropertyPair and this method signature confirm, that the value is indeed valid
            @Suppress("UNCHECKED_CAST")
            change.property.set(this, change.value as T)
        }

        // Add builder to scene
        scene.addBlockingAnimation(builder)

        return builder
    }

    // Get or create pointer for rendering in App class
    private fun getOrCreatePointer(): Ellipse {
        if (pointer == null) {
            pointer = this.copy()
        }
        return pointer as Ellipse
    }
}