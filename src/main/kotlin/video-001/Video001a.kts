@file:Suppress("UNUSED_LAMBDA_EXPRESSION")

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.FontImageMap
import org.openrndr.draw.RenderTarget
import org.openrndr.extensions.Screenshots
import org.openrndr.extra.compositor.*
import org.openrndr.ffmpeg.FFMPEGVideoPlayer
import org.openrndr.filter.blend.Add
import org.openrndr.filter.blend.add
import org.openrndr.filter.blur.GaussianBlur
import org.openrndr.math.Vector2
import org.openrndr.workshop.toolkit.filters.StepWaves
import org.openrndr.workshop.toolkit.filters.VerticalStepWaves
import org.openrndr.workshop.toolkit.filters.VerticalWaves


{ program: PersistentVideoProgram ->
    program.apply {

        val gaussianBlur = GaussianBlur().apply {
            gain = 1.0
            spread = 1.0
            window = 25
        }

        window.drop.listen {

            if (it.files.isNotEmpty()) {
                if (it.files[0].extension in setOf("mp4", "mov", "mpg")) {
                    val path = it.files[0].toString() //.relativeTo(File(".")).toString()
                    videoPlayer = FFMPEGVideoPlayer.fromFile(path)
                    videoPlayer?.start()
                }
            }
        }

        extend(Screenshots().apply {
            scale = 4.0
        })

        val poster = compose {

            layer {
                draw {
                    if (videoPlayer == null) {
                        drawer.fontMap = FontImageMap.fromUrl("file:data/fonts/IBMPlexMono-Regular.ttf", 32.0, 2.0)
                        drawer.text("Drop a video on me", 20.0, 200.0)
                    } else {
                        videoPlayer?.let {
                            it.next()
                            drawer.pushTransforms()
                            drawer.scale(drawer.height.toDouble() / it.height)
                            it.draw(drawer)
                            drawer.popTransforms()
                        }
                    }
                }
            }

            layer {
                blend(Add())
                post(GaussianBlur()) {
                    sigma = 1.0
                }
                draw {
                    drawer.fontMap = FontImageMap.fromUrl("file:data/fonts/IBMPlexMono-Regular.ttf", 32.0, 2.0)
                    drawer.text("VIDEO", Math.random() * drawer.width, Math.random() * drawer.height)
                }
            }
        }

        extend {
            poster.draw(drawer)
        }
    }
}