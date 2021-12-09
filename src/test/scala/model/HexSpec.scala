package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import controller.Controller

class HexFieldSpec extends AnyWordSpec {
    info("Note that HexFields have to have an uneven number of columns!")
    "A Hex" when {
    
        "created as 6 - 9 Grid" should {
        
            val hex = new HexField(9, 6)

            "start with the top" in {
                hex.edgetop should be(new HexField(hex.col, 1).edgetop);
            }
            "have lines" in {
                "\n" + hex.edgetop + hex.top(0) + hex.bot(0) + hex.edgebot should be(new HexField(hex.col, 1).field)
            }
            "be equal to a field the same size" in {
                hex.field should be(new HexField(hex.col, hex.lines).field)
            }
            "placing at 8 5 X" in {
                hex.place('X', 8, 5) should be(new Matrix(9, 6).fill('X', 8, 5))
            }
            "be the same size as an 8 - 6 Grid" in {
                new HexField().field should be(new HexField(8, 6).field)
            }
            "could be filled with one method call" in {
                hex.fillAll('X') should be (new Matrix(9, 6).fillAll('X'))
            }
        }

        "created as default Grid" should {
        
            val hex = new HexField()

            "start with the top" in {
                hex.edgetop should be(new HexField(hex.col, 1).edgetop);
            }
            "have lines" in {
                "\n" + hex.edgetop + hex.top(0) + hex.bot(0) + hex.edgebot should be(new HexField(hex.col, 1).field)
            }
            "be equal to a field with the size: 9 - 6" in {
                hex.field should be(new HexField(9, 6).field)
            }
            "be empty in every Cell at the beginning" in {
                hex.matrix.matrix.contains('X') should be(false)
                hex.matrix.matrix.contains('O') should be(false)
                val nhex = new HexField(2, 3)
                nhex.matrix.matrix.contains('X') should be(false)
                nhex.matrix.matrix.contains('O') should be(false)
            }
        }
        "created as Single Cell" should {

            var hex = new HexField(1, 1)
            var contr = new Controller(hex)

            "contain a Space when empty" in {
                hex.matrix.matrix(0)(0) should be(' ')
            }
            "contain a X" in {
                contr.place('X', 0, 0)
                hex.matrix.matrix(0)(0) should be('X')
            }
            "contain a O" in {
                hex = new HexField(1, 1)
                contr = new Controller(hex)
                contr.place('O', 0, 0)
                hex.matrix.matrix(0)(0) should be('O')
            }
            "filled completely" in {
                contr.fillAll('X')
                hex.matrix.matrix.flatten should contain ('X')
                hex.matrix.matrix.flatten should not contain (' ')
                hex.matrix.matrix.flatten should not contain ('O')
            }
        }
    }
}