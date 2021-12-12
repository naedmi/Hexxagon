package controller

import util.Observable
import model.Field

trait ControllerInterface extends Observable{
  var hexfield: Field
  def gamestatus: GameStatus
  def fillAll(c: Char): Unit
  def place(c: Char, x: Int, y: Int): Unit
  def undo: Unit
  def redo: Unit
  def reset: Unit
}
