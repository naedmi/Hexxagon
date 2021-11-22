package model

case class HexField(col:Int, lines:Int):
    assert(col > 0 && lines > 0 && col < 10 && lines < 10)  // 10 not working with regex
    def this() = this(9,6) // default
    
    var matrix = new Matrix(col, lines)
    if col % 2 == 0 then matrix = new Matrix(col+1, lines)
    
    val eol = "\n"
    def edgetop = " ___ " + "    ___ " * (col/2) + eol
    def edgebot = " " + "   \\___/" * (col/2) + eol

    def placeX(x:Int, y:Int) : Matrix = {
        matrix.fill('X', x, y)
    }

    def placeO(x:Int, y:Int) : Matrix = {
        matrix.fill('O', x, y)
    }

    def bot(line:Int): String = {
        var res = "\\___/"

        matrix.matrix(line).zipWithIndex.foreach(
        (x, i) => if i % 2 != 0 && i >= 1 && i < col then res += " " + x.toString + " \\___/")

        //for (x <- 1 until col - 1 by 2) {
        //  res += " " + matrix(line)(x).toString * condition + " \\___/" * condition
        //}
        res + "\n"
    }

    def top(line:Int): String = {
        var res = "/ " + matrix.matrix(line)(0).toString + " \\"

        matrix.matrix(line).zipWithIndex.foreach(
        (x, i) => if i % 2 == 0 && i >= 2 then res += "___/ " + x + " \\")

        //for (x <- 2 until col by 2) {
        //  res += "___/ " + matrix(line)(x) + " \\"
        //}
        res + "\n"
    }
    
    def field = {
        var res = eol + edgetop
        for (l <- 0 until matrix.row) res += (top(l) + bot(l))
        res += edgebot
        res
    }

    override def toString:String = field
