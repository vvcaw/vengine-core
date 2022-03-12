package me.vvcaw.vengine.core.elements

import processing.core.PApplet
import kotlin.reflect.KMutableProperty1

interface Element {
    // TODO: Implement function to compare two states of an object
    fun render(app: PApplet)
}

data class PropertyPair<A : Element, B>(val property: KMutableProperty1<A, B>, val value: B)

infix fun <A : Element, B> KMutableProperty1<A, B>.be(to: B): PropertyPair<A, B> {
    return PropertyPair(this, to)
}