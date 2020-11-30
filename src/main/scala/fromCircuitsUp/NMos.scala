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
             |### n-Channel MOSFET
             |
             |This time, we've given you control of both the gate voltage and V<sub>DD</sub> to play around with.
             |
             |The chart still charts V<sub>DD</sub> against current, but it'll reset as you change the gate voltage.
             |This should let you draw the chart, then alter the gate voltage, and draw it again to see how it's changed.
             |
             |As the gate voltage rises above the threshold voltage (1.2V), more and more current can flow as the
             |channel gets wider (you should see the pink channel get wider). Below 1.2V, though, nothing can flow at all.
             |
             |In a digital circuit, that lets it act as an electronic switch. To turn it on, we set the gate voltage high.
             |To turn it off, we set the gate voltage low.
             |""".stripMargin
        )
      )
    )(<.div(
      circuit,
      ScatterPlot(600, 300, "Vdd", "Current", (d) => nmc.vdd.voltage.stringify(d), (d) => nmc.nMosfet.drain.current.stringify(d), 5,0.005).plot(plotData.dataValues())
    )))
  }

}
