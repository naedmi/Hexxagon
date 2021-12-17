package model.fieldComponent.fieldBaseImpl

import util.SetHandling.DefaultSetHandler
import model.fieldComponent.MatrixInterface

case class Matrix(matrix: Vector[Vector[Char]], var Xcount: Int = 0, var Ocount: Int = 0) extends MatrixInterface[Char] {
    def this(col: Int, row: Int) = {
        this(
        if col % 2 == 0 then 
            Vector.fill[Char](row, col + 1)(' ') 
        else 
            Vector.fill[Char](row, col)(' '), 
        0, 0)
        assert(col > 0 && row > 0 && col < 10 && row < 10)  // 10 not working with regex
    }

    def MAX = row * col
    val col = matrix(0).size
    val row = matrix.size
    // def row(row: Int) = matrix(row)
    
    override def cell(col: Int, row: Int): Char = matrix(row)(col)

    override def fillAll(content: Char): Matrix =
        content match {
            case 'X' => Xcount = MAX; Ocount = 0
            case 'O' => Ocount = MAX; Xcount = 0
            case _ => Ocount = 0; Xcount = 0
        }
        copy(Vector.fill[Char](row, col)(content))

    override def fill(content: Char, x: Int, y: Int): Matrix = {
        if content.equals(' ') then copy(matrix.updated(y, matrix(y).updated(x, content)))

        var tmpmatrix = new DefaultSetHandler().createSetandHandle(content, x, y, matrix)
        tmpmatrix = tmpmatrix.updated(y, tmpmatrix(y).updated(x, content))

        val count = tmpmatrix.flatten.groupBy(identity).map(t => (t._1, t._2.length))
        Xcount = count.get('X') match { case Some(i) => i; case None => 0 }
        Ocount = count.get('O') match { case Some(i) => i; case None => 0 }
        copy(tmpmatrix)
    }
}