package src.main.scala

import controller.Controller
import aview.TUI
import model.HexField
import scala.io.StdIn.readLine

@main def run: Unit = {
  println("\nWelcome to Hexxagon!")
  val hexfield = new HexField()
  val controller = Controller(hexfield)
  val tui = TUI(controller)

  print(tui.message)
  println(controller.hexfield)

  var input = ""
  while(tui.handleInput(input) != "Exiting.") {
    input = readLine()
    println(tui.handleInput(input)) 
  } 
}