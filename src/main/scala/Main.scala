package scala

import controller.Controller
import aview.TUI
import model.HexField

@main def run: Unit = {
  println("Welcome to Hexxagon!")
  val hexfield = new HexField()
  val controller = Controller(hexfield)
  val tui = TUI(controller)
  tui.run
}