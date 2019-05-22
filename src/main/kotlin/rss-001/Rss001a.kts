@file:Suppress("UNUSED_LAMBDA_EXPRESSION")

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.FontImageMap
import org.openrndr.extensions.Screenshots
import org.openrndr.extra.compositor.*
import org.openrndr.filter.blend.Multiply
import org.openrndr.math.Vector2
import org.openrndr.rss.RSSParser
import org.openrndr.shape.Rectangle
import org.openrndr.text.Writer
import java.net.URL

fun Rectangle.translated(vector2: Vector2): Rectangle {
    return Rectangle(corner + vector2, width, height)
}

{ program: PersistentRssProgram ->
    program.apply {

        println("I got ${articles.size} articles")

        extend(Screenshots().apply {
            scale = 4.0
        })

        val poster = compose {

            layer {
                draw {
                    drawer.background(ColorRGBa.BLACK)
                    val article = articles[seconds.toInt() % articles.size]
                    drawer.fontMap = FontImageMap.fromUrl("file:data/fonts/IBMPlexMono-Regular.ttf", 16.0, 2.0)
                    drawer.fill = ColorRGBa.PINK
                    val w = Writer(drawer)
                    w.apply {
                        box = Rectangle(20.0, 20.0, width - 40.0, height - 20.0)
                        text(article.content ?: "")
                    }
                }
            }
            layer {
                blend(Multiply())
                draw {
                    drawer.background(ColorRGBa.BLACK)
                    drawer.fill = ColorRGBa.WHITE
                    drawer.stroke = null
                    drawer.circle(Vector2(width / 2.0, height / 2.0) + Vector2(0.0, Math.sin(seconds) * 50.0), 300.0)
                }
            }

        }

        extend {
            poster.draw(drawer)
        }
    }
}