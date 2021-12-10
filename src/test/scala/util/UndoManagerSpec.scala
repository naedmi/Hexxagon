package util

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec


class UndoManagerSpec extends AnyWordSpec {
    "An UndoManager" should {
    val undoManager = new UndoManager[Int]
    val command = new incrCommand

    "change nothing if there is no step to undo" in {
      var state = 0
      state = undoManager.undoStep(state)
      undoManager.undoStack should be (Nil)
      state should be (0)
    }
    "change nothing if there is no step to redo" in {
      var state = 0
      state = undoManager.redoStep(state)
      undoManager.redoStack should be (Nil)
      state should be (0)
    }
    "have a do, undo and redo" in {
      var state = 0
      state = undoManager.doStep(state, command)
      state should be(1)
      state = undoManager.undoStep(state)
      state should be(0)
      state = undoManager.redoStep(state)
      state should be(1)
    }
    "handle multiple undo steps correctly" in {
      var state = 0
      state = undoManager.doStep(state, command)
      state = undoManager.doStep(state, command)
      state should be(2)
      state = undoManager.undoStep(state)
      state should be(1)
      state = undoManager.undoStep(state)
      state should be(0)
      state = undoManager.redoStep(state)
      state should be(1)
    }
  }
}