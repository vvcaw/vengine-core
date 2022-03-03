package me.vvcaw.vengine.core.elements

import processing.core.PApplet

interface Element {
    fun render(app: PApplet)
}