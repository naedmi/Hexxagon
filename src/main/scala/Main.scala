import controller.Controller
import aview.TUI
import model.HexField
import scala.io.StdIn.readLine

@main def run: Unit = {
  println("\nWelcome to Hexxagon!")
  val controller = Controller()
  val tui = TUI(controller)

  print(tui.message)
  println(controller)

  var input = ""
  var tmp = tui.handleInput(input)
  while(tmp != "Exiting.") {
    input = readLine()
    tmp = tui.handleInput(input)
    println(tmp) 
  } 
}