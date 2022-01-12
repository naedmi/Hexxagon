package scala

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import com.google.inject.TypeLiteral
import net.codingwell.scalaguice.ScalaModule
import model.fieldComponent.{FieldInterface, MatrixInterface}
import model.fieldComponent.fieldBaseImpl.{Field, Matrix}
import controller.controllerComponent.{ControllerInterface, controllerBaseImpl}

class HexModule extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[FieldInterface[Char]](new TypeLiteral[FieldInterface[Char]]() {}).to(classOf[Field])
    bind[MatrixInterface[Char]](new TypeLiteral[MatrixInterface[Char]]() {}).to(classOf[Matrix])
    bind[ControllerInterface].to[controllerBaseImpl.Controller]
  }
}
