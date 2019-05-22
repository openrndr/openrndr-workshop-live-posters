import org.openrndr.Program
import org.openrndr.application
import org.openrndr.extra.olive.Olive
import org.openrndr.ffmpeg.FFMPEGVideoPlayer
import org.openrndr.workshop.toolkit.website.WebsiteContents
import org.openrndr.workshop.toolkit.website.scrapeWebsite

class PersistentScraperProgram: Program() {
    lateinit var websiteContents: WebsiteContents
}

fun main() = application{
    configure {
        width = 480
        height = 640
    }
    program(PersistentScraperProgram()) {

        val url = "https://nos.nl/artikel/2268648-ijsballen-veroorzaken-schade-aan-auto-s-en-een-gewonde.html"
        websiteContents = scrapeWebsite(url)

        extend(Olive<PersistentScraperProgram>()) {
            script = "src/main/kotlin/website-001/Website001a.kts"
        }
    }
}