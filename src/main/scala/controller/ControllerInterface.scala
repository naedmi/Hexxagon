package controller

import util.Observable
import model.HexField

trait ControllerInterface extends Observable{
  def gamestatus: GameStatus
  def fillAll(c: Char): Unit
  def place(c: Char, x: Int, y: Int): Unit
  def undo: Unit
  def redo: Unit
  def reset: Unit
}
