package aview

import util.Observer
import controller.Controller
import model.HexField
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import aview.TUI

class TUISpec extends AnyWordSpec {
    "A TUI" when {
        val tui = TUI(Controller(new HexField()))
        "created" should {
            "have regex in matching smth like 0 0 X" in {
                tui.matchReg(tui.reg.findFirstIn("0 0 X")) should be("0 0 X")
            }
            "not match smth like X 1 1" in {
                tui.matchReg(tui.reg.findFirstIn("X 1 1")) should be("Wrong Input")
            }
        }  
    }
}