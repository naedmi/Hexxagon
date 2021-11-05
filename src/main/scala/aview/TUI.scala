package aview

import util.Observer
import controller.Controller
import model.HexField
import scala.io.StdIn.readLine
import scala.util.matching.Regex

class TUI(controller: Controller) extends Observer {
    controller.add(this)
    override def update = println(controller.hexfield)
    def run = getInput

    def getInput: Unit = 
        val maxind1 = controller.hexfield.matrix.row - 1
        val maxind2 = controller.hexfield.matrix.col - 1
        printf("Input your x and y Coordinate as followed: [ 0-%d ] [ 0-%d ] [ X | O ] \n", maxind2, maxind1)
        print(controller.hexfield);

        val reg: Regex = ("[0-" + maxind2 + "]\\s[0-" + maxind1 + "]\\s[XO]").r
        var line = readLine()

        while(!line.equals("exit")) {
            line = matchReg(reg.findFirstIn(line))

            if line.equals("Wrong Input") then
                println(line)
            else
                val (y:Int, x:Int, c:Char) = line.split("\\s") match { case Array(x, y, c) => (x.toInt, y.toInt, c.charAt(0)) }
                if c.equals('X') then
                    controller.placeX(x, y)
                else if c.equals('O') then
                    controller.placeO(x, y)
            end if
            line = readLine()
        }

    def matchReg(x : Option[String]): String = 
        x match {
            case None => "Wrong Input"
            case Some(s) => s
        }
}
