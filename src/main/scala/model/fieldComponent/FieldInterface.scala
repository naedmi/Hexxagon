package model.fieldComponent

import model.fieldComponent.MatrixInterface

trait FieldInterface[T] {
    val col: Int
    val lines: Int
    var matrix: MatrixInterface[T]
    val eol = "\n"
    def place(c: T, x: Int, y: Int): MatrixInterface[T]
    def fillAll(c: T): MatrixInterface[T]
    def field: String
    def reset: FieldInterface[T]
    override def toString = field
}