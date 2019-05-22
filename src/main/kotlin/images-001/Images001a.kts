@file:Suppress("UNUSED_LAMBDA_EXPRESSION")

import org.openrndr.PresentationMode
import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.extensions.Screenshots
import org.openrndr.extra.compositor.compose
import org.openrndr.extra.compositor.draw
import org.openrndr.extra.compositor.layer
import org.openrndr.extra.compositor.post
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle
import org.openrndr.workshop.toolkit.filters.Separate
import org.openrndr.workshop.toolkit.filters.StepWaves
import org.openrndr.workshop.toolkit.filters.VerticalStepWaves
import org.openrndr.workshop.toolkit.filters.Waves

fun Rectangle.translated(vector2: Vector2): Rectangle {
    return Rectangle(corner + vector2, width, height)
}

{ program: PersistentImagesProgram ->
    program.apply {



        window.requestDraw()

        window.presentationMode = PresentationMode.MANUAL


        // -- setup drop event
        window.drop.listen {
            val supportedExtensions = setOf("png", "jpg", "jpeg", "gif")
            for (file in it.files) {
                if (file.isDirectory) {
                    file.listFiles().forEach {
                        if (it.extension in supportedExtensions) {
                            images.add(ColorBuffer.fromFile(it.absolutePath).apply {
                                Session.active.untrack(this)
                            })

                        }
                    }
                }

                if (file.isFile) {
                    if (file.extension in supportedExtensions) {
                        println(file.absolutePath)
                        images.add(ColorBuffer.fromFile(file.absolutePath).apply {
                            Session.active.untrack(this)
                        })
                    }
                }
            }
            window.requestDraw()
        }

        mouse.clicked.listen {
            window.requestDraw()
        }


        extend(Screenshots().apply {
            scale = 4.0
        })

        val poster = compose {
            layer {
                draw {
                    drawer.background(ColorRGBa.WHITE)
                    drawer.fontMap = FontImageMap.fromUrl("file:data/fonts/IBMPlexMono-Regular.ttf", 14.0, 2.0)

                    if (images.size == 0) {
                        drawer.fill = ColorRGBa.BLUE
                        drawer.text("drop images here", 20.0, 20.0)
                    } else {
                        for (image in images.shuffled()) {
                            image.filter(MinifyingFilter.LINEAR_MIPMAP_LINEAR, MagnifyingFilter.LINEAR)
                            val imageBounds = image.bounds.offsetEdges(Math.random() * image.height * -0.2)

                            val iw = imageBounds.width * 0.1
                            val ih = imageBounds.height * 0.1
                            val spacing = 2.0
                            val stacking = 100
                            val margin = spacing * stacking

                            val targetBounds = Rectangle(Math.random() * (width - iw + margin / 2.0) - margin, Math.random() * (height - ih + margin / 2.0) - margin, imageBounds.width * 0.1, imageBounds.height * 0.1)

                            for (i in 0 until stacking) {
                                drawer.image(image, imageBounds, targetBounds.translated(Vector2(spacing * i.toDouble(), spacing * i.toDouble())))
                            }
                        }
                    }
                }
            }
        }

        extend {
            poster.draw(drawer)
        }
    }
}