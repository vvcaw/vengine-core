package me.vvcaw.vengine.core.animation

import me.vvcaw.vengine.core.animation.util.Easing
import me.vvcaw.vengine.core.elements.Element
import me.vvcaw.vengine.core.elements.PropertyPair

class DynamicAnimationBuilder<T : Element, V>(
    private val changes: List<PropertyPair<T, in V>>,
    private val elementPointer: T
) : AnimationBuilder<T, V> {
    private var easing = Easing.LINEAR
    private var duration = 1.0

    fun withDuration(duration: Double): DynamicAnimationBuilder<T, V> {
        this.duration = duration
        return this
    }

    override fun withEasing(easing: Easing): DynamicAnimationBuilder<T, V> {
        this.easing = easing
        return this
    }

    override fun build(): PropertyAnimation<T, V> {
        return PropertyAnimation(changes, elementPointer, duration, easing)
    }
}