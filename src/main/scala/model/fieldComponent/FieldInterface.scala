package model.fieldComponent

/** Interface to implement the game board. */
trait FieldInterface[T] {

    /** Matrix that stores the content of the fields cells. */
    val matrix: MatrixInterface[T]

    /** Fills every cell of the game board with a given element. 
    * 
    * @param c the element to fill the board with
    * @return an updated instance of the field
    */
    def fillAll(c: T): FieldInterface[T]

    /** Places an element in a cell.
     * 
     * @param c the element to fill the cell with
     * @param x x coordinate of the cell
     * @param y y coordinate of the cell
     * @return an updated instance of the field
    */
    def place(c: T, x: Int, y: Int): FieldInterface[T]

    /** Returns a string representation of the field. */
    def field: String

    /** Resets the field. 
     * 
     * @return the reset field
    */
    def reset: FieldInterface[T]

    override def toString = field
}