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
object ITiCSETalk2021 {

  // Let's write our slide deck...
  val deck = new DeckBuilder(1920, 1080)
    .titleSlide(
      title="An Accelerated CS0 for Online Mature-Age Part-Time Students",
      subtitle="",
      authorCard = <.div(
        <.div(^.attr("style") := "display: inline-block; margin-right: 50px;",
          bootStrapMediaBox(
            imageUrl = "images/will sketchshot.png",
            content = markdown(
              """<b>William Billingsley</b> <br />
                |University of New England, Australia <br />
                |wbilling@une.edu.au <br />
                |""".stripMargin
            )
          ),
        ),
        <.div(^.attr("style") := "display: inline-block",
          bootStrapMediaBox(
            imageUrl = "https://profiles.uts.edu.au/Jonathan.Vitale/photo",
            content = markdown(
              """<b>Jonathan Vitale</b> <br />
                |University of New England, Australia <br />
                |jvitale@une.edu.au <br />
                |""".stripMargin
            )
          ),
        ),
      ),
      logos = uneLogo
    )
    .markdownSlide(
      """## Skippable links -
        |
        |Links to come back to:
        |
        |* A public version of some of Will's materials (not the whole course, and on a personal site)
        |  https://theintelligentbook.com/thinkingaboutprogramming
        |
        |""".stripMargin
    )
    .ppImageSlide(
      image1 = "images/workshop/armidale.png",
      image2 = "images/workshop/bool.jpg",
      caption = "UNE, Armidale, NSW, Australia. 85% of students online.",
      cover = true
    )
    .markdownSlide(
      """## Context
        |
        |Introducing a Diploma of Information Technology (Feb 2020), including offerings through Open Universities
        |Australia.
        |
        |* We have a mostly-online cohort even when we're not in a pandemic
        |
        |* Approx. 50% of students study part-time
        |
        |* We have a mostly-mature age cohort.
        |
        |  - 70% of BCompSc students older than 25.
        |
        |  - Median age in DIT that we introduced (30-34) actually slightly older than BCompSc (25-29).
        |
        |* Three "genuinely exciting, genuinely foundational" units added for the DIT:
        |
        |  - **Computational Thinking** (a CS0 course)
        |  - From Digital Logic to Data Processing (from the hardware up)
        |  - Data Science Studio 1 (using Jupyter etc)
        |
        |""".stripMargin
    )
    .markdownSlide(
      """## What does *CS0* mean in an online part-time mature age setting?
        |
        |* Probably not students' first exposure to computing
        |
        |* Varied exposure to computing (maybe patchy, maybe a long time ago), but enough that they've decided to take
        |  a degree in it.
        |
        |And as the Australian Curriculum now includes digital technologies from foundation to year 10, we expect these
        |will be the characteristics of future on-campus students too.
        |
        |""".stripMargin
    )
    .markdownSlide(
      """## Design Goals
        |
        |* Compress the introduction of programming
        |
        |* Promote conversations between (potentially isolated) online students, around outreach-style robotics challenges
        |
        |* Stretch and challenge more experienced students, without making the course unachievable for inexperienced students
        |
        |* Teach more than we assess.
        |
        |* To give students a tour of the kinds of computational thinking experience they might have missed
        |
        |""".stripMargin
    )
    .landscapeImageSlide(
      image="images/workshop/coderchallenge2018.jpg",
      caption="UNE Cisco Coders Challenge 2018",
      cover = true
    )
    .landscapeImageSlide(
      image="images/workshop/coderchallenge tam 2019.jpg",
      caption="UNE Cisco Coders Challenge 2019, Peel High School, Tamworth",
      cover = true
    )
    .landscapeImageSlide(
      image="images/workshop/coderchallenge asc 2019.jpg",
      caption="UNE Cisco Coders Challenge 2019, Armidale Secondary College",
      cover = true
    )
    .landscapeImageSlide(
      image="images/workshop/scienceexperience2019.jpg",
      caption="ConocoPhillips Science Experience 2019.",
      cover = true
    )
    .landscapeImageSlide(
      image="images/workshop/faroutscience2019.jpg",
      caption="Far Out Science, The Coding Escape, Tamworth 2018.",
      cover = true
    )
    .veautifulSlide(<.div(
        <.h2("Prod it and see..."), NMos.Page3
    ))
    .markdownSlide(
      """## Morphing as scaffolding
        |
        |Although we lose the tangibility of physical robots, simulations let us try out more things more cheaply
        |
        |* Simulating a jelly experiment
        |  [Making impossible things](https://theintelligentbook.com/thinkingaboutprogramming/#/decks/impossibleThings/0)
        |
        |And we can gradually morph environments to lead students along faster.
        |
        |* e.g. Morphing a graphics turtle
        |
        |  - from a traditional [graphics turtle](https://theintelligentbook.com/thinkingaboutprogramming/#/challenges/turtleGraphics/0/0)
        |
        |  - to [HMS Turtle](https://theintelligentbook.com/thinkingaboutprogramming/#/decks/closedLoop/6/fullscreen)
        |
        |  - to [a robot that can sense its environment](https://theintelligentbook.com/thinkingaboutprogramming/#/challenges/rescueLine/0/2)
        |
        |""".stripMargin
    )
    .markdownSlide(
      """## The coding environment also morphs
        |
        |In the embedded environments, e.g. [from the Commands and Functions deck](https://theintelligentbook.com/thinkingaboutprogramming/#/decks/commandsAndFunctions/10/fullscreen) -
        |
        |* Blocks-coding that produces JavaScript, or
        |
        |* JavaScript
        |
        |The course also transitions from embedded coding environments to file-based coding environments (early in code-along workshops, later in challenges for students)
        |
        |* e.g., [Week 3 tutorial on codesandbox.io](https://codesandbox.io/s/github/jvitale/UNE_ICT100_workshops_T3)
        |
        |""".stripMargin
    )
    .markdownSlide(
      """## Unpacking the environments
        |
        |Playing with these environments also lets us show higher level concepts
        |
        |""".stripMargin
    )
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
      s"""## Assessment structure
        |
        |* **40%** - challenges  \t
        |  intended to stretch students and prompt them to spend time discussing and problem-solving with each other. ️
        |
        |* **30%** - quizzes  \t
        |  set at much lower levels in Bloom’s taxonomy. ️
        |
        |* **30%** - take-home exam  \t
        |  acts as a reflection and summary of what students have learned.
        |""".stripMargin
    )
    .markdownSlide(
      s"""## Challenges
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
    .markdownSlide(
      """## Topics
        |
        |<table class="topictable">
        |<style>.topictable th,.topictable td { padding: 1em; text-align: center; }</style>
        |<tr><th>Week</th><th>Topic</th><th>Tutorial</th></tr>
        |<tr><td>1</td><td>Models & abstraction. Commands and Functions</td><td>Cleverbot. Turtle graphics</td></tr>
        |<tr><td>2</td><td>Types and data (primitive types)</td><td>Challenge 1: Lava Maze</td></tr>
        |<tr><td>3</td><td>Objects and events (reference types)</td><td>Chatbots: speech synthesis</td></tr>
        |<tr><td>4</td><td>Robots and control</td><td>Challenge 2: Rescue Line</td></tr>
        |<tr><td>5</td><td>Sensors and actuators</td><td>Chatbots: face detection</td></tr>
        |<tr><td>6</td><td>Little data (higher order functions)</td><td>Chatbots: face recognition</td></tr>
        |<tr><td>7-8</td><td colspan="2"></td></tr>
        |<tr><td>9</td><td>Markup languages (Markdown first!)</td><td>Chatbots: natural language processing</td></tr>
        |<tr><td>10</td><td>Nested structures (trees, arrays of arrays)</td><td>Challenge 3: MicroRat</td></tr>
        |<tr><td>11</td><td>Asynchronous programming</td><td>Deadlocks and starvation</td></tr>
        |<tr><td>12</td><td>Debuggers and debugging</td><td>Challenge 4: Waiter game</td></tr>
        |<tr><td>13</td><td>Randomness and games</td><td>Game strategies</td></tr>
        |</table>
        |
        |""".stripMargin
    )
    .markdownSlide(
      s"""## Outcomes
        |
        |* We've run two cohorts (Jul-Oct 2020; Nov-Feb 2020/21). A third cohort started yesterday.
        |
        |* Observations mostly from the first cohort
        |  - Average mark in the challenges starts high, but does decline. (90.3%, 73.1%, 69.2%, and 58.9%)
        |  - Exam performance is high (average 87%)
        |  - Attrition (27%) in line with when our CS1 course was first offered
        |
        |* Challenges did foster interactivity and discussion  \t
        |  <img style="max-height: 300px;" src="images/slack_active_users.png" />
        |
        |""".stripMargin
    )
    .markdownSlide(
      """## However
        |
        |* Students often get stressed if they can't beat the last level of a challenge.
        |
        |* Difficulty ramp on the challenges is perceived as too steep (not enough smaller practice environments)
        |
        |* Exam mark suggests students are succeeding - but they do feel it's hard getting there
        |""".stripMargin
    )
    .markdownSlide(
      """## Conclusions
        |
        |* "CS0" increasingly needs to be designed to recognise it *isn't* students' first computational thinking experience
        |
        |* Compressing programming syntax into the first few weeks (before moving on to complex challenges in simplified environments) appears successful
        |
        |* As always for our first offerings, there are some rough edges to work out
        |""".stripMargin
    )
    .renderSlides

}
