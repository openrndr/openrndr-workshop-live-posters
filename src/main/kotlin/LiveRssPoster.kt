import org.openrndr.Program
import org.openrndr.application
import org.openrndr.draw.ColorBuffer
import org.openrndr.extra.olive.Olive
import org.openrndr.ffmpeg.FFMPEGVideoPlayer
import org.openrndr.rss.RSSParser
import org.openrndr.workshop.toolkit.website.WebsiteContents
import org.openrndr.workshop.toolkit.website.scrapeWebsite
import java.net.URL

class PersistentRssProgram: Program() {
     var articles = RSSParser.parse(URL("http://feeds.nos.nl/nosnieuwsalgemeen"))
}

fun main() = application{
    configure {
        width = 480
        height = 640
    }
    program(PersistentRssProgram()) {

        extend(Olive<PersistentRssProgram>()) {
            script = "src/main/kotlin/rss-001/Rss001a.kts"
        }
    }
}