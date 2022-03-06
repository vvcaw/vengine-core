package me.vvcaw.vengine.core.elements

import processing.core.PApplet

interface Element {
    // TODO: Implement function to compare two states of an object
    fun render(app: PApplet)
    fun copyElementData(element: Element)
}