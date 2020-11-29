package talk

import com.wbillingsley.veautiful.PathDSL
import PathDSL.Extract._
import com.wbillingsley.veautiful.html.{<, Attacher, VHtmlNode, ^}
import com.wbillingsley.veautiful.templates.{HistoryRouter, VSlides}
import org.scalajs.dom

import scala.collection.mutable
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

sealed trait Route {
  def path:String
  def render: VHtmlNode
}
object Route {
  val deckPath = /# / stringParam
  val introPath = /# / "home"
}
/**
 * Local copy of standard boilerplate for talks.
 * Future library updates will make this unnecessary.
 */

case object IntroRoute extends Route {
  val path = Route.introPath.mkString(start)
  def render = Common.showDeck(0)
}
case class DeckRoute(page:Int) extends Route {
  val path = Route.deckPath.mkString(page.toString, start)
  def render = Common.showDeck(page)
}

@JSExportTopLevel("Router")
object Router extends HistoryRouter[Route] {

  var route:Route = IntroRoute

  def rerender() = renderElements(render())

  def render() = route.render

  override def path(route: Route): String = route.path

  def parseInt(s:String, or:Int):Int = {
    try {
      s.toInt
    } catch {
      case n:NumberFormatException => or
    }
  }
  override def routeFromLocation(): Route = PathDSL.hashPathList() match {
    case Route.introPath(_) => IntroRoute
    case Route.deckPath(((page, _), _))  => DeckRoute(parseInt(page, 0))
    case _ => IntroRoute
  }

  @JSExport
  def load(): Unit = {
    val n = dom.document.getElementById("render-here")
    n.innerHTML = ""
    val root = Attacher.newRoot(n)
    root.render(Router)
  }

}
