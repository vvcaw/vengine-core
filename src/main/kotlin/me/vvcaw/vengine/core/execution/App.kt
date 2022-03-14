package me.vvcaw.vengine.core.execution

import me.vvcaw.vengine.core.animation.Animation
import me.vvcaw.vengine.core.animation.PropertyAnimation
import me.vvcaw.vengine.core.elements.Element
import processing.core.PApplet

// TODO: fps is a fixed value, whereas frameRate is fluctuating (probably a real estimation), think about what to use where
class App(
    private val fps: Float,
    private val animations: MutableMap<Int, MutableList<Animation>>,
    private val duration: Double
) : PApplet() {
    private val activeAnimations: MutableList<Pair<Int, Animation>> = mutableListOf()
    private val activeElements: MutableMap<Element, Boolean> = mutableMapOf()

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

        updateAnimationsAndElementState()

        renderFrame()

        // TODO: Think about whether this should be set by this code, or there should be a different local variable to keep track of frames
        frameCount = updateFrameCount()
    }

    private fun updateAnimationsAndElementState() {
        // Remove animations & their associated elements
        activeAnimations.removeIf { (startFrame, animation) ->
            // Match on different types of animations for removal
            when (animation) {
                is PropertyAnimation<*, *> -> {
                    if ((frameCount - startFrame) - (animation.duration * fps) > 0) {
                        // Deactivate element in hierarchy
                        activeElements[animation.elementPointer] = false

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

        // Get and add new animations that start this frame
        activeAnimations.addAll(animations[frameCount]?.map { Pair(frameCount, it) } ?: listOf())

        // Evaluate all active animations
        activeAnimations.forEach { (startFrame, animation) ->
            when (animation) {
                is PropertyAnimation<*, *> -> {
                    // Activate element in hierarchy
                    activeElements[animation.elementPointer] = true

                    val animationProgress = (frameCount - startFrame) / (animation.duration * fps)
                    animation.updateValues(animationProgress)
                }
                // is RemoveAnimation -> // Remove item from render list
                else -> TODO("Not yet implemented!")
            }
        }
    }

    private fun renderFrame() {
        activeElements.filterValues { it }.forEach { (element, _) -> element.render(this) }
    }

    // Reset frames (restart animation) if animation is done
    // TODO: Set all values to start values and clean list after one animation iteration is done
    private fun updateFrameCount() = if (frameCount >= fps * duration) 0 else frameCount
}