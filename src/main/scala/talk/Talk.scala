package talk

import com.wbillingsley.veautiful.html.{<, ^}
import com.wbillingsley.veautiful.templates.{Challenge, DeckBuilder, VSlides}
import Common._
import Template._
import Challenge._
import fromCircuitsUp.NMos
import coderunner.LoggingPrefabCodable
import willtap.Common
import willtap.control.States
import willtap.control.States.{bangBangControl, linearControl, lmSnobot}
import willtap.imperativeTopic.{ImpossibleThings, PrefabCodable}

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
            |https://www.wbillingsley.com/ascilite-2020-talk/
            |""".stripMargin
        )
      ),
      logos = uneLogo
    )
    .markdownSlide(
      """## Links
        |
        |Caveat: This is a talk about a live project; I'll talk about more than is in the paper.
        |
        |* This talk:
        |
        |  - Live deck: https://www.wbillingsley.com/ascilite-2020-talk/
        |  - Source code: https://github.com/wbillingsley/ascilite-2020-talk
        |
        |* Veautiful:
        |
        |  - Homepage: https://wbillingsley.github.io/veautiful
        |  - GitHub project: https://github.com/wbillingsley/veautiful
        |
        |* The Intelligent Book demos:
        |
        |  - *Circuits Up!*: https://theintelligentbook.com/circuitsup
        |  - *Thinking About Programming*: https://theintelligentbook.com/thinkingaboutprogramming
        |  - *The Adventures of Will Scala*: https://theintelligentbook.com/willscala
        |  - Homepage (needs updating): https://theintelligentbook.com/
        |
        |""".stripMargin
    )
    .markdownSlide(
      s""" ## Agenda
        |
        | 1. Motivational - making courses proddable
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
        | 5. Behind the scenes
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
      """## The Intelligent Book (circa 2003 - 2009)
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
    .sectionTitle(
      title="Ten years later",
      subtitle="Over the last decade, how we program the web has transformed."
    )
    .sectionTitle(
      title="The Intelligent Book (revisited)",
      subtitle = "Towards Seamless\nIntelligent Content..."
    )
    .veautifulSlide(<.div(
        <.h2("From \"Circuits Up!\""), NMos.Page3
    ))
    .veautifulSlide(<.div(
      <.h2("From \"Thinking About Programming\""), ImpossibleThings.Particles
    ))
    .veautifulSlide(
      lmSnobot.slide
    )
    .veautifulSlide(
      textAndEx(
        markdown(
          """## Bang Bang control
            |
            |One of our state variables is how fast we are descending: `vy`.
            |
            |However, we could decide to simplify that and think about "are we going too fast?"
            |
            |If we're going too fast, turn the thruster on and slow down. If we're not, turn it off.
            |
            |In engineering, this kind is called *on/off* control, or, sometimes
            |"bang bang" control. We never adjust the throttle - it's just fully on or fully off.
            |
            |As a programmer, though, you could think of it as defining your own higher-level derived state:
            |*too fast*.
            |""".stripMargin)
      )(PrefabCodable(
        """showState()
          |function tooFast() {
          |  return getVy() > 2
          |}
          |
          |// Let it get too fast
          |wait(250)
          |
          |while (true) {
          |  if (tooFast()) {
          |    setThrust(1)
          |  } else {
          |    setThrust(0)
          |  }
          |}
          |""".stripMargin, bangBangControl.canvasLand, codeStyle = Some("max-height: 360px; overflow: auto;")))
    )
    .markdownSlide(
      """## Programmable slide decks
        |
        |* Markdown and LaTeX based decks have been popular for many years. e.g. Beamer, Reveal.js, Remark.js
        |
        |  - e.g. [Class Modelling slides](http://turing.une.edu.au/~cosc220/sync/lectures/cosc220/lecture.html?Class%20Modelling.md#1)
        |    source: http://turing.une.edu.au/~cosc220/sync/lectures/cosc220/Class%20Modelling.md#1
        |
        |* **Veautiful VSlides** - make "programmable slide decks" almost as easy as Markdown-based decks.
        |
        |  - Embedding live exercises into the slide deck, so students can try it at home
        |    https://theintelligentbook.com/thinkingaboutprogramming/#/deck/commandsAndFunctions/17
        |
        |  - Explorable interactive widgets, blurring the distinction between slides and notes
        |    https://theintelligentbook.com/thinkingaboutprogramming/#/deck/sensorsAndMotors/11
        |
        |  - Source code for this talk:
        |    https://github.com/wbillingsley/ascilite-2020-talk/blob/master/src/main/scala/talk/Talk.scala
        |
        |""".stripMargin)
    .markdownSlide(
      """## Tutorials and challenge environments
        |
        |* Working towards making these (almost) as easy to write as decks
        |
        |  - e.g. Keeping progress in book-like tutorial exercises
        |    https://theintelligentbook.com/circuitsup/#/latches/0/0
        |
        |  - e.g. Game-like programming challenges in the browser
        |    https://theintelligentbook.com/thinkingaboutprogramming/#/challenge/microRat/0/0
        |
        |""".stripMargin
    )
    .markdownSlide(
      """## Enabling proddability and tinkering
        |
        |When decks can be *brimming* with models and toys, we can show much more subtlety in how things evolve:
        |
        |* Breaking down the steps of a problem, variations on a problem
        |
        |  - https://theintelligentbook.com/thinkingaboutprogramming/#/deck/states/10
        |
        |* Evolving models to think about new problems
        |
        |  1. [Turtle graphics](https://theintelligentbook.com/thinkingaboutprogramming/#/challenge/turtleGraphics/3/0)
        |
        |  2. [Sailing into the Channel](https://theintelligentbook.com/thinkingaboutprogramming/#/deck/closedLoop/6)
        |
        |  3. [A line following robot](https://theintelligentbook.com/thinkingaboutprogramming/#/deck/closedLoop/22)
        |
        |  4. [Bumping into things](https://theintelligentbook.com/thinkingaboutprogramming/#/challenge/microRat/2/1)
        |
        |""".stripMargin
    )
    .sectionTitle(
      title="The Intelligent Book (revisited)",
      subtitle = "... in continuously deployed courses"
    )
    .markdownSlide(
      """## Composability, Reuse, and Publication
        |
        |Demo course sites
        |
        |* [Circuits Up!](https://theintelligentbook.com/circuitsup/)
        |
        |* [Thinking about Programming](https://theintelligentbook.com/thinkingaboutprogramming/)
        |
        |* [Will Scala](https://theintelligentbook.com/willscala/)
        |
        |* This talk
        |
        |...automatically tested and published by public infrastructure
        |
        |* https://travis-ci.com/github/theIntelligentBook/thinkingaboutprogramming/builds
        |
        |* https://github.com/wbillingsley/ascilite-2020-talk/actions
        |
        |... that have version history and can be cloned ...
        |
        |* Public outreach: https://theintelligentbook.com/thinkingaboutprogramming
        |
        |* Private clone: http://turing.une.edu.au/~ict100/tap/
        |
        |""".stripMargin)
    .markdownSlide(
      """## Reusing materials
        |
        |* Software meanings of "reuse" are more sophisticated than OER. e.g. we can import and reuse individual slides,
        |  decks and models from other course sites.
        |
        |  In this deck:
        |
        |  ```scala
        |  // Import the Circuits Up materials
        |  libraryDependencies += "com.github.theintelligentbook" % "thinkingaboutprogramming" % "master-SNAPSHOT"
        |  ```
        |
        |* Adding a model from Thinking About Programming into this deck:
        |
        |  ```scala
        |  .veautifulSlide(lmSnobot.slide)
        |  ```
        |
        |* But we can also import models from other sites to build our own examples (e.g. import the circuit system)
        |  http://turing.une.edu.au/~ict100/tap/#/deck/sensorsAndMotors/0
        |
        |""".stripMargin
    )
    .markdownSlide(
      """## Course materials as digital products
        |
        |Existing public infrastructure for open source allows us to support
        |
        |* Edit, commit, auto-publish workflows
        |
        |* Version control and collaboration
        |
        |* Mirroring a public and a private course
        |
        |* Embedding connections to other services
        |
        |""".stripMargin
    )
    .sectionTitle("Behind the scenes")
    .markdownSlide(
      """## Veautiful
        |
        |* Front-end frameworks, e.g. React.js, Angular, d3, have become very popular recently.
        |  However -
        |
        |  - they are opinionated and high-friction with each other
        |
        |  - e.g. React virtual DOM vs d3 binding arrays to document nodes
        |
        |* [Veautiful](https://wbillingsley.com/veautiful) is an adaptable front-end for education
        |
        |  - less opinionated, so different components can be in different styles
        |
        |  - lower friction, so components from React, Angular, Vue, d3, etc can be incorporated
        |
        |""".stripMargin)
    .markdownSlide(
      """## Diversity of styles of components needed for education
        |
        |* Scatter (custom blocks language) vs Ace.js (third-party)
        |  https://theintelligentbook.com/thinkingaboutprogramming/#/challenge/rescueLine/2/5
        |
        |* d3-like graphs alongside React-like diagrams alongside updating text
        |  https://theintelligentbook.com/circuitsup/#/latches/0/0
        |
        |""".stripMargin
    )
    .markdownSlide(
      """## Simple composability
        |
        |Because it is built on a front end framework,
        |
        |* creating reusable components
        |
        |* creating reusable templates
        |
        |* creating reusable models and exercises
        |
        |* creating reusable sites
        |
        |* embedding code into components
        |
        |come "baked in".
        |
        |""".stripMargin)
    .sectionTitle("The Intelligent Book", "Towards course sites brimming with reusable, proddable models")
    .renderNode

}
