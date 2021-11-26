package util

import model.HexField
import model.Matrix

trait CommandTemplate(field: HexField) extends Command[HexField] {
    var rememberMe = field.matrix
    override def noStep(field: HexField): HexField = field
    override def doStep(field: HexField): HexField = {
        rememberMe = field.matrix
        field.matrix = command
        field
    }
    override def undoStep(field: HexField): HexField = {
        val sec_rememberMe = field.matrix
        field.matrix = rememberMe
        rememberMe = sec_rememberMe
        field
    }
    override def redoStep(field: HexField): HexField = {
        rememberMe = field.matrix
        field.matrix = command
        field
    }
    def command: Matrix
}