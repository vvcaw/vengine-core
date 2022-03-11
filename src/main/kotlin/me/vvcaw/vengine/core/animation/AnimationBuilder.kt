package me.vvcaw.vengine.core.animation

import me.vvcaw.vengine.core.animation.util.Easing
import me.vvcaw.vengine.core.elements.Element
import me.vvcaw.vengine.core.elements.PropertyPair

interface AnimationBuilder<T : Element, V> {
    fun withEasing(easing: Easing): AnimationBuilder<T, V>
    fun build(): PropertyAnimation<T, V>
}