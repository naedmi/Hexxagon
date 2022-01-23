package util

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec


class UndoManagerSpec extends AnyWordSpec {
    "An UndoManager" should {
    val undoManager = new UndoManager[Int]
    val command = new incrCommand

    "change nothing if there is no step to undo" in {
      undoManager.undoStep(0) should be (0)
      undoManager.undoStack should be (Nil)
    }
    "change nothing if there is no step to redo" in {
      undoManager.redoStep(0) should be (0)
      undoManager.redoStack should be (Nil)
    }
    "have a do, undo and redo" in {
      var state = 0
      state = undoManager.doStep(state, command)
      state should be (1)
      state = undoManager.undoStep(1)
      state should be (0)
      undoManager.undoStack should be (Nil)
      undoManager.redoStack should be (command :: Nil)
      state = undoManager.redoStep(state)
      state should be (1)
      undoManager.undoStack should be (command :: Nil)
      undoManager.redoStack should be (Nil)
    }
    "handle multiple undo steps correctly" in {
      var state = 0
      state = undoManager.doStep(state, command)
      undoManager.undoStack should be (command :: command :: Nil)
      undoManager.redoStack should be (Nil)
      state = undoManager.doStep(state, command)
      undoManager.undoStack should be (command :: command :: command :: Nil)
      undoManager.redoStack should be (Nil)
      state should be (2)
      state = undoManager.undoStep(state)
      undoManager.undoStack should be (command :: command :: Nil)
      undoManager.redoStack should be (command :: Nil)
      state should be (1)
      state = undoManager.undoStep(state)
      undoManager.undoStack should be (command :: Nil)
      undoManager.redoStack should be (command :: command :: Nil)
      state should be (0)
      state = undoManager.redoStep(state)
      undoManager.undoStack should be (command :: command :: Nil)
      undoManager.redoStack should be (command :: Nil)
      state should be (1)
    }
  }
}