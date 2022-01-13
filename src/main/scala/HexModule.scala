package scala

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import com.google.inject.TypeLiteral
import net.codingwell.scalaguice.ScalaModule
import model.fieldComponent.{FieldInterface, MatrixInterface}
import model.fieldComponent.fieldBaseImpl.{Field, Matrix}
import controller.controllerComponent.{ControllerInterface, controllerBaseImpl}

class HexModule extends AbstractModule {
  override def configure(): Unit = {
    bind[MatrixInterface[Char]](new TypeLiteral[MatrixInterface[Char]] {}).toInstance(new Matrix(9, 6))
    bind[FieldInterface[Char]](new TypeLiteral[FieldInterface[Char]] {}).to(classOf[Field])
    bind[ControllerInterface](new TypeLiteral[ControllerInterface] {}).to(classOf[controllerBaseImpl.Controller])
  }
}