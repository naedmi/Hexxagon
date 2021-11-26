import controller.Controller
import aview.TUI
import model.HexField
import scala.io.StdIn.readLine

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
}
object Main extends App {
    starter.run
}