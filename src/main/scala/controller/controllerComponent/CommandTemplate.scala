package controller.controllerComponent

import util.Command
import model.fieldComponent.{FieldInterface, MatrixInterface}

trait CommandTemplate[T <: FieldInterface[Char]](field: T) extends Command[T] {
    var rememberMe = field
    
    override def noStep(field: T): T = field

    override def doStep(field: T): T = {
        rememberMe = field
        val f = command
        f
    }

    override def undoStep(field: T): T = {
        val sec_rememberMe = field
        val f = rememberMe
        rememberMe = sec_rememberMe
        f
    }

    override def redoStep(field: T): T = {
        rememberMe = field
        val f = command
        f
    }

    def command: T
}