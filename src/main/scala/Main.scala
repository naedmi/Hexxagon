package scala

import aview.TUI
import aview.gui.GUI
import controller.controllerComponent.ControllerInterface
import controller.controllerComponent.controllerBaseImpl.Controller
import scalafx.application.JFXApp3

import scala.HexModule.given
import scala.io.StdIn.readLine

object starter {
  val tui = TUI()

  def runTUI: Unit = {
    println(tui.startmes)
    tui.handleInput("save")
    var input = ""
    var tmp = tui.handleInput(input)
    while (!tmp.equals(Some("Exiting.")) & !tmp.equals(Some("Filled with X.")) & !tmp.equals(Some("Filled with O."))) {
      input = readLine()
      tmp = tui.handleInput(input)
      if !tmp.isEmpty then println(tmp.get)
    }
  }

  def runGUI = {
    GUI().start()
  }

  def runController: ControllerInterface[Char] = {
    Controller()
  }
}

object MainTUI extends App {
  starter.runTUI
}

object MainGUI extends JFXApp3 {
  override def start() =
    starter.runGUI
}