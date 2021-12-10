package controller

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import util.Observer
import model.HexField
import GameStatus._

class ControllerSpec extends AnyWordSpec {
    "A Controller" when {
        "observed by an Observer" should {
          val field = new HexField()
          val controller = new Controller(field)
          val obs = new Obs()
          controller.add(obs)
          "notify its Observer after placing a stone" in {
            controller.place('X', 0, 0)
            obs.updated should be (true)
            controller.hexfield.matrix.cell(0, 0) should be('X')
          }
          "notify its observer after filling the Field" in {
            controller.fillAll('O')
            obs.updated should be (true)
            val tmp = controller.hexfield.matrix.matrix.flatten
            tmp.contains('X') should be (false)
            tmp.contains(' ') should be (false)
            tmp.contains('O') should be (true)
          }
          "notify its observers after resetting" in {
            controller.reset
            obs.updated should be (true)
            val tmp = controller.hexfield.matrix.matrix.flatten
            tmp.contains('X') should be (false)
            tmp.contains(' ') should be (true)
            tmp.contains('O') should be (false)
          }
        }
        "having a step made" should {
          val field = new HexField()
          val controller = new Controller(field)
          val obs = new Obs()
          controller.add(obs)
          "undo and redo a move" in {
            val stone = controller.hexfield.matrix.cell(2,2)
            val orig_status = controller.gamestatus
            controller.place('O', 2, 2)
            val status = controller.gamestatus
            controller.hexfield.matrix.cell(2, 2) should be('O')
            controller.undo
            controller.gamestatus should be (orig_status)
            controller.hexfield.matrix.cell(2, 2) should be(stone)
            controller.redo
            controller.gamestatus should be (status)
            controller.hexfield.matrix.cell(2, 2) should be('O')
          }
          "change its gamestatus dependent on players turn" in {
            val status = controller.gamestatus
            if (status == TURNPLAYER1) then
              controller.place('X', 1, 1)
              controller.gamestatus should be (TURNPLAYER2)
            else if (status == TURNPLAYER2) then
              controller.place('=', 2, 2)
              controller.gamestatus should be (TURNPLAYER1)
          }
          "don't place a stone if it's not the players turn" in {
            val stone = controller.hexfield.matrix.cell(1, 1)
            controller.place('X', 0, 0)
            controller.place('X', 1, 1)
            obs.updated should be (true)
            controller.hexfield.matrix.cell(1, 1) should be(stone)
            val stone2 = controller.hexfield.matrix.cell(4, 4)
            controller.place('O', 3, 3)
            controller.place('O', 4, 4)
            obs.updated should be (true)
            controller.hexfield.matrix.cell(4, 4) should be(stone2)
          }
        }
        "filled" should {
          val field = new HexField(1, 1)
          val controller = new Controller(field)
          val obs = new Obs()
          controller.add(obs)
          "define a gameover" in {
            controller.place('X', 0, 0)
            controller.gamestatus should be (GAMEOVER)
            controller.fillAll('X')
            controller.gamestatus should be (GAMEOVER)
            controller.fillAll('O')
            controller.gamestatus should be (GAMEOVER)
          }
        }
  }
}
case class Obs() extends Observer:
    var updated = false
    override def update: Unit = updated = true