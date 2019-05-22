import org.openrndr.Program
import org.openrndr.application
import org.openrndr.extra.olive.Olive
import org.openrndr.ffmpeg.FFMPEGVideoPlayer

class PersistentProgram: Program() {
    val cameraWidth = 640
    val cameraHeight = 480
    lateinit var camera: FFMPEGVideoPlayer
}

fun main() = application{
    program(PersistentProgram()) {

        camera = FFMPEGVideoPlayer.fromDevice(framerate = 25.0, width = cameraWidth, height = cameraHeight)
        camera.start()

        extend(Olive<PersistentProgram>()) {
            script = "src/main/kotlin/webcam-001/Webcam001a.kts"
        }
    }
}