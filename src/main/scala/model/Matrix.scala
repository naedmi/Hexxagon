package model

case class Matrix(matrix: Vector[Vector[Char]]) {
    var Xcount = 0
    var Ocount = 0
    def this(columns:Int, rows:Int) = this(Vector.fill[Char](rows, columns)(' '))
    val col = matrix(0).size
    val row = matrix.size
    def cell(col: Int, row: Int):Char = matrix(row)(col)
    // def row(row: Int) = matrix(row)
    def fill(content: Char, x: Int, y: Int): Matrix =
        var ibound = y - 1
        if x % 2 == 0 then
            ibound += 1
        var tolookat : List[(Int, Int)] = List()
        tolookat:+((x, y - 1))
        tolookat:+((x, y + 1))
        tolookat:+((x - 1, ibound))
        tolookat:+((x - 1, ibound + 1))
        tolookat:+((x + 1, ibound))
        tolookat:+((x + 1, ibound + 1))
        tolookat.foreach{ 
        case (x, y) => if !matrix(y)(x).equals(content) && !matrix(y)(x).equals(' ') then
             matrix.updated(y, matrix(y).updated(x, content))
             content match {
                 case 'X' | 'x' => Xcount += 1
                 case 'O' | 'o' => Ocount += 1
             }
        }
        copy(matrix.updated(y, matrix(y).updated(x, content)))
}
