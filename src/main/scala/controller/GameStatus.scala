package controller

object GameStatus extends Enumeration {
  type GameStatus = Value
  val IDLE, GAMEOVER, TURNPLAYER1, TURNPLAYER2 = Value

  val map = Map[GameStatus, String](
    IDLE -> "",
    GAMEOVER ->"GAME OVER",
    TURNPLAYER1 ->"Player 1 to place X",
    TURNPLAYER2 ->"Player 2 to place O")

  def message(gameStatus: GameStatus) = {
    map(gameStatus)
  }

}