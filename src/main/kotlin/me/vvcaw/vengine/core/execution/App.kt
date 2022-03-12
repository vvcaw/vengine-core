package me.vvcaw.vengine.core.execution

import me.vvcaw.vengine.core.animation.Animation
import me.vvcaw.vengine.core.animation.PropertyAnimation
import me.vvcaw.vengine.core.elements.Element
import processing.core.PApplet

// ! fps is a fixed value, whereas frameRate is fluctuating (probably an real estimation), think about what to use where
class App(
    private val fps: Float,
    private val animations: MutableMap<Int, MutableList<Animation>>,
    private val duration: Double
) : PApplet() {
    private val activeAnimations: MutableList<Pair<Int, Animation>> = mutableListOf()
    private val activeElements: MutableList<Element> = mutableListOf()

    override fun setup() {
        // This needs to be called after surface is created
        frameRate(fps)
    }

    override fun settings() {
        // This needs to be called before surface is created
        size(500, 500)
    }

    override fun draw() {
        // Set background color
        background(64)

        // Update list and map of elements in scene
        updateElements()

        // Render all active elements
        renderFrame()

        // Update frame count
        // TODO: Think about whether this should be set by this code
        frameCount = updateFrameCount()
    }

    private fun updateElements() {
        // Remove animations & their associated elements
        activeAnimations.removeIf { (startFrame, animation) ->
            // Match on different types of animations for removal
            when (animation) {
                is PropertyAnimation<*, *> -> {
                    // The (+1) indicated, that it was already rendered last frame
                    if ((frameCount - startFrame) - (animation.duration * fps) > 0) {
                        activeElements.remove(animation.elementPointer)

                        // Remove from list
                        return@removeIf true
                    }

                    // Don't remove from list if animation is not done
                    false
                }
                // is RemoveAnimation -> return@onEach
                // All other animations are only active for one frame and can therefore be removed
                // TODO: Debate whether it is worth even adding them to the list in the first place
                else -> true
            }
        }

        // Get and add new animations
        activeAnimations.addAll(animations[frameCount]?.map { Pair(frameCount, it) } ?: listOf())

        // Evaluate all active animations
        activeAnimations.forEach { (startFrame, animation) ->
            when (animation) {
                is PropertyAnimation<*, *> -> {
                    // Either find the element pointer in list or add it to the list
                    activeElements.find { it == animation.elementPointer } ?: run {
                        activeElements.add(animation.elementPointer)

                        // TODO: Set all values to start values of the first frame where element is added
                    }

                    // Update values based on percentage
                    animation.updateValues((frameCount - startFrame) / (animation.duration * fps))
                }
                // is RemoveAnimation -> // Remove item from render list
                else -> TODO("Not yet implemented!")
            }
        }
    }

    private fun renderFrame() {
        // Render each active element
        activeElements.forEach { it.render(this) }
    }

    // Reset frames (restart animation) if animation is done
    private fun updateFrameCount() = if (frameCount >= fps * duration) 0 else frameCount
}