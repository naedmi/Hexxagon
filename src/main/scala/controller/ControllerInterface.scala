package controller

trait ControllerInterface {
  def gamestatus: GameStatus
  def fillAll(c: Char): Unit
  def place(c: Char, x: Int, y: Int): Unit
  def undo: Unit
  def redo: Unit
  def reset: Unit
}
