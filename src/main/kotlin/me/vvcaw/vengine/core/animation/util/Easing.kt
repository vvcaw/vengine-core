package me.vvcaw.vengine.core.animation.util

enum class Easing(val easingFunction: (p: Float) -> Float) {
    LINEAR(
        { p -> p }
    ),
    /*EASE_OUT_CIRC(0.0, 0.55, 0.45, 1.0),
    EASE_IN_CIRC(0.55, 0.0, 1.0, 0.45),
    EASE_IN_OUT_EXPO(0.87, 0.0, 0.13, 1.0),
    EASE_IN_OUT_QUART(0.76, 0.0, 0.24, 1.0),
    EASE_IN_OUT_QUAD(0.45, 0.0, 0.55, 1.0),*/
}