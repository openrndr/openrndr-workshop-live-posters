@file:Suppress("UNUSED_LAMBDA_EXPRESSION")

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.FontImageMap
import org.openrndr.draw.LineCap
import org.openrndr.draw.RenderTarget
import org.openrndr.extensions.Screenshots
import org.openrndr.extra.compositor.*
import org.openrndr.ffmpeg.FFMPEGVideoPlayer
import org.openrndr.filter.blend.Multiply
import org.openrndr.filter.blend.add
import org.openrndr.filter.blend.multiply
import org.openrndr.filter.blur.GaussianBlur
import org.openrndr.math.Vector2
import org.openrndr.workshop.toolkit.filters.StepWaves
import org.openrndr.workshop.toolkit.filters.VerticalStepWaves
import org.openrndr.workshop.toolkit.filters.VerticalWaves


{ program: PersistentWebcamProgram ->
    program.apply {

        val poster = compose {
            layer {
                draw {
                    camera.next()
                    val s = height.toDouble()/cameraHeight
                    drawer.scale(s)
                    drawer.translate((s*width-cameraWidth)/2.0, 0.0)

                    camera.draw(drawer)
                }
            }
            layer {
                blend(Multiply())
                draw {
                    drawer.fill = ColorRGBa.RED
                    drawer.stroke = null
                    drawer.rectangle(20.0, 20.0, width-40.0, height-40.0)

                    drawer.stroke = ColorRGBa.WHITE
                    drawer.strokeWeight = 20.0
                    drawer.lineCap = LineCap.ROUND
                    drawer.lineSegment(60.0, 60.0, width-60.0, height-60.0)
                }

            }
        }

        extend {
            poster.draw(drawer)
        }
    }
}