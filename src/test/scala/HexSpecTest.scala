package scala

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
class HexSpecTest extends AnyWordSpec {
    "Hex" should {
        "field should be 6 * 10 Hex'" in {
            val hex = new HexField(6);
            hex.field should be("""
 ___     ___     ___     ___     ___  
/   \___/   \___/   \___/   \___/   \
\___/   \___/   \___/   \___/   \___/
/   \___/   \___/   \___/   \___/   \
\___/   \___/   \___/   \___/   \___/
/   \___/   \___/   \___/   \___/   \
\___/   \___/   \___/   \___/   \___/
/   \___/   \___/   \___/   \___/   \
\___/   \___/   \___/   \___/   \___/
/   \___/   \___/   \___/   \___/   \
\___/   \___/   \___/   \___/   \___/
/   \___/   \___/   \___/   \___/   \
\___/   \___/   \___/   \___/   \___/
  """);
        }
        
    }
}