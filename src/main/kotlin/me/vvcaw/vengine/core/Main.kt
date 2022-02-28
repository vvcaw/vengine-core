package me.vvcaw.vengine.core

import processing.core.PApplet

fun main() {
    val appletArgs = listOf("Test")
    val applet = Test()
    PApplet.runSketch(appletArgs.toTypedArray(), applet)
}

class Test : PApplet() {
    // TODO: Have this as scene object to save state, when building provide list, that maps frames to objects to be rendered, then somehow call render methods, like ellipse in (object.draw())
    // TODO: Take Keyframe code from last project and adopt here, keep hash map of keyframes, where key is start frame and keyframe saves duration, then keep list of currently active keyframes and update accordingly
    
    override fun settings() {
        size(500, 500)
    }

    override fun draw() {
        background(64)
        val e = Ellipse()
        e.render(this)
    }
}

class Ellipse {
    fun render(app: PApplet) {
        app.ellipse(app.mouseX.toFloat(), app.mouseY.toFloat(), 20F, 20F)
    }
}