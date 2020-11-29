/**
 * Local copy of standard boilerplate for talks.
 *    Future library updates will make this unnecessary.
 */

package talk

import com.wbillingsley.veautiful.html.{<, DElement, Markup, VHtmlNode, ^}
import com.wbillingsley.veautiful.templates.VSlides

import scala.collection.mutable
import scala.scalajs.js

/**
 * Common UI components to all the views
 */
object Common {

  val markdownGenerator = new Markup({ s:String => js.Dynamic.global.marked(s).asInstanceOf[String] })

  def markdown(s:String):VHtmlNode = markdownGenerator.Fixed(s)

  val routes:Seq[(Route, String)] = Seq(
    IntroRoute -> "Home",
  )

  def linkToRoute(r:Route, s:String):VHtmlNode = <.a(
    ^.href := Router.path(r),
    ^.cls := (if (Router.route == r) "nav-link active" else "nav-link"),
    s
  )

  def leftMenu:VHtmlNode = <("nav")(^.cls := "d-none d-md-block bg-light sidebar",
    <.div(^.cls := "sidebar-sticky",
      <.ul(^.cls := "nav nav-pills flex-column",
        for { (r, t) <- routes } yield <.li(
          ^.cls := "nav-item",
          linkToRoute(r, t)
        )
      )
    )
  )

  def layout(ch:VHtmlNode) = shell(<.div(^.cls := "move-content-down",
    <.div(^.cls := "row",
      <.div(^.cls := "col-sm-3", leftMenu),
      <.div(^.cls := "col-sm-9", ch)
    )
  ))

  def shell(ch:VHtmlNode) = <.div(
    <("nav")(^.cls := "navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow",
      <.div(^.cls := "container",
        <.a(^.cls := "navbar-brand col-sm-3 col-md-2 mr-0", ^.href := "#", "")
      )
    ),

    <.div(^.cls := "container", ch)
  )

  /** Circuits Up! Logo */
  def symbol = {
    <.span()
  }

  def downloadFromGitHub(project:String, user:String="UNEcosc250"):VHtmlNode = {
    <.a(
      ^.cls := "btn btn-secondary",
      ^.href := s"https://github.com/$user/$project/archive/master.zip",
      ^.attr("aria-label") := s"Download $project as zip",
      <("i")(^.cls := "material-con", "cloud_download"), "Download"
    )
  }

  def downloadGitHubStr(project:String, user:String="UNEcosc250"):String = {
    s"<a href='https://github.com/$user/$project/archive/master.zip' aria-label='Download $project as zip'>Download the project as a zip file</i></a>"
  }

  def cloneGitHubStr(project:String, user:String="UNEcosc250"):String = {
    s"`git clone https://github.com/$user/$project.git`"
  }

  def titleCard(title:String, subtitle:String, authorCard:VHtmlNode, logos:VHtmlNode) = {
    <.div(
      <.h1(^.attr("style") := "margin-bottom: 0; font-size: 96px", title),
      <.h2(^.attr("style") := "margin-bottom: 150px", subtitle),
      <.div(^.attr("style") := "display: inline-flex;",
        authorCard
      ),
      <.div(logos)
    )
  }

  def bootStrapMediaBox(imageUrl:String, content:VHtmlNode) = {
    <.div(^.cls := "media",
      <.img(^.src := imageUrl, ^.cls := "mr-3", ^.attr("style") := "height: 150px"),
      <.div(^.cls := "media-body", ^.attr("style") := "text-align: left;", content)
    )
  }

  def uneLogo = <.img(
    ^.src := "images/workshop/unelogo.png",
    ^.attr("style") := "position: absolute; bottom: 20px; left:910px; max-height: 150px;"
  )

  val willCcBy:String =
    """
      |<p>Written by Will Billingsley</p>
      |
      |<a rel="license" href="http://creativecommons.org/licenses/by/3.0/au/">
      |  <img alt="Creative Commons Licence" style="border-width:0" src="https://i.creativecommons.org/l/by/3.0/au/88x31.png" /></a><br />
      |  This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by/3.0/au/">Creative Commons Attribution 3.0 Australia License</a>.
      |""".stripMargin

  /*
   * Slide decks
   */

  val decks:Map[String, VSlides] = Map(
    "talk" -> Talk.deck,
  )

  def showDeck(page:Int = 0):VHtmlNode = {
    <.div(
      <("nav")(^.cls := "navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow",
        <.div(^.cls := "container",
          <.a(^.cls := "navbar-brand col-sm-3 col-md-2 mr-0", ^.href := "#", "")
        )
      ),
      <.div(Talk.deck.atSlide(page))
    )
  }



}