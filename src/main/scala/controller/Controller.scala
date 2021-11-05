package controller
import util.Observable
import model.HexField

case class Controller (var hexfield: HexField = new HexField()) extends Observable {
    def placeX(x: Int, y: Int) =
        hexfield.matrix = hexfield.placeX(x, y)
        notifyObservers

    def placeO(x: Int, y: Int) =
        hexfield.matrix = hexfield.placeO(x, y)
        notifyObservers
}
