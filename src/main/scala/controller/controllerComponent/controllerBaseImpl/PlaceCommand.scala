package controller.controllerComponent.controllerBaseImpl

import model.fieldComponent.FieldInterface
import controller.controllerComponent.CommandTemplate

class PlaceCommand[T <: FieldInterface[Char]](field: T, content:Char, x:Int, y:Int) extends CommandTemplate(field: T) {
    override def command = field.place(content, x, y).asInstanceOf[T]
}

class PlaceAllCommand[T <: FieldInterface[Char]](field: T, content:Char) extends CommandTemplate(field: T) {
    override def command = field.fillAll(content).asInstanceOf[T]
}