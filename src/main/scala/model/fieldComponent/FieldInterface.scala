package model.fieldComponent

import model.Matrix

trait FieldInterface {
    val col: Int
    val lines: Int
    var matrix: Matrix
    val eol = "\n"
    def place(c: Char, x: Int, y: Int): Matrix
    def fillAll(c: Char): Matrix
    def field: String
    def reset: FieldInterface
    override def toString = field
}