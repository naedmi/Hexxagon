package controller.controllerComponent

import util.Observable
import controller.GameStatus
import com.google.inject.Inject
import model.fieldComponent.FieldInterface

/** Interface to implement the controller unit. */
trait ControllerInterface[T] extends Observable {
  /** Field of the game. */
  var hexfield: FieldInterface[T]

  /** Returns the status of the game. */
  def gamestatus: GameStatus

  /** Fills every cell of the game board with a given character. 
   * 
   * @param c the character to fill the board with
  */
  def fillAll(c: T): Unit

  /** Places a character in a cell.
   * 
   * @param c the character to fill the cell with
   * @param x x coordinate of the cell
   * @param y y coordinate of the cell
  */
  def place(c: T, x: Int, y: Int): Unit

  /** Undoes the last change of the game. */
  def undo: Unit

  /** Redoes the last undone step. */
  def redo: Unit

  /** Resets the game board and its status. */
  def reset: Unit
}