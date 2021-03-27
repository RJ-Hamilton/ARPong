package com.hamilton.arpong.utils

import android.content.Context
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.await
import kotlinx.coroutines.withContext

suspend fun makeSphereRenderable(
    context: Context,
    radius: Float,
    position: Vector3,
    color: Color
): ModelRenderable = withContext(
    Dispatchers.Main
) {
    val material = MaterialFactory.makeOpaqueWithColor(context, color).await()
    ShapeFactory.makeSphere(radius, position, material)
}

suspend fun makeCubeRenderable(
    context: Context,
    size: Vector3,
    position: Vector3,
    color: Color
) = withContext(
    Dispatchers.Main
) {
    val material = MaterialFactory.makeOpaqueWithColor(context, color).await()
    ShapeFactory.makeCube(size, position, material)
}

suspend fun makeViewRenderable(context: Context, viewId: Int) = withContext(Dispatchers.Main) {
    val viewRenderable: ViewRenderable =
        ViewRenderable.builder().setView(context, viewId).build().await()
    viewRenderable
}