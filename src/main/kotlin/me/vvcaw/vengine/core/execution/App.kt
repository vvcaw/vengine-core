package me.vvcaw.vengine.core.execution

import me.vvcaw.vengine.core.animation.Animation
import me.vvcaw.vengine.core.elements.Element
import me.vvcaw.vengine.core.elements.Ellipse
import processing.core.PApplet

class App(
    private val fps: Float,
    private val elements: MutableMap<Int, MutableList<Animation>>,
    private val duration: Double
) : PApplet() {
    private val activeElements: MutableList<Pair<Int, Animation>> = mutableListOf()

    override fun settings() {
        size(500, 500)
        frameRate(fps)
    }

    override fun draw() {
        background(64)
        val e = Ellipse()
        e.render(this)
        // Update list and map of elements in scene
        updateElements()

        // Render all active elements
        render()

        // Reset animation if full duration is reached
        frameCount = updateFrameCount()
    }

    private fun updateElements() {
        // Remove and skip all elements, whose animations stopped
        activeElements.removeIf { (startFrame, _) -> (frameCount - startFrame) - (duration * frameRate) <= 0 }

        // Get and add new elements
        //activeElements.addAll(elements[frameCount]?.map { Pair(frameCount, it) } ?: return)
    }

    private fun render() {
        // Render each active element
        activeElements.forEach { (startFrame, animation) ->
            // Get current animation state based on percentage
            //val animatedElement: Element =
            //  animation.getAnimationState((frameCount - startFrame) / (duration * frameRate))

            // Render element
            //animatedElement.render(this)
        }
    }

    // Reset frames (restart animation) if animation is done
    private fun updateFrameCount() = if (frameCount >= frameRate * duration) 0 else frameCount
}