package talk

import com.wbillingsley.veautiful.html.{<, ^}
import com.wbillingsley.veautiful.templates.{DeckBuilder, VSlides}
import Common._
import Template._

// From February 2021), we won't need this line.
object Talk {

  // Let's write our slide deck...
  val deck = new DeckBuilder()
    .titleSlide(
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
    .ppImageSlide(
      image1 = "images/workshop/armidale.png",
      image2 = "images/workshop/bool.jpg",
      caption = "UNE, Armidale, NSW, Australia. 85% of students online."
    )
    .landscapeImageSlide(
      image="images/workshop/coderchallenge2018.jpg",
      caption="UNE Cisco Coders Challenge 2018"
    )
    .landscapeImageSlide(
      image="images/workshop/coderchallenge tam 2019.jpg",
      caption="UNE Cisco Coders Challenge 2019, Peel High School, Tamworth"
    )
    .landscapeImageSlide(
      image="images/workshop/coderchallenge asc 2019.jpg",
      caption="UNE Cisco Coders Challenge 2019, Armidale Secondary College"
    )
    .landscapeImageSlide(
      image="images/workshop/scienceexperience2019.jpg",
      caption="ConocoPhillips Science Experience 2019."
    )
    .landscapeImageSlide(
      image="images/workshop/faroutscience2019.jpg",
      caption="Far Out Science, The Coding Escape, Tamworth 2018."
    )
    .markdownSlide(
      """## The Intelligent Book (circa 2009)
        |
        |* "Books that understand what they teach"
        |
        |* "Books that can learn from their users"
        |
        |* "What if the AI was your partner, not your marker?"
        |
        |Interaction with models and AI, over the web, in a wiki-like textbook
        |
        |""".stripMargin
    )
    .landscapeImageSlide(
      image="images/workshop/ibook1.png",
      caption="The Intelligent Book, wiki-like topic environment, circa 2004.",
      cover = false
    )
    .landscapeImageSlide(
      image="images/workshop/ibook2.png",
      caption="The Intelligent Book, circuits exercise, circa 2004.",
      cover = false
    )
    .sectionTitle("Fifteen years later", "In the early 2000s, browsers weren't up to the job. Now they are.")
    .renderNode

}
