package scala

import model.fieldComponent.fieldBaseImpl.{Field, Matrix}
import model.fieldComponent.{FieldInterface, MatrixInterface}
import controller.controllerComponent.{ControllerInterface, controllerBaseImpl}
import model.fileIOComponent.FileIOInterface
import model.fileIOComponent.fileIOJsonImpl.FileIO
// import model.fileIOComponent.fileIOXMLImpl.FileIO

object HexModule {
  given MatrixInterface[Char] = new Matrix(9, 6)
  given FieldInterface[Char] = new Field()
  given ControllerInterface[Char] = controllerBaseImpl.Controller()
  given FileIOInterface = FileIO()
}

class FlexibleModule(rows: Int, cols: Int) {
  given MatrixInterface[Char] = new Matrix(cols, rows)
  given FieldInterface[Char] = new Field()
  given ControllerInterface[Char] = controllerBaseImpl.Controller()
  given FileIOInterface = FileIO()
}
