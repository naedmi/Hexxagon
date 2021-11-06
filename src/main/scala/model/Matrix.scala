package scala
package model

case class Matrix(matrix: Vector[Vector[Char]]) {
    def this(columns:Int, rows:Int) = this(Vector.fill[Char](rows, columns)(' '))
    val col = matrix(0).size
    val row = matrix.size
    def cell(col: Int, row: Int):Char = matrix(row)(col)
    // def row(row: Int) = matrix(row)
    def fill(content: Char, x: Int, y: Int): Matrix =
        copy(matrix.updated(y, matrix(y).updated(x, content)))
}
