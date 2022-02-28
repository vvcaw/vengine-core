package me.vvcaw.vengine.core.animation

import me.vvcaw.vengine.core.animation.util.Easing
import me.vvcaw.vengine.core.elements.Element

data class Animation<T : Element>(val start: T, val end: T, val duration: Double, val easing: Easing)