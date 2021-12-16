package controller.controllerComponent

import model.fieldComponent.{FieldInterface, MatrixInterface}
import util.Command

trait CommandTemplate[T <: FieldInterface[Char]](field: T) extends Command[T] {
    var rememberMe = field.matrix
    var rememberCounter = (field.matrix.Xcount, field.matrix.Ocount)
    override def noStep(field: T): T = field
    override def doStep(field: T): T = {
        rememberMe = field.matrix
        rememberCounter = (field.matrix.Xcount, field.matrix.Ocount)
        field.matrix = command
        field
    }
    override def undoStep(field: T) = {
        val sec_rememberMe = field.matrix
        val sec_rememberCounter = (field.matrix.Xcount, field.matrix.Ocount)
        field.matrix = rememberMe
        rememberMe = sec_rememberMe
        rememberCounter match {
            case (x,y) => field.matrix.Xcount = x; field.matrix.Ocount = y
        }
        rememberCounter = sec_rememberCounter
        field
    }
    override def redoStep(field: T) = {
        rememberMe = field.matrix
        rememberCounter = (field.matrix.Xcount, field.matrix.Ocount)
        field.matrix = command
        field
    }
    def command: MatrixInterface[Char]
}