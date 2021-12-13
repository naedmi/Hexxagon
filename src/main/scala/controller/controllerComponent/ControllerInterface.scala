package controller.controllerComponent

import util.Observable
import model.fieldComponent.FieldInterface
import controller.GameStatus
import model.fieldComponent.FieldInterface

trait ControllerInterface extends Observable{
  var hexfield: FieldInterface
  def gamestatus: GameStatus
  def fillAll(c: Char): Unit
  def place(c: Char, x: Int, y: Int): Unit
  def undo: Unit
  def redo: Unit
  def reset: Unit
}
