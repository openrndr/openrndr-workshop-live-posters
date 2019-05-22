import org.openrndr.Program
import org.openrndr.application
import org.openrndr.draw.ColorBuffer
import org.openrndr.extra.olive.Olive
import org.openrndr.ffmpeg.FFMPEGVideoPlayer
import org.openrndr.workshop.toolkit.website.WebsiteContents
import org.openrndr.workshop.toolkit.website.scrapeWebsite

class PersistentTextProgram: Program() {
     var text = mutableListOf("drop a text file here")
}

fun main() = application{
    configure {
        width = 480
        height = 640
    }
    program(PersistentTextProgram()) {

        extend(Olive<PersistentTextProgram>()) {
            script = "src/main/kotlin/textfile-001/Textfile001a.kts"
        }
    }
}