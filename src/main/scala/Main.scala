import controller.Controller
import aview.TUI
import aview.gui.GUI
import model.HexField
import scala.io.StdIn.readLine
import scalafx.application.JFXApp3;

object starter {
  val controller = Controller()
  val tui = TUI(controller)
  def start: String = "\nWelcome to Hexxagon!\n" + tui.message + controller
  def run: Unit = {
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
    starter.run
}
object MainGUI extends JFXApp3 {
    override def start() = starter.runGUI
}