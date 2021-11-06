package scala
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class MatrixSpecTest extends AnyWordSpec {
    "A Matrix" when {
        "initialized" should {
            "contain only ' '" in {
                val matrix = new Matrix(9, 6)
                matrix.matrix.filter(_.contains(' ')) should be(Vector.fill[Char](matrix.row, matrix.col)(' '))
                matrix.matrix.filter(_.contains('X')) should be(Vector())
                matrix.matrix.filter(_.contains('O')) should be(Vector())
            }
        }
        "placed a Stone" should {
            var matrix = new Matrix(3, 2)
            matrix = matrix.fill('X', 0, 0)
            matrix = matrix.fill('O', 1, 0)
            "contain Stone in the Cell" in {
                matrix.cell(0, 0) should be('X')
                matrix.cell(1, 0) should be('O')
                matrix.cell(2, 0) should be(' ')
            }
        }
    }
}