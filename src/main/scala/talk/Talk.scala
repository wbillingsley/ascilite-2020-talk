package talk

import com.wbillingsley.veautiful.html.{<, ^}
import com.wbillingsley.veautiful.templates.{DeckBuilder, VSlides}
import Common._

// From February 2021), we won't need this line.
object Talk {

  // Let's write our slide deck...
  val deck = new DeckBuilder()
    .veautifulSlide(
      titleCard(
        title="Revisiting the Intelligent Book",
        subtitle="Towards Seamless Intelligent Content and Continuously Deployed Courses",
        authorCard = bootStrapMediaBox(
          imageUrl = "images/will sketchshot.png",
          content = markdown(
            """<b>William Billingsley</b> <br />
              |University of New England, Australia <br />
              |wbilling@une.edu.au <br />
              |https://www.wbillingsley.com/ascilite2020talk
              |""".stripMargin
          )
        ),
        logos = uneLogo
      )
    ).withClass("center middle")
    .markdownSlide(
      s""" ## Agenda
        |
        | 1. Motivational - things it's still hard to do online
        |
        | 2. Revisiting the Intelligent Book - *"Courses that understand what they teach"*
        |
        | 3. Towards Seamless Intelligent Content ...
        |
        |    - A demo in this deck!
        |
        |    - Programmable slide decks
        |
        |    - Tutorials and challenge environments
        |
        | 4. ... and Continuously Deployed Courses
        |
        |    - A demo in this deck!
        |
        |    - Edit, publish, and consume like it's open source
        |
        | 5. Behind the scences
        |
        |    - Veautiful and VSlides
        |
        |""".stripMargin
    )
    .veautifulSlide(
      <.div(^.cls := "wrapper",
        <.img(^.src := "images/workshop/armidale.png"),
        <.img(^.src := "images/workshop/bool.jpg"),
        <("figcaption")("UNE, Armidale, NSW, Australia. 85% of students online.")
      )
    ).withClass("image-slide pp cover")
    .veautifulSlide(
      <.div(^.cls := "wrapper",
        <.img(^.src := "images/workshop/coderchallenge2018.jpg"), <("figcaption")("UNE Cisco Coders Challenge 2018")
      )
    ).withClass("image-slide cover")
    .veautifulSlide(
      <.div(^.cls := "wrapper",
        <.img(^.src := "images/workshop/coderchallenge tam 2019.jpg"), <("figcaption")("UNE Cisco Coders Challenge 2019, Peel High School, Tamworth")
      )
    ).withClass("image-slide cover")
    .veautifulSlide(
      <.div(^.cls := "wrapper",
        <.img(^.src := "images/workshop/coderchallenge asc 2019.jpg"), <("figcaption")("UNE Cisco Coders Challenge 2019, Armidale Secondary College")
      )
    ).withClass("image-slide cover")
    .veautifulSlide(
      <.div(^.cls := "wrapper",
        <.img(^.src := "images/workshop/scienceexperience2019.jpg"),
        <("figcaption")("ConocoPhillips Science Experience 2019.")
      )
    ).withClass("image-slide cover")
    .veautifulSlide(
      <.div(^.cls := "wrapper",
        <.img(^.src := "images/workshop/faroutscience2019.jpg"),
        <("figcaption")("Far Out Science, The Coding Escape, Tamworth 2018.")
      )
    ).withClass("image-slide cover")
    .veautifulSlide(
      <.div(^.cls := "wrapper",
        <.img(^.src := "images/workshop/ibook2.png"),
        <("figcaption")("The Intelligent Book, circuits exercise, circa 2004.")
      )
    ).withClass("image-slide cover")
    .renderNode

}
