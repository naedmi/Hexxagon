package controller

import GameStatus._
import util.Command

class PlaceCommand(content:Char, x:Int, y:Int, controller: Controller) extends Command {

    var rememberMe = controller.hexfield.matrix

    override def doStep = {
        rememberMe = controller.hexfield.matrix
        controller.hexfield.matrix = controller.hexfield.place(content, x, y)
    }
    override def undoStep = {
        val sec_rememberMe = controller.hexfield.matrix
        controller.hexfield.matrix = rememberMe
        rememberMe = sec_rememberMe
    }
    override def redoStep = {
        controller.hexfield.matrix = controller.hexfield.place(content, x, y)
    }
}

class PlaceAllCommand(content:Char, controller: Controller) extends Command {
    var rememberMe = controller.hexfield.matrix
    override def doStep = {
        rememberMe = controller.hexfield.matrix
        controller.hexfield.matrix = controller.hexfield.fillAll(content)
    }
    override def undoStep = controller.hexfield.matrix = controller.hexfield.fillAll(' ')
    override def redoStep = controller.hexfield.matrix = controller.hexfield.fillAll(content)
}