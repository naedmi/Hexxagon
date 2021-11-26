package controller

import GameStatus._
import util.{Observable, UndoManager}
import model.HexField

case class Controller(var hexfield: HexField = new HexField()) extends Observable {
    var gamestatus: GameStatus = IDLE
    private val undoManager = new UndoManager[HexField]
    private var laststatus = IDLE

    val GAMEMAX = hexfield.matrix.MAX
    private def checkStat = if hexfield.matrix.Xcount == GAMEMAX || hexfield.matrix.Ocount == GAMEMAX then gamestatus = GAMEOVER

    def create(col:Int = 9, row:Int = 6) = 
        hexfield = new HexField(col, row)
        notifyObservers

    def fillAll(c:Char) =
        undoManager.doStep(hexfield, new PlaceAllCommand(hexfield, c))
        checkStat
        notifyObservers

    def place(c:Char, x: Int, y: Int) =
        if ((gamestatus.equals(TURNPLAYER1) & c.equals('O')) 
            | (gamestatus.equals(TURNPLAYER2) & c.equals('X')))
            print("Not your turn!\n\n")
            notifyObservers
        else
            undoManager.doStep(hexfield, new PlaceCommand(hexfield, c, x, y))
            laststatus = gamestatus
            if checkStat.equals(GAMEOVER) then notifyObservers
            else c match { 
                case 'X' => gamestatus = TURNPLAYER2; 
                case 'O' => gamestatus = TURNPLAYER1;
            }
            notifyObservers
    
    def undo = {
        var mem = gamestatus
        undoManager.undoStep(hexfield)
        gamestatus = laststatus
        laststatus = mem
        notifyObservers
    }

    def redo = {
        undoManager.redoStep(hexfield)
        notifyObservers
    }    

    override def toString = 
        GameStatus.message(gamestatus) + hexfield.toString 
        + "\nX: " + hexfield.matrix.Xcount + "\tO: " + hexfield.matrix.Ocount
        + "\n" + "_"*(4*hexfield.matrix.col+1) + "\n"
}
