package me.vvcaw.vengine.core.animation

import me.vvcaw.vengine.core.animation.util.Easing
import me.vvcaw.vengine.core.elements.Element
import me.vvcaw.vengine.core.elements.PropertyPair
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.typeOf

data class PropertyAnimation<T : Element, V>(
    private val changes: List<StartToEndAnimationData<T, in V>>,
    val element: T,
    val duration: Double,
    private val easing: Easing
) : Animation {
    fun updateValues(progress: Float) {
        changes.forEach { (start, end, property) ->
            when (start) {
                is Float -> {
                    val castedEnd = end as Float
                    val easingProgress = easing.easingFunction(progress)
                    val animationProgress = start + easingProgress * (castedEnd - start)

                    //println("Start: $start, End: $castedEnd")
                    //println("Animating ${property.name} with current value of $animationProgress")

                    property.set(element, animationProgress as V)
                }
            }
        }
    }

    data class StartToEndAnimationData<T : Element, V>(
        val start: V,
        val end: V,
        val property: KMutableProperty1<T, in V>
    )
}
// TODO: Implement function to compare two states of an object and use start or end to do it (start.compare(start, end))