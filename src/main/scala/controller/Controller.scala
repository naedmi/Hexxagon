package controller

import GameStatus._
import util.{Observable, UndoManager}
import model.HexField

case class Controller(var hexfield: HexField = new HexField()) extends Observable {
    var gamestatus: GameStatus = IDLE
    private val undoManager = new UndoManager[HexField]
    private var laststatus = IDLE

    val GAMEMAX = hexfield.matrix.MAX
    private def checkStat = 
        if hexfield.matrix.Xcount == GAMEMAX || hexfield.matrix.Ocount == GAMEMAX then gamestatus = GAMEOVER
        else if matrixisEmpty then gamestatus = IDLE

    def matrixisEmpty = !hexfield.matrix.matrix.flatten.contains('O') && !hexfield.matrix.matrix.flatten.contains('X')

    def fillAll(c:Char) =
        undoManager.doStep(hexfield, new PlaceAllCommand(hexfield, c))
        checkStat
        notifyObservers

    def place(c:Char, x: Int, y: Int) =
        if ((gamestatus.equals(TURNPLAYER1) & c.equals('O')) 
            | (gamestatus.equals(TURNPLAYER2) & c.equals('X')))
            print("\nNot your turn!\n")
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
        if matrixisEmpty then notifyObservers
        else
            var mem = gamestatus
            undoManager.undoStep(hexfield)
            gamestatus = laststatus
            laststatus = mem
            checkStat
            notifyObservers
    }

    def redo = {
        var mem = laststatus
        laststatus = gamestatus
        undoManager.redoStep(hexfield)
        gamestatus = mem
        checkStat
        notifyObservers
    }    

    override def toString = 
        gamestatus.message() + hexfield.toString 
        + "\nX: " + hexfield.matrix.Xcount + "\tO: " + hexfield.matrix.Ocount
        + "\n" + "_" * ( 4 * hexfield.matrix.col + 1 ) + "\n"
}
