@file:Suppress("UNUSED_LAMBDA_EXPRESSION")

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.FontImageMap
import org.openrndr.draw.LineCap
import org.openrndr.draw.RenderTarget
import org.openrndr.extensions.Screenshots
import org.openrndr.extra.compositor.*
import org.openrndr.ffmpeg.FFMPEGVideoPlayer
import org.openrndr.filter.blend.add
import org.openrndr.filter.blend.multiply
import org.openrndr.filter.blur.GaussianBlur
import org.openrndr.math.Vector2
import org.openrndr.workshop.toolkit.filters.StepWaves
import org.openrndr.workshop.toolkit.filters.Threshold
import org.openrndr.workshop.toolkit.filters.VerticalStepWaves
import org.openrndr.workshop.toolkit.filters.VerticalWaves


{ program: PersistentWebcamProgram ->
    program.apply {

        val poster = compose {
            layer {
                draw {
                    drawer.background(ColorRGBa.PINK)
                    for (y in 0 until height step 64) {
                        for (x in 0 until width step 64) {
                            drawer.circle(x*1.0, y*1.0, 64.0)
                        }
                    }
                }
            }
            layer {
                post(Threshold()) {
                    light = ColorRGBa.RED
                    dark = ColorRGBa.TRANSPARENT
                    threshold = 0.5
                }
                draw {
                    camera.next()
                    val s = height.toDouble()/cameraHeight
                    drawer.scale(s)
                    drawer.translate((s*width-cameraWidth)/2.0, 0.0)

                    camera.draw(drawer)
                }
            }
        }

        extend {
            poster.draw(drawer)
        }
    }
}