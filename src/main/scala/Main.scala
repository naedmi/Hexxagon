package scala

import aview.TUI
import aview.gui.GUI
import com.google.inject.Guice
import scala.io.StdIn.readLine
import scalafx.application.JFXApp3
import model.fieldComponent.fieldBaseImpl._
import controller.controllerComponent.ControllerInterface
import scala.HexModule
import com.google.inject.Guice

object starter {
  val injector = Guice.createInjector(new HexModule)
  val controller = injector.getInstance(classOf[ControllerInterface[Char]])
  // val controller = Controller(new Field(new Matrix(3, 2)))
  val tui = TUI(controller)
  def start: String = "\nWelcome to Hexxagon!\n" + tui.message + controller
  def runTUI: Unit = {
    println(start)
    var input = ""
    var tmp = tui.handleInput(input)
    while(!tmp.equals(Some("Exiting.")) & !tmp.equals(Some("Filled with X.")) & !tmp.equals(Some("Filled with O."))) {
      input = readLine()
      tmp = tui.handleInput(input)
      if !tmp.isEmpty then println(tmp.get)
    } 
  }
  def runGUI = {
    GUI(controller).start()
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