package controller

enum GameStatus(mes: String):
  case IDLE extends GameStatus("")
  case GAMEOVER extends GameStatus("GAME OVER")
  case TURNPLAYER1 extends GameStatus("Player 1s turn to place X")
  case TURNPLAYER2 extends GameStatus("Player 2s turn to place O")
  def message() = mes
end GameStatus