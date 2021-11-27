package controller

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import util.Observer
import model.HexField

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
          "notify its Observer after filling the Field" in {
            controller.fillAll('O')
            obs.updated should be (true)
            val tmp = controller.hexfield.matrix.matrix.flatten
            tmp.contains('X') should be (false)
            tmp.contains(' ') should be (false)
            tmp.contains('O') should be (true)
          }
          "undo and redo a move" in {
            val stone = controller.hexfield.matrix.cell(2,2)
            controller.place('O', 2, 2)
            controller.hexfield.matrix.cell(2, 2) should be('O')
            controller.undo
            controller.hexfield.matrix.cell(2, 2) should be(stone)
            controller.redo
            controller.hexfield.matrix.cell(2, 2) should be('O')

          }
        }
  }
}
case class Obs() extends Observer:
    var updated = false
    override def update: Unit = updated = true