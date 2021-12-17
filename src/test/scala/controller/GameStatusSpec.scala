package controller

import GameStatus._
import controllerComponent.controllerBaseImpl._
import model.fieldComponent.fieldBaseImpl._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class GameStatusSpec extends AnyWordSpec {
    "A GameStatus" should {
        "have map descriptions for its status" in {
            IDLE.message() should be ("")
            GAMEOVER.message() should be ("GAME OVER")
        }
        "have map descriptions for each players turn" in {
            TURNPLAYER1.message() should be ("Player 1 to place X")
            TURNPLAYER2.message() should be ("Player 2 to place O")
        }
        "be controlled via Controller" in {
            val c = Controller(new Field(new Matrix(9, 6)))
            c.place('X', 0, 0)
            c.gamestatus should be (TURNPLAYER2)
            c.gamestatus.message() should be (TURNPLAYER2.message())
            c.place('O', 1, 0)
            c.gamestatus should be (TURNPLAYER1)
            c.gamestatus.message() should be (TURNPLAYER1.message())
        }
        "have a type with a value of the given ENUMS" in {
            GameStatus.values should be(Array( IDLE, GAMEOVER, TURNPLAYER1, TURNPLAYER2 ))
        }
    }
}