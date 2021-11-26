package controller

import GameStatus._
import util.Command
import model.HexField
import model.Matrix

class PlaceCommand(field: HexField, content:Char, x:Int, y:Int) extends Command[HexField] {

    var rememberMe = field.matrix

    override def noStep(field: HexField): HexField = field

    override def doStep(field: HexField): HexField = {
        rememberMe = field.matrix
        field.matrix = field.place(content, x, y)
        field
    }
    override def undoStep(field: HexField): HexField = {
        val sec_rememberMe = field.matrix
        field.matrix = rememberMe
        rememberMe = sec_rememberMe
        field
    }
    override def redoStep(field: HexField): HexField = {
        field.matrix = field.place(content, x, y)
        field
    }
}

class PlaceAllCommand(field: HexField, content:Char) extends Command[HexField] {
    
    var rememberMe = field.matrix

    override def noStep(field: HexField): HexField = field

    override def doStep(field: HexField): HexField = {
        rememberMe = field.matrix
        field.matrix = field.fillAll(content)
        field
    }
    override def undoStep(field: HexField): HexField = {
        field.matrix = field.fillAll(' ')
        field
    }
    override def redoStep(field: HexField): HexField = {
        field.fillAll(content)
        field
    }
}