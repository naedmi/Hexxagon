package aview

import scala.controller.Controller
import scala.model.HexField
import scala.aview.TUI
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class TUISpec extends AnyWordSpec {
    "A TUI" when {
        val hex = new HexField()
        val tui = TUI(Controller(hex))
        "created" should {
            "have regex in matching smth like 0 0 X" in {
                val in = "0 0 X"
                var t = false
                in match {
                    case tui.reg(_*) => t = true
                    case _ =>
                }
                t should be(true)
                tui.handleInput(in) should be("")
            }
            "not match smth like X 1 1" in {
                val in = "X 1 1"
                var t = false
                in match {
                    case tui.reg(_*) => t = true
                    case _ =>
                }
                t should be(false)
                tui.handleInput(in) should be("Wrong Input")
            }
            "exit when q | exit | quit | ..." in {
                tui.handleInput("q") should be("Exiting.")
            }
        }  
    }
}