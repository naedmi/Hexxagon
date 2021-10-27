package scala

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
class HexSpecTest extends AnyWordSpec {
    "A Hex" when {
        "created as 6 - 9 Grid" should {
        
        val hex = new HexField(6, 4);

            "always start with the top" in {
                hex.edgetop should be(new HexField(hex.lines, hex.col).edgetop);
            }
            "continue with lines like this" in {
                "\n" + hex.edgetop + hex.top + hex.bot should be(new HexField(1, hex.col).field)
            }
            "result as final field" in {
                hex.field should be(new HexField(6, 4).field)
            }
        }
    }
}