package model.fieldComponent

trait MatrixInterface[T]() {
    var Xcount: Int
    var Ocount: Int
    def matrix: Vector[Vector[T]]
    def row: Int
    def col: Int
    def MAX: Int
    
    def cell(col: Int, row: Int) : T
    def fillAll(content: T) : MatrixInterface[T]
    def fill(content: T, x: Int, y: Int): MatrixInterface[T]
}
