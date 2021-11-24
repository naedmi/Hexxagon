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
          "notify its Observer after creation" in {
            controller.create()
            obs.updated should be(true)
            controller.hexfield.matrix.col should be(9)
          }
          "notify its Observer after placing a stone" in {
            controller.placeX(0, 0)
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
        }
  }
}
case class Obs() extends Observer:
    var updated = false
    override def update: Unit = updated = true