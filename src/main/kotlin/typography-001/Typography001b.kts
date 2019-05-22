@file:Suppress("UNUSED_LAMBDA_EXPRESSION")

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.FontImageMap
import org.openrndr.draw.RenderTarget
import org.openrndr.extensions.Screenshots
import org.openrndr.extra.compositor.*
import org.openrndr.filter.blend.Add
import org.openrndr.filter.blend.add
import org.openrndr.filter.blur.GaussianBlur
import org.openrndr.math.Vector2
import org.openrndr.workshop.toolkit.filters.StepWaves
import org.openrndr.workshop.toolkit.filters.VerticalStepWaves
import org.openrndr.workshop.toolkit.filters.VerticalWaves
import org.openrndr.workshop.toolkit.filters.Waves


{ program: Program ->
    program.apply {


        extend(Screenshots().apply {
            scale = 4.0
        })

        val poster = compose {
            val scale = (RenderTarget.active.width.toDouble() / width)
            layer {
                post(Waves()) {
                    amplitude = 0.01
                    period = Math.PI * 2.0 * 32.0
                    phase = 0.0
                }
                post(Waves()) {
                    amplitude = 0.1
                    period = Math.PI * 2.0 * 8.432
                    phase = seconds
                }
                draw {
                    drawer.fontMap = FontImageMap.fromUrl("file:data/fonts/IBMPlexMono-Regular.ttf", 32.0, scale)
                    drawer.texts((0..40).map { "My a name is" }, (0..40).map { Vector2(20.0, it * 20.0) })
                }
            }

            layer {
                blend(Add())
                post(VerticalWaves()) {
                    amplitude = 0.1
                    period = Math.PI * 2.0 * 2.0
                    phase = seconds * 2.0
                }
                post(GaussianBlur()) {
                    gain = 1.0
                    spread = 1.0
                    window = (5 * scale).toInt()
                    sigma = (Math.cos(seconds) * 5.0 + 5.0) * scale
                }
                draw {
                    drawer.fontMap = FontImageMap.fromUrl("file:data/fonts/IBMPlexMono-Regular.ttf", 64.0, scale)
                    drawer.text("Tim Blurton", 20.0, 250.0)
                }
            }
        }

        extend {
            poster.draw(drawer)
        }
    }
}