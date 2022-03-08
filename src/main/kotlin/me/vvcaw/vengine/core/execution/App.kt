package me.vvcaw.vengine.core.execution

import me.vvcaw.vengine.core.animation.Animation
import me.vvcaw.vengine.core.animation.PropertyAnimation
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
        background(64)
        val e = Ellipse("asdf", 1.0)
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
        // Remove animations & their associated elements
        activeAnimations.removeAll(
            activeAnimations.filter { (startFrame, _) ->
                (frameCount - startFrame) - (duration * frameRate) <= 0
            }
                .onEach { (_, animation) ->
                    when (animation) {
                        is WaitAnimation -> return@onEach
                        is PropertyAnimation<*, *> -> return@onEach //activeElements.remove(animation.elementPointer)
                        // is RemoveAnimation -> return@onEach
                        else -> TODO("Not yet implemented!")
                    }
                }
        )

        // Get and add new animations
        activeAnimations.addAll(animations[frameCount]?.map { Pair(frameCount, it) } ?: listOf())

        // Evaluate all active animations
        activeAnimations.forEach { (startFrame, animation) ->
            when (animation) {
                is WaitAnimation -> return
                is PropertyAnimation<*, *> -> {
                    // Either find the element pointer in list or add it to the list
                    activeElements.find { it == animation.elementPointer } ?: run {
                        activeElements.add(animation.elementPointer)

                        // TODO: Set all values to start values of the first frame where element is added
                        //animation.elementPointer.copyElementData(animation.start)
                    }

                    // Update values based on percentage
                    animation.updateValues((animation.duration * frameRate - startFrame) / animation.duration)
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
    private fun updateFrameCount() = if (frameCount >= frameRate * duration) 0 else frameCount
}