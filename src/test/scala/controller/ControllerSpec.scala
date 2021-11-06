package controller

import util.Observer
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import scala.model.HexField

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
          "notify its Observer after placing a Stone" in {
            controller.placeX(0, 0)
            obs.updated should be(true)
            controller.hexfield.matrix.cell(0, 0) should be('X')
          }
        }
  }
}
case class Obs() extends Observer:
    var updated = false
    override def update: Unit = updated = true