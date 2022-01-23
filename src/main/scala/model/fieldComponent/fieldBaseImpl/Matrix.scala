package model.fieldComponent.fieldBaseImpl

import util.SetHandling.DefaultSetHandler
import model.fieldComponent.MatrixInterface

case class Matrix(matrix: Vector[Vector[Char]], Xcount: Int = 0, Ocount: Int = 0) extends MatrixInterface[Char] {
    def this(col: Int, row: Int) = {
        this(
        if (col < 0 || row < 0 || col > 10 || row > 10) // 10 not working with regex
            Vector.fill[Char](6, 9)(' ')                // default values
        else if col % 2 == 0 then 
            Vector.fill[Char](row, col + 1)(' ') 
        else
            Vector.fill[Char](row, col)(' '), 
        0, 0)
    } 

    def MAX = row * col
    val col = matrix(0).size
    val row = matrix.size
    
    override def cell(col: Int, row: Int): Char = matrix(row)(col)

    override def fillAll(content: Char): Matrix = {
        var o, x = 0
        content match {
            case 'X' => x = MAX; o = 0
            case 'O' => o = MAX; x = 0
            case _ => o = 0; x = 0
        }
        copy(Vector.fill[Char](row, col)(content), x, o)
    }

    override def fill(content: Char, x: Int, y: Int): Matrix = {
        if content.equals(' ') then copy(matrix.updated(y, matrix(y).updated(x, content)))

        var tmpmatrix = new DefaultSetHandler().createSetandHandle(content, x, y, matrix)
        tmpmatrix = tmpmatrix.updated(y, tmpmatrix(y).updated(x, content))

        val count = tmpmatrix.flatten.groupBy(identity).map(t => (t._1, t._2.length))
        copy(tmpmatrix, 
            count.get('X') match { case Some(i) => i; case None => 0 }, 
            count.get('O') match { case Some(i) => i; case None => 0 })
    }

    override def fillAlways(content: Char, x: Int, y: Int): Matrix = {
        var tmpmatrix = matrix.updated(y, matrix(y).updated(x, content))

        val count = tmpmatrix.flatten.groupBy(identity).map(t => (t._1, t._2.length))
        copy(tmpmatrix, 
            count.get('X') match { case Some(i) => i; case None => 0 }, 
            count.get('O') match { case Some(i) => i; case None => 0 })
    }
}