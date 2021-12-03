package util
package SetHandling

import scala.util.{Try,Success,Failure}

case class CornerSetHandler() extends SideSetHandler {
    override def createSetandHandle(content: Char, x: Int, y: Int, startmatrix: Vector[Vector[Char]]) = {
        setBound(x, y)
        var tmpmatrix = startmatrix
        setMaxY(startmatrix)
        setMaxX(startmatrix)
        tolookat = List(Set((x, y + 1), (x + 1, y)),
                        Set((x, MaxY - 1), (x + 1, MaxY), (x + 1, MaxY - 1)),
                        Set((MaxX - 1, y), (MaxX, y + 1)),
                        Set((MaxX - 1, y), (MaxX - 1, y - 1), (MaxX, y - 1)))
        
        val map = tolookat.map(x => Try(setforeach(x, tmpmatrix, content))).collect{ case Success(x) => x }
        if map.isEmpty then startmatrix
        else map(0)

        //Try(setforeach(tolookat(0), tmpmatrix, content)) match {
        //    case Success(x) => x
        //    case Failure(_) => {
        //        Try(setforeach(tolookat(1), tmpmatrix, content)) match {
        //            case Success(x) => x
        //            case Failure(_) => {
        //            Try(setforeach(tolookat(2), tmpmatrix, content)) match {
        //                case Success(x) => x
        //                case Failure(_) => {
        //                Try(setforeach(tolookat(3), tmpmatrix, content)) match {
        //                    case Success(x) => x
        //                    case Failure(_) => startmatrix
        //                }
        //            }
        //            }
        //        }
        //        }
        //    }
        //}
    }
}