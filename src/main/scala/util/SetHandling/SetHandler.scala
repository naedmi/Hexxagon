package util
package SetHandling

import scala.util.{Try,Success,Failure}

trait SetHandler {
    def createSetandHandle(content: Char, x: Int, y: Int, startmatrix: Vector[Vector[Char]]): Vector[Vector[Char]]
    def setforeach(s: Set[(Int,Int)], matrix: Vector[Vector[Char]], content: Char) = {
        var tmpmatrix = matrix
        s.foreach{
            case (x, y) => if !tmpmatrix(y)(x).equals(content) && !tmpmatrix(y)(x).equals(' ') then tmpmatrix = tmpmatrix.updated(y, tmpmatrix(y).updated(x, content))
        } 
        tmpmatrix
    }
}