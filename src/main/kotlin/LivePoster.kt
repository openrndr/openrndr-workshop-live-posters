import org.openrndr.Program
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.FontImageMap
import org.openrndr.draw.loadImage
import org.openrndr.draw.tint
import org.openrndr.extra.olive.Olive

fun main() = application {
    configure {
        width = 768
        height = 576
    }

    program {

        extend(Olive<Program>()) {
            script = "src/main/kotlin/poster-script-01.kts"
        }


    }
}