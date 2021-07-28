package fromCircuitsUp

import circuitsup.Common
import circuitsup.analog.Ohms.ScatterPlotData
import circuitsup.mosfets.NMos.NMOSFETCircuit
import circuitsup.mosfets.NMos.Page3.{nextButton, onCompletionUpdate, rerender}
import circuitsup.templates.{ExerciseStage, ScatterPlot}
import com.wbillingsley.veautiful.DiffNode
import com.wbillingsley.veautiful.html.{<, VHtmlComponent}
import com.wbillingsley.veautiful.templates.Challenge
import com.wbillingsley.veautiful.templates.Challenge.{Complete, Open}
import com.wbillingsley.wren.{Circuit, ConstraintPropagator, UserSet, ValueSlider}
import org.scalajs.dom.{Element, Node}

object NMos {


  object Page3 extends VHtmlComponent {
    var completion: Challenge.Completion = Open

    val nmc = new NMOSFETCircuit()
    nmc.vdd.voltage.content = Some(3d -> UserSet)
    nmc.vgb.voltage.content = Some(3d -> UserSet)

    val circuit = new Circuit(nmc.components :+
      new ValueSlider(nmc.vdd.voltage, nmc.vdd.x + 30 -> (nmc.vdd.y + 10), max="5", min="0", step="0.1")(() => onUpdate()) :+
      new ValueSlider(nmc.vgb.voltage, nmc.vgb.x - 130 -> (nmc.vgb.y + 10), max="5", min="0", step="0.1")(() => {
        plotData.data.clear()
        plotData.update()
        onUpdate()
      }), 600, 400)
    val propagator = new ConstraintPropagator(circuit.components.flatMap(_.constraints))
    propagator.resolve()

    val plotData = new ScatterPlotData(nmc.vdd.voltage, nmc.nMosfet.drain.current)

    def checkCompletion:Boolean = plotData.dataValues().length > 25

    def onUpdate():Unit = {
      propagator.clearCalculations()
      propagator.resolve()
      plotData.update()
      rerender()
    }

    override protected def render: DiffNode[Element, Node] = <.div(Challenge.textAndEx(
      <.div(
        Common.marked(
          s"""
             |* I'd started developing a kit for writing "Intelligent Books" that let us make the learning environment
             |  programmable
             |
             |* For example, the embedded circuit simulator on the right, simulating an nMOSFET
             |  (used in the digital electronics unit)
             |""".stripMargin
        )
      )
    )(<.div(
      circuit,
      ScatterPlot(600, 300, "Vdd", "Current", (d) => nmc.vdd.voltage.stringify(d), (d) => nmc.nMosfet.drain.current.stringify(d), 5,0.005).plot(plotData.dataValues())
    )))
  }

}
