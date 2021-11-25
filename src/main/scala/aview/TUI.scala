package aview

import util.Observer
import controller.Controller
import model.HexField
import scala.util.matching.Regex

class TUI(controller: Controller) extends Observer {
    controller.add(this)
    override def update = println(controller)
    
    val maxind1 = controller.hexfield.matrix.row - 1
    val maxind2 = controller.hexfield.matrix.col - 1
    val reg: Regex = ("([0-" + maxind2 + "]\\s[0-" + maxind1 + "]\\s[XOxo])").r
    val message = s"Input your x and y Coordinate as followed: [ 0-$maxind2 ] [ 0-$maxind1 ] [ X | O ] \n"

    def handleInput(in:String): String = 
        in match {
            case reg(_*) => {
                val (x:Int, y:Int, c:Char) = in.split("\\s") match { case Array(x, y, c) => (x.toInt, y.toInt, c.charAt(0)) }
                controller.place(c.toUpper, x, y)
                ""  // must return String
            }
            case "fill X" | "fill x" => {
                controller.fillAll('X')
                "Filled with X."
            }
            case "fill O" | "fill o" => {
                controller.fillAll('O')
                "Filled with O."
            }
            case "redo" | "r" | "re" => controller.redo; "Redone."
            case "undo" | "u" | "un" => controller.undo; "Undone."
            case "q" | "e" | "exit" | "quit" | "Exit" | "Quit" => "Exiting."
            case _ => "Wrong Input."
        }
}