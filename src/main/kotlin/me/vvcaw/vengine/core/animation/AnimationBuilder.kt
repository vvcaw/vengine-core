package me.vvcaw.vengine.core.animation

import me.vvcaw.vengine.core.animation.util.Easing
import me.vvcaw.vengine.core.elements.Element
import me.vvcaw.vengine.core.elements.PropertyPair

class AnimationBuilder<T : Element, V>(
    private val changes: List<PropertyPair<T, in V>>,
    private val elementPointer: T
) {
    private var easing = Easing.LINEAR
    private var duration = 1.0

    fun withEasing(easing: Easing): AnimationBuilder<T, V> {
        this.easing = easing
        return this
    }

    fun withDuration(duration: Double): AnimationBuilder<T, V> {
        this.duration = duration
        return this
    }

    fun build(): PropertyAnimation<T, V> {
        return PropertyAnimation(changes, elementPointer, duration, easing)
    }
}