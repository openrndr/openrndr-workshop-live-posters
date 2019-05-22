@file:Suppress("UNUSED_LAMBDA_EXPRESSION")

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.extensions.Screenshots
import org.openrndr.extra.compositor.compose
import org.openrndr.extra.compositor.draw
import org.openrndr.extra.compositor.layer
import org.openrndr.extra.compositor.post
import org.openrndr.math.Vector2
import org.openrndr.workshop.toolkit.filters.Separate
import org.openrndr.workshop.toolkit.filters.StepWaves
import org.openrndr.workshop.toolkit.filters.VerticalStepWaves


{ program: Program ->
    program.apply {

        extend(Screenshots().apply {
            scale = 4.0
        })

        val poster = compose {
            layer {
                post(Separate()) {
                    redShift = Vector2(0.1, 0.1)
                    greenShift = Vector2(0.1, 0.0)
                    blueShift = Vector2(0.0, 0.1)
                }
                draw {
                    drawer.fill = ColorRGBa.WHITE
                    drawer.stroke = null
                    drawer.circle(Vector2(width / 2.0, height / 2.0), 200.0)
                }
            }
        }

        extend {
            poster.draw(drawer)
        }
    }
}