package scala

import aview.TUI
import aview.gui.GUI
import HexModule.{given}
import scala.io.StdIn.readLine
import scalafx.application.JFXApp3
import model.fieldComponent.fieldBaseImpl._
import controller.controllerComponent.ControllerInterface

object starter {
  // val controller = Controller(new Field(new Matrix(3, 2)))
  val tui = TUI()
  def runTUI: Unit = {
    println(tui.startmes)
    var input = ""
    var tmp = tui.handleInput(input)
    while(!tmp.equals(Some("Exiting.")) & !tmp.equals(Some("Filled with X.")) & !tmp.equals(Some("Filled with O."))) {
      input = readLine()
      tmp = tui.handleInput(input)
      if !tmp.isEmpty then println(tmp.get)
    } 
  }
  def runGUI = {
    GUI().start()
  }
}
object MainTUI extends App {
    starter.runTUI
}
object MainGUI extends JFXApp3 {
    override def start() = 
      starter.runGUI
      // GUI(Controller(new Field())).start()
}