package util
package SetHandling

import scala.util.{Try,Success,Failure}

case class CornerSetHandler() extends SideSetHandler {
    override def createSetandHandle(content: Char, x: Int, y: Int, startmatrix: Vector[Vector[Char]]) = {
        setBound(x, y)
        var tmpmatrix = startmatrix
        setMaxY(startmatrix)
        setMaxX(startmatrix)
        Try(setforeach(Set((0, y + 1), (1, y)), tmpmatrix, content)) match {
            case Success(x) => x
            case Failure(_) => {
                Try(setforeach(Set((0, MaxY - 1), (1, MaxY), (1, MaxY - 1)), tmpmatrix, content)) match {
                    case Success(x) => x
                    case Failure(_) => {
                    Try(setforeach(Set((MaxX - 1, 0), (MaxX, 1)), tmpmatrix, content)) match {
                        case Success(x) => x
                        case Failure(_) => {
                        Try(setforeach(Set((MaxX - 1, MaxY), (MaxX - 1, MaxY - 1), (MaxX, MaxY - 1)), tmpmatrix, content)) match {
                            case Success(x) => x
                            case Failure(_) => startmatrix
                        }
                    }
                    }
                }
                }
            }
        }
    }
}