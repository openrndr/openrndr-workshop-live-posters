import org.openrndr.Program
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.FontImageMap
import org.openrndr.draw.loadImage
import org.openrndr.draw.tint
import org.openrndr.extra.olive.Olive
import org.openrndr.math.IntVector2

fun main() = application {
    configure {
        width = 480
        height = 640
    }

    program {
        extend(Olive<Program>()) {

              script = "src/main/kotlin/template-001/Template001a.kts"

//            script = "src/main/kotlin/animations-001/Animations001a.kts"
//            script = "src/main/kotlin/animations-001/Animations001b.kts"
//            script = "src/main/kotlin/effects-001/Effects001a.kts"
//            script = "src/main/kotlin/effects-001/Effects001b.kts"
//            script = "src/main/kotlin/effects-001/Effects001c.kts"
//            script = "src/main/kotlin/effects-001/Effects001d.kts"
//            script = "src/main/kotlin/effects-002/Effects002a.kts"
//            script = "src/main/kotlin/effects-002/Effects002b.kts"
//            script = "src/main/kotlin/effects-003/Effects003a.kts"
//            script = "src/main/kotlin/effects-003/Effects003b.kts"
//            script = "src/main/kotlin/effects-003/Effects003c.kts"
//            script = "src/main/kotlin/shapes-001/Shapes001a.kts"
//            script = "src/main/kotlin/shapes-001/Shapes001b.kts"
//            script = "src/main/kotlin/shapes-001/Shapes001c.kts"
//            script = "src/main/kotlin/typography-001/Typography001a.kts"
//            script = "src/main/kotlin/typography-001/Typography001b.kts"

        }
   }
}