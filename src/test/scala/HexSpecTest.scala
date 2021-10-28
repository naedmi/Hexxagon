package scala

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class HexSpecTest extends AnyWordSpec {
    info("Note that HexFields have to have an uneven number of columns!")
    "A Hex" when {
    
        "created as 6 - 9 Grid" should {
        
            val hex = new HexField(6, 9)

            "start with the top" in {
                hex.edgetop should be(new HexField(1, hex.col).edgetop);
            }
            "have lines" in {
                "\n" + hex.edgetop + hex.top(0) + hex.bot should be(new HexField(1, hex.col).field)
            }
            "be equal to a field the same size" in {
                hex.field should be(new HexField(hex.lines, hex.col).field)
            }
        }

        "created as default Grid" should {
        
            val hex = new HexField()

            "start with the top" in {
                hex.edgetop should be(new HexField(1, hex.col).edgetop);
            }
            "have lines" in {
                "\n" + hex.edgetop + hex.top(0) + hex.bot should be(new HexField(1, hex.col).field)
            }
            "be equal to a field with the size: 6 - 9" in {
                hex.field should be(new HexField(6, 9).field)
            }
        }

        "created as empty Grid" should {

            val hex = new HexField(0, 0)

            "not contain a top" in {
                hex.edgetop should be("");
            }
            "not have any lines" in {
                hex.edgetop + hex.top(0) + hex.bot should be(new HexField(0, 0).field)
            }
            "be empty when printed completly" in {
                hex.field should be("")
            }
        }

        "created as Single Cell" should {

            val hex = new HexField(1, 1)

            "contain nothing" in {
                hex.matrix(0)(0) should be(' ')
            }
            "or contain a X" in {
                hex.placeX(0, 0)
                hex.matrix(0)(0) should be('X')
            }
            "or contain a O" in {
                hex.placeO(0, 0)
                hex.matrix(0)(0) should be('O')
            }
        }
    }
}