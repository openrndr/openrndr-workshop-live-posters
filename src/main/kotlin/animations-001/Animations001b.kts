@file:Suppress("UNUSED_LAMBDA_EXPRESSION")

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.extensions.Screenshots
import org.openrndr.extra.compositor.blend
import org.openrndr.extra.compositor.compose
import org.openrndr.extra.compositor.draw
import org.openrndr.extra.compositor.layer
import org.openrndr.filter.blend.multiply
import org.openrndr.math.Vector2
import org.openrndr.numate.inOutExpo
import org.openrndr.numate.storyboard


{ program: Program ->
    program.apply {

        extend(Screenshots().apply {
            scale = 4.0
        })


        val poster = compose {

            draw {
                drawer.background(ColorRGBa.PINK)
            }

            for (i in 0 until 5)
                layer {
                    blend(multiply)
                    val a = object {
                        var position: Vector2 = Vector2.ZERO
                        var radius: Double = 100.0
                    }
                    storyboard(loop = true) {
                        val newPosition = Vector2(Math.random()*width, Math.random()*height)
                        val length = (newPosition - a.position).length
                        a::position to newPosition during Math.max(0.1, length/200.0) eased inOutExpo
                        a::radius to Math.random()*100.0+ 50.0 during Math.max(0.1, length/200.0) eased inOutExpo
                    }
                    draw {
                        drawer.fill = ColorRGBa.PINK.shade(0.75)
                        drawer.circle(a.position, a.radius)
                    }
                }
        }

        extend {
            poster.draw(drawer)
        }
    }
}