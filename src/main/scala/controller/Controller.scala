package controller

import GameStatus._
import util.Observable
import model.HexField

case class Controller (var hexfield: HexField = new HexField()) extends Observable {

    var gamestatus: GameStatus = IDLE

    def create(col:Int = 9, row:Int = 6) = 
        hexfield = new HexField(col, row)
        notifyObservers

    def fillAll(c:Char) =
        hexfield.matrix = 
        c match {
            case 'X' => hexfield.fillAllX()
            case 'O' => hexfield.fillAllO()    
        }
        notifyObservers

    def placeX(x: Int, y: Int) =
        hexfield.matrix = hexfield.placeX(x, y)
        notifyObservers

    def placeO(x: Int, y: Int) =
        hexfield.matrix = hexfield.placeO(x, y)
        notifyObservers
        
    override def toString = hexfield.toString + "\nX: " + hexfield.matrix.Xcount + "\tO: " + hexfield.matrix.Ocount
}
