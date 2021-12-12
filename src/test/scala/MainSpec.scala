import controller.Controller
import aview.TUI
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import model.HexField

class MainSpec extends AnyWordSpec {
    "The Main Program" when {
        "starting" should {
            val controller = Controller(new HexField())
            val tui = TUI(controller)
            "print a Start-Message" in {
                starter.start should be ("\nWelcome to Hexxagon!\n" + tui.message + controller)
            }
        }
    }
}