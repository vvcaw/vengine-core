package me.vvcaw.vengine.core.animation

import me.vvcaw.vengine.core.animation.util.Easing
import me.vvcaw.vengine.core.elements.Element
import me.vvcaw.vengine.core.elements.PropertyPair

class BlockingAnimationBuilder<T : Element, V>(
    private val changes: List<PropertyPair<T, in V>>,
    private val elementPointer: T,
    private var duration: Double
) : AnimationBuilder<T, V> {
    private var easing = Easing.LINEAR

    override fun withEasing(easing: Easing): BlockingAnimationBuilder<T, V> {
        this.easing = easing
        return this
    }

    override fun build(): PropertyAnimation<T, V> {
        return PropertyAnimation(changes, elementPointer, duration, easing)
    }
}