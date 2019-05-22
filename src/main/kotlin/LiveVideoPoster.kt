import org.openrndr.Program
import org.openrndr.application
import org.openrndr.draw.ColorBuffer
import org.openrndr.extra.olive.Olive
import org.openrndr.ffmpeg.FFMPEGVideoPlayer

class PersistentVideoProgram: Program() {
    var videoPlayer: FFMPEGVideoPlayer? = null
}

fun main() = application{
    configure {
        width = 480
        height = 640
    }

    program(PersistentVideoProgram()) {

        extend(Olive<PersistentVideoProgram>()) {
            script = "src/main/kotlin/video-001/Video001b.kts"
        }
    }
}