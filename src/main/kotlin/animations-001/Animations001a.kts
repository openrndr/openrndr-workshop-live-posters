@file:Suppress("UNUSED_LAMBDA_EXPRESSION")

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.extensions.Screenshots
import org.openrndr.extra.compositor.compose
import org.openrndr.extra.compositor.draw
import org.openrndr.extra.compositor.layer
import org.openrndr.math.Vector2
import org.openrndr.numate.inOutExpo
import org.openrndr.numate.storyboard


{ program: Program ->
    program.apply {

        extend(Screenshots().apply {
            scale = 4.0
        })

        val poster = compose {
            layer {
                val a = object {
                    var position: Vector2 = Vector2.ZERO
                }
                storyboard(loop = true) {
                    val newPosition = Vector2(Math.random()*width, Math.random()*height)
                    val length = (newPosition - a.position).length
                    a::position to newPosition during Math.max(0.1, length/200.0) eased inOutExpo
                }
                draw {
                    drawer.circle(a.position, 200.0)
                }
            }
        }

        extend {
            poster.draw(drawer)
        }
    }
}