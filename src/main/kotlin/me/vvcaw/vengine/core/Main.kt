package me.vvcaw.vengine.core

import me.vvcaw.vengine.core.elements.*
import me.vvcaw.vengine.core.execution.scene

fun main() {
    val scene = scene {
        wait(1.0)

        val e = Ellipse("Hallo", 1.0)

        e.animate(
            Ellipse::test to "1.0",
            Ellipse::x to 1.0,
            Ellipse::x to 1.0
        )
            .withDuration(1.0)
    }

    scene.render()
}