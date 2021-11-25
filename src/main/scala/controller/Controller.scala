package controller

import GameStatus._
import util.{Observable, UndoManager}
import model.HexField

case class Controller (var hexfield: HexField = new HexField()) extends Observable {

    var gamestatus: GameStatus = IDLE
    private val undoManager = new UndoManager
    private var laststatus = IDLE

    val GAMEMAX = hexfield.matrix.MAX
    private def checkStat = if hexfield.matrix.Xcount == GAMEMAX || hexfield.matrix.Ocount == GAMEMAX then gamestatus = GAMEOVER

    def create(col:Int = 9, row:Int = 6) = 
        hexfield = new HexField(col, row)
        notifyObservers

    def fillAll(c:Char) =
        undoManager.doStep(new PlaceAllCommand(c, this))
        checkStat
        notifyObservers

    def place(c:Char, x: Int, y: Int) =
        undoManager.doStep(new PlaceCommand(c, x, y, this))
        laststatus = gamestatus
        if checkStat.equals(GAMEOVER) then notifyObservers
        else c match { case 'X' => gamestatus = TURNPLAYER2; case 'O' => gamestatus = TURNPLAYER1 }
        notifyObservers
    
    def undo = {
        var mem = gamestatus
        undoManager.undoStep
        gamestatus = laststatus
        laststatus = mem
        notifyObservers
    }

    def redo = {
        undoManager.redoStep
        notifyObservers
    }    

    override def toString = GameStatus.message(gamestatus) + hexfield.toString + "\nX: " + hexfield.matrix.Xcount + "\tO: " + hexfield.matrix.Ocount
}
