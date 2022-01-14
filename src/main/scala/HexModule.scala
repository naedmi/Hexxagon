package scala

import model.fieldComponent.fieldBaseImpl.{Field, Matrix}
import model.fieldComponent.{FieldInterface, MatrixInterface}
import controller.controllerComponent.{ControllerInterface, controllerBaseImpl}

object HexModule {
  given MatrixInterface[Char] = new Matrix(9, 6)
  given FieldInterface[Char] = new Field()
  given ControllerInterface[Char] = controllerBaseImpl.Controller()
}

object HexModuleTestCase1 {
  given MatrixInterface[Char] = new Matrix(1, 1)
  given FieldInterface[Char] = new Field()
  given ControllerInterface[Char] = controllerBaseImpl.Controller()
}
