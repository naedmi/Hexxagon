package controller
import util.Observable
import model.HexField

case class Controller (var hexfield: HexField = new HexField()) extends Observable {

    def create(col:Int = 9, row:Int = 6) = 
        hexfield = new HexField(col, row)
        notifyObservers

    def placeX(x: Int, y: Int) =
        hexfield.matrix = hexfield.placeX(x, y)
        hexfield.matrix.Xcount += 1
        notifyObservers

    def placeO(x: Int, y: Int) =
        hexfield.matrix = hexfield.placeO(x, y)
        hexfield.matrix.Xcount += 1
        notifyObservers
        
    override def toString = hexfield.toString + "\nX: " + hexfield.matrix.Xcount + "\tO: " + hexfield.matrix.Ocount
}
