package controller

import model.HexField
import util.CommandTemplate

class PlaceCommand(field: HexField, content:Char, x:Int, y:Int) extends CommandTemplate(field: HexField) {
    override def command = field.place(content, x, y)
}

class PlaceAllCommand(field: HexField, content:Char) extends CommandTemplate(field: HexField) {
    override def command = field.fillAll(content)
}