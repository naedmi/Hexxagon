package model.fieldComponent

import model.fieldComponent.MatrixInterface

/** Interface to implement the game board. */
trait FieldInterface[T] {
    /** Number of columns of the field. */
    // val col: Int

    /** Number of lines of the field. */
    // val lines: Int

    /** Matrix that stores the content of the fields cells. */
    var matrix: MatrixInterface[T]

    /** Fills every cell of the game board with a given element. 
    * 
    * @param c the element to fill the board with
    * @return an updated instance of the matrix
    */
    def fillAll(c: T): MatrixInterface[T]

    /** Places an element in a cell.
     * 
     * @param c the element to fill the cell with
     * @param x x coordinate of the cell
     * @param y y coordinate of the cell
     * @return an updated instance of the matrix
    */
    def place(c: T, x: Int, y: Int): MatrixInterface[T]

    /** Returns a string representation of the field. */
    def field: String

    /** Resets the field. 
     * 
     * @return the reset field
    */
    def reset: FieldInterface[T]

    override def toString = field
}