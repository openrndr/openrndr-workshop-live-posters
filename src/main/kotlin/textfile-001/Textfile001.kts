@file:Suppress("UNUSED_LAMBDA_EXPRESSION")

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.FontImageMap
import org.openrndr.draw.RenderTarget
import org.openrndr.extensions.Screenshots
import org.openrndr.extra.compositor.compose
import org.openrndr.extra.compositor.draw
import org.openrndr.extra.compositor.layer
import org.openrndr.extra.compositor.post
import org.openrndr.math.Vector2
import org.openrndr.workshop.toolkit.filters.StepWaves
import org.openrndr.workshop.toolkit.filters.VerticalStepWaves
import org.openrndr.workshop.toolkit.filters.VerticalWaves


{ program: Program ->
    program.apply {

        var text = mutableListOf("drop a text file here")

        window.drop.listen {
            text = it.files[0].readLines().toMutableList()
        }

        extend(Screenshots().apply {
            scale = 4.0
        })

        val scale = (RenderTarget.active.width.toDouble() / width)
        val poster = compose {
            layer {
                post(VerticalWaves()) {
                    amplitude = 0.01
                    period = Math.PI * 2.0 * mouse.position.y / height * 16.0
                    phase = seconds
                }
                draw {
                    drawer.fontMap = FontImageMap.fromUrl("file:data/fonts/IBMPlexMono-Regular.ttf", 32.0, scale)
                    drawer.texts((0 until text.size).map { text[it] }, (0 until text.size).map { Vector2(20.0, (it + 1) * 20.0) })
                }
            }
        }

        extend {
            poster.draw(drawer)
        }
    }
}