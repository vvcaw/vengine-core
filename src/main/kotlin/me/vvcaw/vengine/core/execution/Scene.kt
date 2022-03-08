package me.vvcaw.vengine.core.execution

import me.vvcaw.vengine.core.animation.WaitAnimation
import me.vvcaw.vengine.core.animation.Animation
import me.vvcaw.vengine.core.animation.AnimationBuilder
import processing.core.PApplet

class Scene {
    private var duration: Double = 0.0
    private var fps: Float = 60f
    private val elements: MutableMap<Int, MutableList<Animation>> = mutableMapOf()

    /**
     * ## wait
     *
     * Sleeps the animation at the given step.
     *
     * @param duration Time to sleep for.
     */
    fun wait(duration: Double) {
        // Update duration of animation
        this.duration += duration

        // Add wait animation to animation map
        val list = elements.getOrElse((duration * fps).toInt()) { mutableListOf() }
        list.add(WaitAnimation(duration))
    }

    fun animate(animationBuilder: AnimationBuilder<*, *>) {
        TODO("Not yet implemented!")
    }

    /**
     * ## render
     *
     * Renders the current scene and builds a processing scene from it.
     */
    fun render() {
        // Arguments for processing
        val appletArgs = listOf("Test")

        // Create app & run sketch
        val applet = App(fps, elements, duration)
        PApplet.runSketch(appletArgs.toTypedArray(), applet)
    }
}