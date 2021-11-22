package util

object ListFactory {
    def apply(x:Int, y:Int, MaxX:Int, MaxY:Int):List[(Int, Int)] = {
        var ibound = y - 1
        if x % 2 == 1 || y < 0 then
            ibound += 1
        if (MaxX == 0 && MaxY == 0) || (x > MaxX || y > MaxY) then List()
        else if MaxX == 0 then 
            y match { 
                case 0 => List((0, 1))
                case MaxY => List((0, MaxY - 1))
                case _ => List((0, y - 1), (0, y + 1))
            }
        else if MaxY == 0 then 
            x match { 
                case 0 => List((1, 0))
                case MaxX => List((MaxX - 1, y))
                case _ => List((x - 1, 0), (x + 1, 0))
            }
        else
            x match {
                case 0 => {
                    y match {
                        case 0 => List((0, y + 1), (1, y))
                        case MaxY => List((0, MaxY - 1), (1, MaxY), (1, MaxY - 1))
                        case _ => List((0, y - 1), (0, y + 1), (1, y), (1, y + 1))
                    }
                }
                case MaxX => {
                    y match {
                        case 0 => List((MaxX - 1, 0), (MaxX, 1))
                        case MaxY => List((MaxX - 1, MaxY), (MaxX - 1, MaxY - 1), (MaxX, MaxY - 1))
                        case _ => List((MaxX, y - 1), (MaxX, y + 1), (MaxX - 1, y), (MaxX - 1, y + 1))
                    }
                }
                case _ => {
                    y match {
                        case 0 => List((x, y + 1), (x - 1, 0), (x - 1, ibound + 1), (x + 1, 0), (x + 1, ibound + 1))
                        case MaxY => List((x, y - 1), (x - 1, MaxY), (x - 1, ibound + 1), (x + 1, 0), (x + 1, ibound + 1)) //not sure
                        case _ => List((x, y - 1), (x, y + 1), (x - 1, ibound), (x - 1, ibound + 1), (x + 1, ibound), (x + 1, ibound + 1))
                    }
                }
            }
    }
}