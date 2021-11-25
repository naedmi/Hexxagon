package model

import util.ListFactory

case class Matrix(matrix: Vector[Vector[Char]], var Xcount: Int = 0, var Ocount: Int = 0) {
    def this(columns:Int, rows:Int) = this(Vector.fill[Char](rows, columns)(' '))
    def MAX = row * col
    val col = matrix(0).size
    val row = matrix.size
    def cell(col: Int, row: Int):Char = matrix(row)(col)
    // def row(row: Int) = matrix(row)
    def fillAll(content: Char): Matrix =
        content match {
            case 'X' => Xcount = row * col; Ocount = 0
            case 'O' => Ocount = row * col; Xcount = 0
            case ' ' => Ocount = 0; Xcount = 0
        }
        copy(Vector.fill[Char](row, col)(content))
    def fill(content: Char, x: Int, y: Int): Matrix = {
        
        if content.equals(' ') then copy(matrix.updated(y, matrix(y).updated(x, content)))

        if !matrix(y)(x).equals(' ') && !matrix(y)(x).equals(content) then
        content match { 
            case 'X' => Xcount += 1; Ocount -= 1;
            case 'O' => Ocount += 1; Xcount -= 1 
        }
        else if matrix(y)(x).equals(content) then this  //prevent from placing X ontop of X
        else content match { 
            case 'X' => Xcount += 1; 
            case 'O' => Ocount += 1 
        }

        var tmpmatrix = matrix
        val tolookat = ListFactory(x, y, col - 1, row - 1)
        tolookat.foreach{ 
            case (x, y) => if !tmpmatrix(y)(x).equals(content) && !tmpmatrix(y)(x).equals(' ') then
             tmpmatrix = tmpmatrix.updated(y, tmpmatrix(y).updated(x, content))
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
        tmpmatrix = tmpmatrix.updated(y, tmpmatrix(y).updated(x, content))
        copy(tmpmatrix)
    }
}
