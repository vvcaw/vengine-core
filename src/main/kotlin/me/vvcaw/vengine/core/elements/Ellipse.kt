package me.vvcaw.vengine.core.elements

import processing.core.PApplet

class Ellipse : Element() {
    fun render(app: PApplet) {
        app.ellipse(app.mouseX.toFloat(), app.mouseY.toFloat(), 20F, 20F)
    }
}