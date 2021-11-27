package controller

import model.HexField
import model.Matrix
import util.Command

trait CommandTemplate(field: HexField) extends Command[HexField] {
    var rememberMe = field.matrix
    var rememberCounter = (field.matrix.Xcount, field.matrix.Ocount)
    override def noStep(field: HexField): HexField = field
    override def doStep(field: HexField): HexField = {
        rememberMe = field.matrix
        rememberCounter = (field.matrix.Xcount, field.matrix.Ocount)
        field.matrix = command
        field
    }
    override def undoStep(field: HexField): HexField = {
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
    override def redoStep(field: HexField): HexField = {
        rememberMe = field.matrix
        rememberCounter = (field.matrix.Xcount, field.matrix.Ocount)
        field.matrix = command
        field
    }
    def command: Matrix
}