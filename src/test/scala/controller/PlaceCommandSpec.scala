package controller

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import model.fieldComponent.fieldBaseImpl.Field

class PlaceCommandSpec extends AnyWordSpec {
    "A PlaceCommand" should {
        var hex = new Field()
        var emptyHex = new Field()
        val command = new PlaceCommand(hex, 'X', 0, 0)

        "place a stone" in {
            hex = command.doStep(hex)
            hex.matrix.cell(0, 0) should be('X')
        }

        "capture state of field before changing it" in {
            command.rememberMe.matrix should be(new Field().matrix.matrix) 
            // Matrix(Xcount: 1 -> 0)?
        }

        "undo and redo a move" in {
            command.undoStep(hex).matrix.cell(0, 0) should be(' ')
            command.redoStep(hex).matrix.cell(0, 0) should be('X')
        }
    }
}

class PlaceAllCommandSpec extends AnyWordSpec {
    "A PlaceAllCommand" should {
        var hex = new Field()
        var emptyHex = new Field()
        val command = new PlaceAllCommand(hex, 'X')

        "fill every cell" in {
            hex = command.doStep(hex)
            var i = 0
            for (rows <- hex.matrix.matrix) {
                for (col <- rows) 
                    if col.equals('X') then i += 1
            }
            i should be(hex.col*hex.lines)
        }

        "undo and redo a move" in {
            command.undoStep(hex).matrix.matrix should be(new Field().matrix.matrix)
            command.redoStep(hex).matrix.matrix should be(new Field().matrix.fillAll('X').matrix)
        }
    }
}