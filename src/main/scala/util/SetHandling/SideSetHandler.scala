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
        tolookat = List(Set((x, y - 1), (x, y + 1), (x + 1, ibound), (x + 1, ibound + 1)),
                        Set((x, y - 1), (x, y + 1), (MaxX - 1, y - 1), (MaxX - 1, y)))

        val map = tolookat.map(x => Try(setforeach(x, tmpmatrix, content))).collect{ case Success(x) => x }
        if map.isEmpty then new CornerSetHandler().createSetandHandle(content, x, y, startmatrix)
        else map(0)

        //Try(setforeach(tolookat(0), tmpmatrix, content)) match {
        //    case Success(o) => o
        //    case Failure(_) => {
        //        Try(setforeach(tolookat(1), tmpmatrix, content)) match {
        //            case Success(o) => o 
        //            case Failure(_) => new CornerSetHandler().createSetandHandle(content, x, y, startmatrix)
        //        }
        //    }
        //}
    }
    def setMaxX(ma: Vector[Vector[Char]]) = MaxX = ma(0).size - 1
}