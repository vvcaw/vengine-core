package me.vvcaw.vengine.core.elements

import me.vvcaw.vengine.core.animation.Animation
import me.vvcaw.vengine.core.animation.AnimationBuilder
import processing.core.PApplet

data class Ellipse(private var test: String) : Element {
    override fun render(app: PApplet) {
        app.ellipse(app.mouseX.toFloat(), app.mouseY.toFloat(), 20F, 20F)
    }

    override fun copyElementData(element: Element) {
        // Cast to ellipse
        val ellipse = element as Ellipse

        this.test = ellipse.test
    }

    // TODO: Make this an override method for clarity
    fun animateTo(target: Ellipse): Animation {
        // Build animation
        val builder = AnimationBuilder(this.copy(), target.copy(), this)

        // Update members, so that animation works when building the file
        test = target.test

        return builder.build()
    }
}