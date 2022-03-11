package me.vvcaw.vengine.core.execution

import me.vvcaw.vengine.core.animation.WaitAnimation
import me.vvcaw.vengine.core.animation.Animation
import me.vvcaw.vengine.core.animation.AnimationBuilder
import processing.core.PApplet

class Scene {
    private var duration: Double = 0.0
    private var fps: Float = 60f
    private val frameToAnimation: MutableMap<Int, MutableList<Animation>> = mutableMapOf()

    init {
        // Assign a value to CURRENT_SCENE
        CURRENT_SCENE = this
    }

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
        val list = frameToAnimation.getOrElse((duration * fps).toInt()) { mutableListOf() }
        list.add(WaitAnimation(duration))
    }

    /**
     * ## addBlockingAnimation
     *
     * Adds a given **blocking** animation builder to the scene.
     *
     * @param animationBuilder Builder to add to the scene.
     */
    fun addBlockingAnimation(animationBuilder: AnimationBuilder<*, *>) {
        // Update duration of animation

        TODO("Not yet implemented!")
    }

    /**
     * ## addDynamicAnimation
     *
     * Adds a given **dynamic** animation builder to the scene (it does not add to the duration of the animation directly).
     *
     * @param animationBuilder Builder to add to the scene.
     */
    fun addDynamicAnimation(animationBuilder: AnimationBuilder<*, *>) {
        // Don't update duration of animation
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
        val applet = App(fps, frameToAnimation, duration)
        PApplet.runSketch(appletArgs.toTypedArray(), applet)
    }

    companion object {
        var CURRENT_SCENE: Scene? = null
        fun getCurrentlyActiveScene(): Scene? = CURRENT_SCENE
    }
}