package controller

import model.fieldComponent.FieldInterface

class PlaceCommand[T <: FieldInterface](field: T, content:Char, x:Int, y:Int) extends CommandTemplate(field: T) {
    override def command = field.place(content, x, y)
}

class PlaceAllCommand[T <: FieldInterface](field: T, content:Char) extends CommandTemplate(field: T) {
    override def command = field.fillAll(content)
}