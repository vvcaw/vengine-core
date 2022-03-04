package me.vvcaw.vengine.core.execution

import me.vvcaw.vengine.core.animation.Animation
import me.vvcaw.vengine.core.animation.WaitAnimation
import me.vvcaw.vengine.core.elements.Element
import me.vvcaw.vengine.core.elements.Ellipse
import processing.core.PApplet

class App(
    private val fps: Float,
    private val animations: MutableMap<Int, MutableList<Animation>>,
    private val duration: Double
) : PApplet() {
    private val activeAnimations: MutableList<Pair<Int, Animation>> = mutableListOf()
    private val activeElements: Map<Element, Boolean> = mutableMapOf()

    override fun setup() {
        // This needs to be called after surface is created
        frameRate(fps)
    }

    override fun settings() {
        // This needs to be called before surface is created
        size(500, 500)
    }

    override fun draw() {
        background(64)
        val e = Ellipse()
        e.render(this)
        // Update list and map of elements in scene
        updateElements()

        // Render all active elements
        renderFrame()

        // Update frame count
        frameCount = updateFrameCount()
        println("Current frame: $frameCount")
    }

    private fun updateElements() {
        // Remove and skip all elements, whose animations stopped
        activeAnimations.removeIf { (startFrame, _) -> (frameCount - startFrame) - (duration * frameRate) <= 0 }

        // Get and add new elements
        activeAnimations.addAll(animations[frameCount]?.map { Pair(frameCount, it) } ?: return)

        // Evaluate all active animations
        activeAnimations.forEach { (_, animation) ->
            when (animation) {
                is WaitAnimation -> return
                else -> TODO("Not yet implemented!")
            }
        }
    }

    private fun renderFrame() {
        // Render each active element
        activeElements.keys.forEach { it.render(this) }
    }

    // Reset frames (restart animation) if animation is done
    private fun updateFrameCount() = if (frameCount >= frameRate * duration) 0 else frameCount
}