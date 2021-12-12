package controller

import model.Field

class PlaceCommand[T <: Field](field: T, content:Char, x:Int, y:Int) extends CommandTemplate(field: T) {
    override def command = field.place(content, x, y)
}

class PlaceAllCommand[T <: Field](field: T, content:Char) extends CommandTemplate(field: T) {
    override def command = field.fillAll(content)
}