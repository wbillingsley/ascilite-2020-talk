package talk

import com.wbillingsley.veautiful.html.{<, VHtmlNode, ^}
import com.wbillingsley.veautiful.templates.DeckBuilder

/**
 * A local object to demonstrate how layouts can be templated. These can be provided as libraries or written by
 * local teams. The syntax will be cleaner with Scala 3
 */
object Template {

  implicit class DeckExtensions(val db:DeckBuilder) extends AnyVal {

    /**
     * A slide with a single image and a caption
     */
    def landscapeImageSlide(image:String, caption:String, cover:Boolean = true):DeckBuilder = {
      db.veautifulSlide(
        <.div(^.cls := "wrapper",
          <.img(^.src := image),
          <("figcaption")(caption)
        )
      ).withClass(if (cover) "image-slide cover" else "image-slide contain")
    }

    /**
     * Two portrait images, side by side to fill a slide
     */
    def ppImageSlide(image1:String, image2: String, caption:String, cover:Boolean = true):DeckBuilder = {
      db.veautifulSlide(
        <.div(^.cls := "wrapper",
          <.img(^.src := image1),
          <.img(^.src := image2),
          <("figcaption")(caption)
        )
      ).withClass(if (cover) "image-slide pp cover" else "image-slide pp contain")
    }

    /** A title slide for the front of the deck */
    def titleSlide(title:String, subtitle:String, authorCard:VHtmlNode, logos:VHtmlNode):DeckBuilder = {
      db.veautifulSlide(
        <.div(
          <.h1(^.attr("style") := "margin-bottom: 0; font-size: 96px", title),
          <.h2(^.attr("style") := "margin-bottom: 150px", subtitle),
          <.div(^.attr("style") := "display: inline-flex;",
            authorCard
          ),
          <.div(logos)
        )
      ).withClass("center middle")

    }

    def sectionTitle(title:String, subtitle:String = ""):DeckBuilder = {
      db.veautifulSlide(<.div(
        <.h2(title),
        <.p(subtitle)
      )).withClass("center middle")
    }


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

}
