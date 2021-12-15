package model.fieldComponent.fieldBaseImpl

import model.fieldComponent.FieldInterface
import model.fieldComponent.MatrixInterface

case class Field(col: Int, lines: Int) extends FieldInterface[Char]:
    assert(col > 0 && lines > 0 && col < 10 && lines < 10)  // 10 not working with regex
    def this() = this(9,6) // default
    
    var matrix = new Matrix(col, lines)
    if col % 2 == 0 then matrix = new Matrix(col+1, lines)
    
    def edgetop = " ___ " + "    ___ " * (col/2) + eol
    def edgebot = " " + "   \\___/" * (col/2) + eol

    override def fillAll(c: Char): MatrixInterface[Char] = matrix.fillAll(c)

    override def place(c: Char, x: Int, y: Int): MatrixInterface[Char] = {
        matrix.fill(c, x, y)
    }

    def bot(line: Int): String = {
        var res = "\\___/"
        
        matrix.matrix(line).zipWithIndex.foreach(
        (x, i) => if i % 2 != 0 && i >= 1 && i < col then res += " " + x.toString + " \\___/")

        res + "\n"
    }

    def top(line: Int): String = {
        var res = "/ " + matrix.matrix(line)(0).toString + " \\"

        matrix.matrix(line).zipWithIndex.foreach(
        (x, i) => if i % 2 == 0 && i >= 2 then res += "___/ " + x + " \\")

        res + "\n"
    }

    override def reset = copy(col, lines)
    
    override def field = {
        var res = eol + edgetop
        for (l <- 0 until matrix.row) res += (top(l) + bot(l))
        res += edgebot
        res
    }
