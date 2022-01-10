import controller.controllerComponent._
import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import model.fieldComponent._
import model.fieldComponent.fieldBaseImpl._
import com.google.inject.TypeLiteral


class Module extends AbstractModule with ScalaModule {
    def configure() = {
        bind(TypeLiteral(MatrixInterface[Char])).to[Matrix]
    }
}