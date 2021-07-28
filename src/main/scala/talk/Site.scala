package talk

import com.wbillingsley.veautiful.doctacular._
import com.wbillingsley.veautiful.html._
import org.scalajs.dom

import scala.scalajs.js.annotation._

/** The doctacular site */
val site = Site()

val scaleChallengesToWindow:Boolean = {
  !dom.window.location.search.contains("scale=off")
}

@JSExportTopLevel("ITICSESite")
object ITICSESite {
  /** The script that loads the site into the page */
  @JSExport
  @main def main(): Unit = {
    val n = dom.document.getElementById("render-here")
    n.innerHTML = ""

    site.toc = site.Toc(
      "Home" -> site.HomeRoute,
      "Talks" -> site.Toc(
        "ASCILITE 2020" -> site.addDeck("ASCILITE Talk", ASCILITE2020talk.deck),
        "ITiCSE 2021" -> site.addDeck("ITICSE Talk", ITiCSETalk2021.deck),
        "UNE Symposium 2021" -> site.addDeck("uneSymposium2021", UNESymposiumTalk2021.deck),
      )
    )

    site.home = () => site.renderDeck("ASCILITE Talk", 0)
    Styles.installStyles()
    site.attachTo(n)
  }
}

val frontPage = unique(<.div(
  <.h1("A site for my ITiCSE Talk in 2021"),
  <.p(
    """
      | Bla bla bla
      |""".stripMargin
  )
))