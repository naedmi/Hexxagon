package scala

import scala.io.StdIn.readLine
import scala.util.matching.Regex

@main def Hex: Unit = {

  val h = new HexField();
  val maxind1 = h.lines - 1
  val maxind2 = h.col - 1

  printf("Input your x and y Coordinate as followed: [ 0-%d ] [ 0-%d ] [ X | O ] \n", maxind2, maxind1)
  print(h.field);

  val reg: Regex = ("[0-" + maxind2 + "]\\s+[0-" + maxind1 + "]\\s+[XO]").r
  var line = readLine()

  while(!line.equals("exit")) {
    line = matchReg(reg.findFirstIn(line))

    if line.equals("Wrong Input") then
      println(line)
    else
      val (y:Int, x:Int, c:Char) = line.split("\\s") match {
        case Array(x, y, c) => (x.toInt, y.toInt, c.charAt(0))
      }
      if c.equals('X') then
        h.placeX(x, y)
      else if c.equals('O') then
        h.placeO(x, y)
      end if

    print(h.field)

    line = readLine()
  }
}

def matchReg(x : Option[String]): String = x match {
    case None => "Wrong Input"
    case Some(s) => s
}

case class HexField(var col:Int = 9, var lines:Int = 6):
  if col % 2 == 0 then col += 1
  var condition = 1;
  if (col == 0 || lines == 0) {
    condition = 0;
  }
  val usecol = col / 2

  if lines == 0 then lines = 1
  if col == 0 then col = 1

  var matrix = Vector.fill[Char](lines, col)(' ');
  
  val eol = "\n" * condition
  def edgetop = " ___ " * condition + "    ___ " * usecol + eol
  def edgebot = " " * condition + "   \\___/" * condition * usecol + eol
  
  def placeX(x:Int, y:Int) = {
    matrix = matrix.updated(x, matrix(x).updated(y, 'X'))
  }
  def placeO(x:Int, y:Int) = {
    matrix = matrix.updated(x, matrix(x).updated(y, 'O'))
  }

  def bot(line:Int): String = {
    
    var res = "\\___/" * condition

    matrix(line).zipWithIndex.foreach((x, i) => if i % 2 != 0 && i >= 1 && i < col then res += " " + x.toString * condition + " \\___/" * condition)

    //for (x <- 1 until col - 1 by 2) {
    //  res += " " + matrix(line)(x).toString * condition + " \\___/" * condition
    //}
    res + "\n" * condition
  }

  def top(line:Int): String = {
    
    var res = "/ " * condition + matrix(line)(0).toString * condition + " \\" * condition

    matrix(line).zipWithIndex.foreach((x, i) => if i % 2 == 0 && i >= 2 then res += "___/ " + x + " \\")

    //for (x <- 2 until col by 2) {
    //  res += "___/ " + matrix(line)(x) + " \\"
    //}
    res + "\n" * condition
  }

  def field = {
    var res = eol + edgetop
    for(l <- 0 until lines) {
      res += (top(l) + bot(l))
    }
    res += edgebot
    res
  }