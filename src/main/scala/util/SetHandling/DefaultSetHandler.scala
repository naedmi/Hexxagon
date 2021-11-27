package util
package SetHandling

import scala.util.{Try,Success,Failure}

class DefaultSetHandler() extends SetHandler {
    var ibound: Int = 0
    override def createSetandHandle(content: Char, x: Int, y: Int, startmatrix: Vector[Vector[Char]]) = {
        setBound(x, y)
        var tmpmatrix = startmatrix
        Try(setforeach(Set((x, y - 1), (x, y + 1), (x - 1, ibound), (x - 1, ibound + 1), (x + 1, ibound), (x + 1, ibound + 1)), tmpmatrix, content)) match {
            case Success(o) => o
            case Failure(_) => new TopBotSetHandler().createSetandHandle(content, x, y, startmatrix)
        }
    }
    def setBound(x: Int, y: Int) = 
        ibound = y - 1
        if x % 2 == 1 || ibound < 0 then
            ibound += 1
}