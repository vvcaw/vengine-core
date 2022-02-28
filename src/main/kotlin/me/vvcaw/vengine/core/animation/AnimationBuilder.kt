package me.vvcaw.vengine.core.animation

import me.vvcaw.vengine.core.animation.util.Easing
import me.vvcaw.vengine.core.elements.Element

class AnimationBuilder(private val startValue: Element, private val endValue: Element) {
    private var easing = Easing.LINEAR
    private var duration = 1.0

    fun withEasing(easing: Easing): AnimationBuilder {
        this.easing = easing
        return this
    }

    fun withDuration(duration: Double): AnimationBuilder {
        this.duration = duration
        return this
    }

    fun build(): Animation<Element> {
        return Animation(startValue, endValue, duration, easing)
    }
}