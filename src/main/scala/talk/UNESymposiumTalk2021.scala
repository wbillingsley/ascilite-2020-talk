package talk

import com.wbillingsley.veautiful.html.{<, ^}
import com.wbillingsley.veautiful.templates.{Challenge, DeckBuilder, VSlides}
import Common._
import Challenge._
import fromCircuitsUp.NMos
import coderunner.LoggingPrefabCodable
import willtap.Common
import willtap.control.States
import willtap.control.States.{bangBangControl, linearControl, lmSnobot}
import willtap.imperativeTopic.{ImpossibleThings, PrefabCodable}

// From February 2021), we won't need this line.
object UNESymposiumTalk2021 {

  // Let's write our slide deck...
  val deck = new DeckBuilder(1920, 1080)
    .titleSlide(
      title="Gameful Open Source Self-Publishing. And Robots",
      subtitle="",
      authorCard = <.div(
        <.div(^.attr("style") := "display: inline-block; margin-right: 50px;",
          bootStrapMediaBox(
            imageUrl = "images/will sketchshot.png",
            content = markdown(
              """<b>William Billingsley</b> <br />
                |University of New England, Australia <br />
                |wbilling@une.edu.au <br />
                |https://www.wbillingsley.com/ascilite-2020-talk/#/decks/uneSymposium2021/0
                |""".stripMargin
            )
          ),
        )
      ),
      logos = uneLogo
    )
    .markdownSlide(
      """
        |> In 2020, during COVID, everyone had to learn to do things from home that few could have imagined before.
        |> Things like rescuing victims from chemical spills. Teaching blind rats to explore their way to the middle of mazes.
        |> Escaping from dungeons filled with nefarious blob guards. Landing on the Moon.
        |> This talk gives a whirlwind tour of my “CS0” course and some of the techniques behind it.
        |
        |An abridged version of two presentations:
        |
        |* *"Revisiting the Intelligent Book: Towards Seamless Intelligent Content and Continuously Deployed Courses"* (ASCILITE, 2020)
        |* *"An Accelerated CS0 for Online Mature-Age Part-Time Students"* (ITiCSE, 2021)
        |* (plus a few new bits)
        |
        |""".stripMargin
    )
    .sectionTitle(
      title="What computational thinking looks like in a physical setting",
      subtitle=""
    )
    .landscapeImageSlide(
      image="images/workshop/coderchallenge asc 2019.jpg",
      caption="UNE Cisco Coders Challenge 2019, Armidale Secondary College",
      cover = true
    )
    .landscapeImageSlide(
      image="images/workshop/faroutscience2019.jpg",
      caption="Far Out Science, The Coding Escape, Tamworth 2018.",
      cover = true
    )
    .sectionTitle(
      title="But we're not in a physical context",
      subtitle="Introducing a Diploma of Information Technology (Feb 2020)"
    )
    .markdownSlide(
      """## Our context
        |
        |* A mostly-online cohort (you might be the only student in your city)
        |
        |* Mostly part-time (they struggle to find a common time to meet virtually too)
        |
        |* A mostly-mature age cohort.
        |
        |Oh, and a pandemic.
        |""".stripMargin
    )
    .markdownSlides(
      """## What does *CS0* mean in an online part-time mature age setting?
        |
        |* Probably not students' first exposure to computing
        |
        |* Promote conversations between (potentially isolated) online students, around outreach-style robotics challenges
        |
        |* Stretch and challenge more experienced students, without making the course unachievable for inexperienced students
        |
        |* Compress the introduction of programming
        |
        |""".stripMargin
    )
    .veautifulSlide(<.div(
      <.h2("Prod it and see..."), NMos.Page3
    ))
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
     s"""## In context
        |
        |  - [A simple maze for demonstrating while loops](https://theintelligentbook.com/thinkingaboutprogramming/#/decks/commandsAndFunctions/10/fullscreen)  \t
        |    You can program it with tiles or with JavaScript.  \t
        |    Note that if you pop out of full-screen, you can also play the game in the "notes" version, and you
        |    can jump to the video.
        |
        |A lot of "scaffolding" happens by morphing the environment
        |
        |  - from a traditional [graphics turtle](https://theintelligentbook.com/thinkingaboutprogramming/#/challenges/turtleGraphics/0/0)
        |
        |  - to [HMS Turtle](https://theintelligentbook.com/thinkingaboutprogramming/#/decks/closedLoop/6/fullscreen)
        |
        |  - to [a robot that can sense its environment](https://theintelligentbook.com/thinkingaboutprogramming/#/challenges/rescueLine/0/2)
        |
        |  - to [a robot that can bump into walls](https://theintelligentbook.com/thinkingaboutprogramming/#/challenges/microRat/0/2)
        |
        |""".stripMargin
    )
    .markdownSlide(
      s"""## Four challenges
         |
         |These actually get quite complicated, promoting conversations across Slack as the students try to figure
         |out how to solve them
         |
         |* Escape the Lava Maze  \t
         |  https://theintelligentbook.com/thinkingaboutprogramming/#/challenges/lavaMaze/0/0
         |
         |* Rescue Line  \t
         |  https://theintelligentbook.com/thinkingaboutprogramming/#/challenges/rescueLine/0/0
         |
         |* MicroRat  \t
         |  https://theintelligentbook.com/thinkingaboutprogramming/#/challenges/microRat/0/0
         |
         |* Waiter Game  \t
         |  https://codesandbox.io/s/github/jvitale/une_ICT100_assignment4
         |""".stripMargin
    )
    .sectionTitle(
      title="Self-publishing...", subtitle="Toto, I've a feeling we're not in Moodle any more"
    )
    .veautifulSlide(
      lmSnobot.slide
    )
    .markdownSlide(
      s"""## You might notice...
        |
        |* Even though this is *a completely different site*, I was able to bring in a slide from one of my
        |  *Thinking About Programming* decks. Not just a picture of it. It *runs*.
        |
        |* This talk's URL is under my personal homepage, `wbillingsley.com`.  \t
        |  It keeps linking to my personal PhD project site (`theintelligentbook.com`) that I've used since Cambridge days,  \t
        |  but the links in Moodle are to `turing.une.edu.au`...  \t
        |
        |Somehow things are being published to multiple destinations
        |
        |First, let's step back in time...
        |
        |""".stripMargin
    )
    .landscapeImageSlide(
      image="images/overleaf_smaller.png",
      caption="For 37 years, academics have been writing papers like they're code... (LaTeX)",
      cover = true
    )
    .markdownSlide(
      """## 2004-2015 "Markdown" made text formats incredibly popular...
        |
        |This is a slide deck from Software Development Studio 2. It looks like PowerPoint.
        |
        |<iframe src="https://turing.une.edu.au/~cosc220/sync/lectures/cosc220/lecture.html?Class%20Modelling.md#1"
        |  width="1340" height="720"
        |></iframe>
        |https://turing.une.edu.au/~cosc220/sync/lectures/cosc220/lecture.html?Class%20Modelling.md#1
        |
        |""".stripMargin
    )
    .markdownSlide(
      """## But really it isn't...
        |
        |Behind the scenes, it's just a text file, in "Markdown" format
        |
        |<iframe src="https://turing.une.edu.au/~cosc220/sync/lectures/cosc220/Class%20Modelling.md#1"
        |  width="1340" height="720"
        |></iframe>
        |https://turing.une.edu.au/~cosc220/sync/lectures/cosc220/Class%20Modelling.md#1
        |
        |""".stripMargin
    )
    .markdownSlide(
      """## Why programmers like text formats...
        |
        |We can put it in version control:
        |
        |* https://gitlab.une.edu.au/wbilling/cosc220-sync-lectures (requires UNE login)
        |
        |We can "push" it to more than one place:
        |
        |* https://github.com/UNEsestudio/cosc220-sync-lectures (published for today's demo)
        |
        |Because it's all loaded via HTML and JavaScript, we can load it in our browser:
        |
        |* On turing [https://turing.une.edu.au/~cosc220/sync/lectures/cosc220/lecture.html?Class%20Modelling.md#1](https://turing.une.edu.au/~cosc220/sync/lectures/cosc220/lecture.html?Class%20Modelling.md#1)
        |
        |* For today's demo, on GitHub Pages [https://unesestudio.github.io/cosc220-sync-lectures/lecture.html?Class%20Modelling.md#1](https://unesestudio.github.io/cosc220-sync-lectures/lecture.html?Class%20Modelling.md#1)
        |
        |""".stripMargin
    )
    .markdownSlide(
      s"""## Going smarter with Doctacular and GitHub Actions
        |
        |Trying to do a whole site with Markdown is messy & it's harder to embed interactive elements.
        |
        |Instead, let's allow a course site to include *some code*. The LaTeX of course publishing.
        |Then, we can put our source in version control:
        |
        |* Thinking About Programming  \t
        |  https://github.com/theintelligentbook/thinkingaboutprogramming/
        |
        |* This talk  \t
        |  https://github.com/wbillingsley/ascilite-2020-talk/blob/master/src/main/scala/talk/UNESymposiumTalk2021.scala
        |
        |* The Adventures of Will Scala  \t
        |  https://github.com/theintelligentbook/willscala
        |
        |... which GitHub will then compile into a site for us:
        |
        |* https://theintelligentbook.github.io/thinkingaboutprogramming)
        |
        |* https://wbillingsley.github.io/ascilite-2020-talk
        |
        |* https://theintelligentbook.github.io/willscala)
        |
        |""".stripMargin
    )
    .markdownSlide(
      """## Two last demos
        |
        |* You can [download this talk](https://github.com/wbillingsley/ascilite-2020-talk/archive/refs/heads/gh-pages.zip) and it will work!
        |
        |* And let's [edit this line on GitHub](https://github.com/wbillingsley/ascilite-2020-talk/blob/master/src/main/scala/talk/UNESymposiumTalk2021.scala) and trigger an action to recompile the site:
        |
        |  *Mamma mia, here we go again...*
        |
        |""".stripMargin
    )
    .markdownSlide(Common.willCcBy)
    .renderSlides

}
