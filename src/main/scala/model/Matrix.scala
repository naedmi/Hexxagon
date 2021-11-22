package model

import util.ListFactory

case class Matrix(var matrix: Vector[Vector[Char]], var Xcount: Int = 0, var Ocount: Int = 0) {
    def this(columns:Int, rows:Int) = this(Vector.fill[Char](rows, columns)(' '))
    val col = matrix(0).size
    val row = matrix.size
    def cell(col: Int, row: Int):Char = matrix(row)(col)
    // def row(row: Int) = matrix(row)
    def fill(content: Char, x: Int, y: Int): Matrix =
        content match {
            case 'X' => Xcount += 1
            case 'O' => Ocount += 1
        }
        val tolookat = ListFactory(x, y, col - 1, row - 1)
        tolookat.foreach{ 
            case (x, y) => if !matrix(y)(x).equals(content) && !matrix(y)(x).equals(' ') then
             matrix = matrix.updated(y, matrix(y).updated(x, content))
             content match {
                case 'X' => {
                    Xcount += 1
                    Ocount -= 1
                 }
                case 'O' => {
                    Ocount += 1
                    Xcount -= 1
                 }
             }
        }
        matrix = matrix.updated(y, matrix(y).updated(x, content))
        copy(matrix)
}
