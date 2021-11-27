package model

import util.SetHandling.DefaultSetHandler

case class Matrix(matrix: Vector[Vector[Char]], var Xcount: Int = 0, var Ocount: Int = 0) {
    def this(columns:Int, rows:Int) = this(Vector.fill[Char](rows, columns)(' '), 0, 0)
    def MAX = row * col
    val col = matrix(0).size
    val row = matrix.size
    // def row(row: Int) = matrix(row)
    
    def cell(col: Int, row: Int):Char = matrix(row)(col)

    def fillAll(content: Char): Matrix =
        content match {
            case 'X' => Xcount = MAX; Ocount = 0
            case 'O' => Ocount = MAX; Xcount = 0
            case ' ' => Ocount = 0; Xcount = 0
        }
        copy(Vector.fill[Char](row, col)(content))

    def fill(content: Char, x: Int, y: Int): Matrix = {
        if content.equals(' ') then copy(matrix.updated(y, matrix(y).updated(x, content)))

        var tmpmatrix = new DefaultSetHandler().createSetandHandle(content, x, y, matrix)
        tmpmatrix = tmpmatrix.updated(y, tmpmatrix(y).updated(x, content))

        val count = tmpmatrix.flatten.groupBy(identity).map(t => (t._1, t._2.length))
        Xcount = count.get('X') match { case Some(i) => i; case None => 0 }
        Ocount = count.get('O') match { case Some(i) => i; case None => 0 }
        copy(tmpmatrix)
    }
}
