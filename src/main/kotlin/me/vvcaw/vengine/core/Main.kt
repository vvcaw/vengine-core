package me.vvcaw.vengine.core

import me.vvcaw.vengine.core.elements.Ellipse
import me.vvcaw.vengine.core.elements.be
import me.vvcaw.vengine.core.execution.scene

fun main() {
    val scene = scene {
        wait(1.0)

        val e = Ellipse(100F, 100F)

        e.animate(
            Ellipse::x be 200F,
            Ellipse::y be 200F,
            duration = 2.0
        )

        wait(1.0)
    }

    scene.render()
}