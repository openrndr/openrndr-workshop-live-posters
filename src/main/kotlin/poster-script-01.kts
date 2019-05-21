@file:Suppress("UNUSED_LAMBDA_EXPRESSION")
import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.extra.compositor.compose
import org.openrndr.extra.compositor.draw
import org.openrndr.extra.compositor.layer
import org.openrndr.extra.compositor.post
import org.openrndr.filter.blur.BoxBlur

{ program: Program ->
    program.apply {

        val composition = compose {

            layer {
                draw {
                    drawer.fontMap = FontImageMap.fromUrl("file:data/fonts/IBMPlexMono-Regular.ttf", 32.0)
                    drawer.fill = ColorRGBa.WHITE
                    drawer.text("blaa", 40.0, 100.0)
                }
            }

            layer {
                post(BoxBlur()) {
                    window = (Math.cos(seconds*2.0)*10.0 + 10.0).toInt()
                }
                draw {
                    drawer.fontMap = FontImageMap.fromUrl("file:data/fonts/IBMPlexMono-Regular.ttf", 164.0)
                    drawer.fill = ColorRGBa.WHITE
                    drawer.text("blaa", 40.0, 240.0)
                }
            }
        }
        extend {
            composition.draw(drawer)
        }
    }
}