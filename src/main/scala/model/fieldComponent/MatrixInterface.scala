package model.fieldComponent

/** Interface to implement the cells of the game board as a matrix. */
trait MatrixInterface[T]() {
    /** Number of cells occupied by player one. */
    val Xcount: Int

    /** Number of cells occupied by player two. */
    val Ocount: Int

    /** The matrix as a vector of vectors. */
    def matrix: Vector[Vector[T]]

    /** Number of rows. */
    def row: Int

    /** Number of columns. */
    def col: Int

    /** Number of cells. */
    def MAX: Int
    
    /** Returns the value of a particular cell. 
     * 
     * @param col number of the column
     * @param row number of the row
     * @return content of the cell
    */
    def cell(col: Int, row: Int) : T

    /** Fills every cell of the matrix with a given element. 
    * 
    * @param c the element to fill the matrix with
    * @return an updated instance of the matrix
    */
    def fillAll(content: T) : MatrixInterface[T]

    /** Fills a cell with a given element.
     * 
     * @param c the element to fill the cell with
     * @param x x coordinate of the cell
     * @param y y coordinate of the cell
     * @return an updated instance of the matrix
    */
    def fill(content: T, x: Int, y: Int): MatrixInterface[T]
}
