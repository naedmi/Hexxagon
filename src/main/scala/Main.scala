
import aview.TUI
import aview.gui.GUI
import scala.io.StdIn.readLine
import scalafx.application.JFXApp3
import model.fieldComponent.fieldBaseImpl._
import controller.controllerComponent.controllerBaseImpl._

object starter {
  val controller = Controller(new Field())
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
      //GUI(Controller(new Field())).start()
}