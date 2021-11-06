package scala
package controller
import util.Observable
import model.HexField

case class Controller (var hexfield: HexField = new HexField()) extends Observable {

    def create(col:Int = 9, row:Int = 6) = 
        new HexField(col, row)
        notifyObservers

    def placeX(x: Int, y: Int) =
        hexfield.matrix = hexfield.placeX(x, y)
        notifyObservers

    def placeO(x: Int, y: Int) =
        hexfield.matrix = hexfield.placeO(x, y)
        notifyObservers
}
