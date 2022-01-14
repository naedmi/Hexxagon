package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import controller.controllerComponent.controllerBaseImpl.Controller
import model.fieldComponent.fieldBaseImpl._

class FieldSpec extends AnyWordSpec {
    info("Note that Fields have to have an uneven number of columns!")
    "A Hex" when {
        "created as 9 - 6 Grid" should {
            val hex = new Field(using new Matrix(9, 6))

            "start with the top" in {
                hex.edgetop should be (new Field(using new Matrix(hex.matrix.col, 1)).edgetop);
            }
            "have lines" in {
                "\n" + hex.edgetop + hex.top(0) + hex.bot(0) + hex.edgebot should be (new Field(using new Matrix(hex.matrix.col, 1)).field)
            }
            "be equal to a field the same size" in {
                hex.field should be (new Field(using new Matrix(hex.matrix.col, hex.matrix.row)).field)
            }
            "placing at 8 5 X" in {
                hex.place('X', 8, 5).matrix should be (new Matrix(9, 6).fill('X', 8, 5))
            }
            "be the same size as an 8 - 6 Grid" in {
                new Field(using new Matrix(9, 6)).field should be (new Field(using new Matrix(8, 6)).field)
            }
            "be filled with one method call" in {
                hex.fillAll('X').matrix should be (new Matrix(9, 6).fillAll('X'))
            }
        }

        "created as default Grid" should {
            val hex = new Field(using new Matrix(9, 6))

            "start with the top" in {
                hex.edgetop should be (new Field(using new Matrix(hex.matrix.col, 1)).edgetop)
            }
            "have lines" in {
                "\n" + hex.edgetop + hex.top(0) + hex.bot(0) + hex.edgebot should be (new Field(using new Matrix(hex.matrix.col, 1)).field)
            }
            "be equal to a field with the size: 9 - 6" in {
                hex.field should be (new Field(using new Matrix(9, 6)).field)
            }
            "be empty in every Cell at the beginning" in {
                hex.matrix.matrix.contains('X') should be (false)
                hex.matrix.matrix.contains('O') should be (false)
                val nhex = new Field(using new Matrix(2, 3))
                nhex.matrix.matrix.contains('X') should be (false)
                nhex.matrix.matrix.contains('O') should be (false)
            }
        }
        "created as Single Cell" should {

            var contr = new Controller(using new Field(using new Matrix(1, 1)))

            "contain a Space when empty" in {
                contr.hexfield.matrix.matrix(0)(0) should be (' ')
            }
            "contain a X" in {
                contr.place('X', 0, 0)
                contr.hexfield.matrix.matrix(0)(0) should be ('X')
            }
            "contain a O" in {
                contr = new Controller(using new Field(using new Matrix(1, 1)))
                contr.place('O', 0, 0)
                contr.hexfield.matrix.matrix(0)(0) should be ('O')
            }
            "be filled completely" in {
                contr.fillAll('X')
                contr.hexfield.matrix.matrix.flatten should contain ('X')
                contr.hexfield.matrix.matrix.flatten should not contain (' ')
                contr.hexfield.matrix.matrix.flatten should not contain ('O')
            }
        }
    }
}