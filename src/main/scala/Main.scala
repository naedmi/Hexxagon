package scala

@main def Hex: Unit = {
  val h = new HexField(1, 1);
  print(h.field);
}

case class HexField(val lines:Int = 6, val col:Int = 9):
  //val matrix = Array.ofDim[Char](lines, col)
  var condition = 1;
  if (col == 0 || lines == 0) {
    condition = 0;
  }
  val usecol = col / 2
  val eol = "\n" * condition
  //var stone = ' ';
  def top = "/   \\" * condition + "___/   \\" * usecol + eol
  def bot = "\\___/" * condition + "   \\___/" * usecol + eol
  def edgetop = " ___ " * condition + "    ___ " * usecol + eol

  //def placeX(x:Int, y:Int) = {
  //  matrix(x)(y) = 'X'
  //}
  //def placeO(x:Int, y:Int) = {
  //  matrix(x)(y) = 'O'
  //}

  //def top(line:Int): String = {
  //  var res = "/ " + matrix(line)(0) + " \\" * condition
  //  for (x <- (1 to col)) {
  //    res += "___/ " + matrix(line)(x) + " \\" * usecol + eol
  //  }
  //  res
  //}
  def field = eol + edgetop + (top + bot) * lines
