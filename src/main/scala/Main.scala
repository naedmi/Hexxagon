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
    while(tmp != "Exiting.") {
      input = readLine()
      tmp = tui.handleInput(input)
      println(tmp) 
    } 
  }
}
object Main extends App {
    starter.run
}