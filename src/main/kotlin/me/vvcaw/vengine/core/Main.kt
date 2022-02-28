package me.vvcaw.vengine.core

import me.vvcaw.vengine.core.execution.Scene
import processing.core.PApplet

fun main() {
    val appletArgs = listOf("Test")
    val applet = Scene()
    PApplet.runSketch(appletArgs.toTypedArray(), applet)
}