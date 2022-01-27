package controller.controllerComponent.controllerBaseImpl

import scala.HexModule
import controller.GameStatus
import controller.GameStatus._
import util.{Observable, UndoManager}
import model.fieldComponent.FieldInterface
import controller.controllerComponent.ControllerInterface
import controller.controllerComponent.controllerBaseImpl.{ PlaceCommand, PlaceAllCommand }

class Controller (using var hexfield: FieldInterface[Char]) 
    extends ControllerInterface[Char] {

    var gamestatus: GameStatus = IDLE
    private var savedstatus = IDLE
    private val undoManager = new UndoManager[FieldInterface[Char]]
    private var laststatus = IDLE
    val GAMEMAX = hexfield.matrix.MAX

    private def checkStat = 
        if hexfield.matrix.Xcount == GAMEMAX 
        || hexfield.matrix.Ocount == GAMEMAX 
        || hexfield.matrix.Ocount + hexfield.matrix.Xcount == GAMEMAX 
        then gamestatus = GAMEOVER
        else if emptyMatrix then gamestatus = IDLE

    private def emptyMatrix = !hexfield.matrix.matrix.flatten.contains('O') && !hexfield.matrix.matrix.flatten.contains('X')

    override def fillAll(c: Char) =
        hexfield = undoManager.doStep(hexfield, new PlaceAllCommand(hexfield, c))
        checkStat
        notifyObservers

    override def place(c: Char, x: Int, y: Int) =
        if ((gamestatus.equals(TURNPLAYER1) & c.equals('O')) 
            || (gamestatus.equals(TURNPLAYER2) & c.equals('X')))
            print("\nNot your turn!\n")
            notifyObservers
        else if !hexfield.matrix.cell(x, y).equals(' ') then 
            println("\nOccupied.")
            notifyObservers
        else
            undoManager.redoStack = Nil
            hexfield = undoManager.doStep(hexfield, new PlaceCommand(hexfield, c, x, y))
            laststatus = gamestatus
            checkStat
            if gamestatus == GAMEOVER then notifyObservers
            else c match { 
                case 'X' => gamestatus = TURNPLAYER2; 
                case 'O' => gamestatus = TURNPLAYER1;
            }
            notifyObservers
    
    override def undo = {
        if emptyMatrix || gamestatus == GAMEOVER then notifyObservers
        else
            var mem = gamestatus
            hexfield = undoManager.undoStep(hexfield)
            gamestatus = laststatus
            laststatus = mem
            checkStat
            notifyObservers
    }

    override def redo = {
        if undoManager.redoStack == Nil then notifyObservers
        else
            if laststatus == IDLE then laststatus = TURNPLAYER1
            var mem = laststatus
            laststatus = gamestatus
            hexfield = undoManager.redoStep(hexfield)
            gamestatus = mem
            checkStat
            notifyObservers
    }    

    override def reset = {
        hexfield = hexfield.reset
        gamestatus = IDLE
        undoManager.undoStack = Nil
        undoManager.redoStack = Nil
        notifyObservers
    }

    override def save = {
        HexModule.given_FileIOInterface.save(hexfield)
        savedstatus = gamestatus
        notifyObservers
    }

    override def load = {
        hexfield = HexModule.given_FileIOInterface.load
        gamestatus = savedstatus
        checkStat
        notifyObservers
    }

    override def toString = 
        gamestatus.message() + hexfield.toString 
        + "\nX: " + hexfield.matrix.Xcount + "\tO: " + hexfield.matrix.Ocount
        + "\n" + "_" * ( 4 * hexfield.matrix.col + 1 ) + "\n"
}
