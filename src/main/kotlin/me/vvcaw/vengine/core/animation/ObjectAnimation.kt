package me.vvcaw.vengine.core.animation

import me.vvcaw.vengine.core.animation.util.Easing
import me.vvcaw.vengine.core.elements.Element

data class ObjectAnimation<T : Element>(
    private val start: T,
    private val end: T,
    private val elementPointer: T,
    val duration: Double,
    private val easing: Easing
) : Animation
// TODO: Implement function to compare two states of an object and use start or end to do it (start.compare(start, end))