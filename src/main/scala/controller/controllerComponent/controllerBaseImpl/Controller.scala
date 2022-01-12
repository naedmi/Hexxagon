package controller.controllerComponent.controllerBaseImpl

import scala.HexModule
import controller.GameStatus
import controller.GameStatus._
import com.google.inject.Inject
import com.google.inject.name.Names
import util.{Observable, UndoManager}
import com.google.inject.{Guice, Inject}
import model.fieldComponent.FieldInterface
import net.codingwell.scalaguice.InjectorExtensions._
import controller.controllerComponent.ControllerInterface
import controller.controllerComponent.controllerBaseImpl.PlaceCommand
import controller.controllerComponent.controllerBaseImpl.PlaceAllCommand

case class Controller @Inject() (var hexfield: FieldInterface[Char]) 
    extends ControllerInterface[Char] {

    var gamestatus: GameStatus = IDLE
    private val undoManager = new UndoManager[FieldInterface[Char]]
    private var laststatus = IDLE
    val GAMEMAX = hexfield.matrix.MAX

    def this() = {
        this(Guice.createInjector(new HexModule).getInstance(classOf[FieldInterface[Char]]))
    }

    private def checkStat = 
        if hexfield.matrix.Xcount == GAMEMAX || hexfield.matrix.Ocount == GAMEMAX || hexfield.matrix.Ocount + hexfield.matrix.Xcount == GAMEMAX then gamestatus = GAMEOVER
        else if emptyMatrix then gamestatus = IDLE

    def emptyMatrix = !hexfield.matrix.matrix.flatten.contains('O') && !hexfield.matrix.matrix.flatten.contains('X')

    override def fillAll(c: Char) =
        undoManager.doStep(hexfield, new PlaceAllCommand(hexfield, c))
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
            undoManager.doStep(hexfield, new PlaceCommand(hexfield, c, x, y))
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
            undoManager.undoStep(hexfield)
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
            undoManager.redoStep(hexfield)
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

    override def toString = 
        gamestatus.message() + hexfield.toString 
        + "\nX: " + hexfield.matrix.Xcount + "\tO: " + hexfield.matrix.Ocount
        + "\n" + "_" * ( 4 * hexfield.matrix.col + 1 ) + "\n"
}
