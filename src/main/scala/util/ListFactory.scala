package util

object ListFactory {
    def createList(x:Int, y:Int, maxX:Int, maxY:Int):List[(Int, Int)] = {
        var ibound = y - 1
        if x % 2 == 1 then
            ibound += 1
        if maxX == 0 || maxY == 0 then List()
        else
            x match {
                case 0 => y match {
                    case 0 => List((0, y + 1), (1, y))
                    case maxY => List((0, maxY - 1), (1, maxY), (1, maxY - 1))
                    case _ => List((0, y - 1), (0, y + 1), (1, y), (1, y + 1))
                }
                case maxX => y match {
                    case 0 => List((maxX - 1, 0), (maxX, 1))
                    case maxY => List((maxX - 1, maxY), (maxX - 1, maxY - 1), (maxX, maxY - 1))
                    case _ => List((maxX, y - 1), (maxX, y + 1), (maxX - 1, y), (maxX - 1, y + 1))
                }
                case _ => y match {
                    case 0 => List((x, y + 1), (x - 1, 0), (x - 1, ibound + 1), (x + 1, 0), (x + 1, ibound + 1))
                    case maxY => List((x, y - 1), (x - 1, maxY), (x - 1, ibound + 1), (x + 1, 0), (x + 1, ibound + 1)) //not sure
                    case _ => List((x, y - 1), (x, y + 1), (x - 1, ibound), (x - 1, ibound + 1), (x + 1, ibound), (x + 1, ibound + 1))
                }
            }
    }
}