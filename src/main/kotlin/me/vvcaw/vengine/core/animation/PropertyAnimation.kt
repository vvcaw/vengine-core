package me.vvcaw.vengine.core.animation

import me.vvcaw.vengine.core.animation.util.Easing
import me.vvcaw.vengine.core.elements.Element
import me.vvcaw.vengine.core.elements.PropertyPair

data class PropertyAnimation<T : Element, V>(
    private val changes: List<PropertyPair<T, in V>>,
    val elementPointer: T,
    val duration: Double,
    private val easing: Easing
) : Animation {
    fun updateValues(progress: Double) {
        TODO("Not yet implemented!")
    }
}
// TODO: Implement function to compare two states of an object and use start or end to do it (start.compare(start, end))