@file:Suppress("UNUSED_LAMBDA_EXPRESSION")
import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*

{ program: Program ->
    program.apply {
        extend {
            drawer.fontMap = FontImageMap.fromUrl("file:data/fonts/IBMPlexMono-Regular.ttf", 64.0)
            drawer.background(ColorRGBa.PINK)
            drawer.fill = ColorRGBa.BLACK
            drawer.text("HELLO,", 30.0, 100.0)
            drawer.text("MY NAME IS:", 30.0, 150.0)
            val name = "<YOUR NAME HERE>"
            drawer.fontMap = FontImageMap.fromUrl("file:data/fonts/IBMPlexMono-Regular.ttf", 96.0)
            drawer.text("$name", 30.0, 220.0)
        }
    }
}