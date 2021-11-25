package controller

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import model.HexField

class PlaceCommandSpec extends AnyWordSpec {
    "A PlaceCommand" should {
        val hex = new HexField()
        val controller = new Controller(new HexField)
        val command = new PlaceCommand('X', 0, 0, controller)
        "Capture state of hexfield before changing it" in {
            command.doStep
            command.rememberMe should be(new HexField().matrix) 
            // failed: Matrix(Xcount: 1 -> 0)?
        }
        "Place a stone" in {
            command.doStep
            controller.hexfield.matrix.cell(0, 0) should be('X')
        }
        "Redo a step" in {
            command.redoStep
            controller.hexfield.matrix.cell(0,0) should be ('X')
        }
    }
  
}

class PlaceAllCommandSpec extends AnyWordSpec {

}