import org.openrndr.Program
import org.openrndr.application
import org.openrndr.draw.ColorBuffer
import org.openrndr.extra.olive.Olive
import org.openrndr.ffmpeg.FFMPEGVideoPlayer
import org.openrndr.workshop.toolkit.website.WebsiteContents
import org.openrndr.workshop.toolkit.website.scrapeWebsite

class PersistentImagesProgram: Program() {
    val images = mutableListOf<ColorBuffer>()
}

fun main() = application{
    configure {
        width = 480
        height = 640
    }
    program(PersistentImagesProgram()) {

        extend(Olive<PersistentImagesProgram>()) {
            script = "src/main/kotlin/images-001/Images001a.kts"
        }
    }
}