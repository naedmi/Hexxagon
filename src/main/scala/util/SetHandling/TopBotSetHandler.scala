package util
package SetHandling

import scala.util.{Try,Success,Failure}

class TopBotSetHandler() extends DefaultSetHandler {
    var MaxY = 0
    override def createSetandHandle(content: Char, x: Int, y: Int, startmatrix: Vector[Vector[Char]]) = {
        setBound(x, y)
        var tmpmatrix = startmatrix
        setMaxY(startmatrix)
        tolookat = List(Set((x, y + 1), (x - 1, 0), (x - 1, ibound + 1), (x + 1, 0), (x + 1, ibound + 1)),
                        Set((x, y - 1), (x - 1, MaxY), (x - 1, ibound), (x + 1, MaxY), (x + 1, ibound)))

        Try(setforeach(tolookat(0), tmpmatrix, content)) match {
            case Success(o) => o
            case Failure(_) => {
                Try(setforeach(tolookat(1), tmpmatrix, content)) match {
                    case Success(o) => o
                    case Failure(_) => new SideSetHandler().createSetandHandle(content, x, y, startmatrix)
                }
            }
        }
    }
    def setMaxY(ma: Vector[Vector[Char]]) = ma.size - 1
}