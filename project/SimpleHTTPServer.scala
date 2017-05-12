import java.io.{ Closeable, File }

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.RouteResult.Complete
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

import scala.concurrent.Future

object SimpleHTTPServer {
  def apply(webDirectory: File, port: Int = 8080) = new SimpleHTTPServer(webDirectory, port)
}

class SimpleHTTPServer(webDirectory: File, port: Int) extends Closeable {
  private val classLoader = getClass.getClassLoader
  private implicit val system = ActorSystem("simple-http-server", ConfigFactory.load(classLoader), classLoader)
  private implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  private implicit val executionContext = system.dispatcher

  private val route404 = getFromFile(new File(webDirectory, "404.html"))
    .andThen(_.map {
      case Complete(response) => Complete(response.copy(status = 404))
      case other => other
    })

  private val route =
    getFromDirectory(webDirectory.getAbsolutePath) ~
      pathPrefix(Segments) { folderNameSeq =>
        val absoluteFolder = folderNameSeq.foldLeft(webDirectory)((acc, subfolder) => new File(acc, subfolder))
        getFromFile(new File(absoluteFolder, "index.html"))
      } ~ route404


  val bindingFuture: Future[ServerBinding] = Http().bindAndHandle(route, "localhost", port)

  def close(): Unit = {
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.shutdown())
  }
}
