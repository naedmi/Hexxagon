package model

trait Field {
    val col: Int
    val lines: Int
    var matrix: Matrix
    val eol = "\n"
    def place(c: Char, x: Int, y: Int): Matrix
    def fillAll(c: Char): Matrix
    def field: String
    def reset: Field
    override def toString = field
}