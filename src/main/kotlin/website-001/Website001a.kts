@file:Suppress("UNUSED_LAMBDA_EXPRESSION")

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.FontImageMap
import org.openrndr.draw.RenderTarget
import org.openrndr.draw.grayscale
import org.openrndr.extensions.Screenshots
import org.openrndr.extra.compositor.*
import org.openrndr.ffmpeg.FFMPEGVideoPlayer
import org.openrndr.filter.blend.Multiply
import org.openrndr.filter.blend.add
import org.openrndr.filter.blend.multiply
import org.openrndr.filter.blur.GaussianBlur
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle
import org.openrndr.text.Writer
import org.openrndr.workshop.toolkit.filters.StepWaves
import org.openrndr.workshop.toolkit.filters.VerticalStepWaves
import org.openrndr.workshop.toolkit.filters.VerticalWaves


{ program: PersistentScraperProgram ->
    program.apply {

        extend(Screenshots().apply {
            scale = 4.0
        })

        val scale = (RenderTarget.active.width.toDouble() / width)
        var baseColor = ColorRGBa(Math.random(), Math.random(), Math.random())

        val poster = compose {
            draw {
                baseColor = ColorRGBa(Math.random(), Math.random(), Math.random())
            }

            layer {
                draw {
                    drawer.background(baseColor)
                }
            }
            websiteContents.images.forEach {
                // -- create a layer for every image, these will be multiply blended
                layer {
                    blend(Multiply())
                    draw {
                        drawer.background(ColorRGBa.WHITE)
                        drawer.drawStyle.colorMatrix = grayscale(0.1, 0.3, 0.3)
                        val imageSize = it.bounds
                        val target = Rectangle(Math.random() * (width - imageSize.width), Math.random() * (height - imageSize.height), imageSize.width, imageSize.height)
                        drawer.image(it, imageSize, target)
                    }
                }
            }

            // - create the typography layer
            layer {
                draw {
                    drawer.fill = baseColor
                    drawer.fontMap = FontImageMap.fromUrl("file:data/fonts/IBMPlexMono-Regular.ttf", 64.0, scale)

                    val w = Writer(drawer)
                    w.box = Rectangle(10.0, 10.0, width - 20.0, height - 20.0)
                    w.newLine()
                    // -- randomize tracking and leading
                    w.style.tracking = Math.random() * 10.0 + 10.0
                    w.style.leading = Math.random() * 10.0 + 10.0
                    w.text(websiteContents.title)
                }
            }
        }

        extend {
            poster.draw(drawer)
            Thread.sleep(100)
        }
    }
}