package me.vvcaw.vengine.core

import me.vvcaw.vengine.core.execution.App
import me.vvcaw.vengine.core.execution.scene
import processing.core.PApplet

fun main() {
    val scene = scene {
        wait(1.0)
    }

    scene.render()
}