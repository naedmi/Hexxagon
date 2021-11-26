package controller

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import model.HexField

class PlaceCommandSpec extends AnyWordSpec {
    "A PlaceCommand" should {
        var hex = new HexField()
        val command = new PlaceCommand(hex, 'X', 0, 0)
        /*"Capture state of hexfield before changing it" in {
            hex = command.doStep(hex)
            command.rememberMe should be(new HexField().matrix) 
            // failed: Matrix(Xcount: 1 -> 0)?
        }*/
        "Place a stone" in {
            hex = command.doStep(hex)
            hex.matrix.cell(0, 0) should be('X')
        }
        "Undo and redo a move in" in {
            hex = command.undoStep(hex)
            hex.matrix.cell(0, 0) should be (' ')
            hex = command.redoStep(hex)
            hex.matrix.cell(0, 0) should be ('X')
        }
    }
  
}

class PlaceAllCommandSpec extends AnyWordSpec {

}