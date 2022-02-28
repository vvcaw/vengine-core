package me.vvcaw.vengine.core.execution

import me.vvcaw.vengine.core.elements.Ellipse
import processing.core.PApplet

class Scene : PApplet() {
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