package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import scala.annotation.varargs

class MatrixSpec extends AnyWordSpec {
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
            var matrix = new Matrix(5, 4)
            matrix = matrix.fill('O', 1, 0)
            matrix = matrix.fill('X', 0, 0)
            matrix = matrix.fill('O', 2, 0)
            "contain Stone in the Cell" in {
                matrix.Ocount should be (2)
                matrix.Xcount should be (1)
                matrix.cell(0, 0) should be('X')
                matrix.cell(1, 0) should be('O')
                matrix.cell(2, 0) should be('O')
                matrix.cell(0, 1) should be(' ')
            }
        }
        "placing a Stone on top of a Stone" should {
            var matrix = new Matrix(5, 4)
            "Counters should substract 1" in {
                matrix = matrix.fill('O', 2, 2)
                matrix.Ocount should be (1)
                matrix.Xcount should be (0)
                matrix = matrix.fill('X', 2, 2)
                matrix.Ocount should be (0)
                matrix.Xcount should be (1)
            }
        }
        "filled with a Stone" should {
            var matrix = new Matrix(5, 5)
            
            "should only contain 'X'" in {
                matrix = matrix.fillAll('X')
                matrix.Xcount should be (matrix.row * matrix.col)
                matrix.Ocount should be (0)
                matrix.matrix.flatten.contains(' ') should be (false)
                matrix.matrix.flatten.contains('O') should be (false)
                matrix.matrix.flatten.contains('X') should be (true)
            }
            "should only contain 'O'" in {
                matrix = matrix.fillAll('O')
                matrix.Ocount should be (matrix.row * matrix.col)
                matrix.Xcount should be (0)
                matrix.matrix.flatten.contains(' ') should be (false)
                matrix.matrix.flatten.contains('X') should be (false)
                matrix.matrix.flatten.contains('O') should be (true)
            }
            "filling results in new Matrix" in {
                var mat = new Matrix(1, 1)
                mat.fillAll(' ') should be (new Matrix(1, 1))
                mat = mat.fill('X', 0, 0)
                mat.cell(0, 0) should be ('X')
                mat = mat.fillAll(' ')
                mat.cell(0, 0) should be (' ')
            }
        }
    }
}