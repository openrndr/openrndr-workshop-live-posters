@file:Suppress("UNUSED_LAMBDA_EXPRESSION")

package `effects-002`
import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.extensions.Screenshots
import org.openrndr.extra.compositor.compose
import org.openrndr.extra.compositor.draw
import org.openrndr.extra.compositor.layer
import org.openrndr.extra.compositor.post
import org.openrndr.math.Vector2
import org.openrndr.workshop.toolkit.filters.StepWaves
import org.openrndr.workshop.toolkit.filters.VerticalStepWaves
import org.openrndr.workshop.toolkit.filters.ZoomMosaic


{ program: Program ->
    program.apply {

        extend(Screenshots().apply {
            scale = 4.0
        })

        val poster = compose {
            layer {
                post(ZoomMosaic()) {
                    scale = Math.cos(seconds * 2.1) + 2.0
                    ySteps = 8
                    xSteps = 8
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