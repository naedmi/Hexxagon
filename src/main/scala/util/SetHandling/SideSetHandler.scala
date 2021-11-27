package util
package SetHandling

import scala.util.{Try,Success,Failure}

class SideSetHandler() extends TopBotSetHandler {
    var MaxX = 0
    override def createSetandHandle(content: Char, x: Int, y: Int, startmatrix: Vector[Vector[Char]]) = {
        setBound(x, y)
        var tmpmatrix = startmatrix
        setMaxY(startmatrix)
        setMaxX(startmatrix)
        Try(setforeach(Set((0, y - 1), (0, y + 1), (1, ibound), (1, ibound + 1)), tmpmatrix, content)) match {
            case Success(o) => o
            case Failure(_) => {
                Try(setforeach(Set((MaxX, y - 1), (MaxX, y + 1), (MaxX - 1, ibound), (MaxX - 1, ibound + 1)), tmpmatrix, content)) match {
                    case Success(o) => o
                    case Failure(_) => new CornerSetHandler().createSetandHandle(content, x, y, startmatrix)
                }
            }
        }
    }
    def setMaxX(ma: Vector[Vector[Char]]) = ma(0).size - 1
}