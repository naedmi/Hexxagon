package aview

import util.Observer
import HexModule.{given}
import scala.util.matching.Regex
import controller.controllerComponent.ControllerInterface

class TUI(using controller: ControllerInterface[Char]) extends Observer {
    controller.add(this)
    override def update = println(controller)
    
    val maxind1 = controller.hexfield.matrix.row - 1
    val maxind2 = controller.hexfield.matrix.col - 1
    val reg: Regex = ("([0-" + maxind2 + "]\\s[0-" + maxind1 + "]\\s[XOxo])").r
    val message = s"Input your x and y Coordinate as followed:\n[ 0-$maxind2 ] [ 0-$maxind1 ] [ X | O ] \n"

    def startmes = "\nWelcome to Hexxagon!\n" + message

    def handleInput(in:String): Option[String] = 
        in match {
            case reg(_*) => {
                val (x:Int, y:Int, c:Char) = in.split("\\s") match { 
                    case Array(x, y, c) => (x.toInt, y.toInt, c.charAt(0)) 
                }
                controller.place(c.toUpper, x, y)
                None
            }
            case "fill X" | "fill x" => {
                controller.fillAll('X')
                Some("Filled with X.")
            }
            case "fill O" | "fill o" => {
                controller.fillAll('O')
                Some("Filled with O.")
            }
            case "save" => controller.save; Some("Saved.")
            case "load" => controller.load; Some("Loaded.")
            case "reset" => controller.reset; Some("Reset.")
            case "redo" | "r" | "re" => controller.redo; Some("Redone.")
            case "undo" | "u" | "un" | "z" => controller.undo; Some("Undone.")
            case "q" | "e" | "exit" | "quit" | "Exit" | "Quit" => Some("Exiting.")
            case _ => Some("Wrong Input.")
        }
}