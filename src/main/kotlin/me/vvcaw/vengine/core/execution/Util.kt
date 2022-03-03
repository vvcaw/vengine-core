package me.vvcaw.vengine.core.execution

fun scene(init: Scene.() -> Unit): Scene {
    val scene = Scene()
    scene.init()
    return scene
}