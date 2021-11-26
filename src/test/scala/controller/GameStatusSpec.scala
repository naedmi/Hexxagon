package controller

import GameStatus._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class GameStatusSpec extends AnyWordSpec {
    "A GameStatus" when {
        "map descriptions for each value" in {
            GameStatus.message(IDLE) should be ("")
            GameStatus.message(GAMEOVER) should be ("GAME OVER")
            GameStatus.message(TURNPLAYER1) should be ("Player 1 to place X")
            GameStatus.message(TURNPLAYER2) should be ("Player 2 to place O")
        }
        "have a type with a value of the given ENUMS" in {
            GameStatus.values should be(Set( IDLE, GAMEOVER, TURNPLAYER1, TURNPLAYER2 ))
        }
    }
}