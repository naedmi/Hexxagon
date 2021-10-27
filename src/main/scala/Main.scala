package scala

@main def Hex: Unit = {
  val h = new HexField(1, 1);
  print(h.field);
}

case class HexField(val lines:Int = 6, val col:Int = 9):
  //val matrix = Array.ofDim[String](lines, col)
  var condition = 1;
  if (col == 0 || lines == 0) {
    condition = 0;
  }
  val usecol = col / 2
  val eol = "\n" * condition
  def top = "/   \\" * condition + "___/   \\" * usecol + eol
  def bot = "\\___/" * condition + "   \\___/" * usecol + eol
  def edgetop = " ___ " * condition + "    ___ " * usecol + eol

  //def placeX(x:Int, y:Int) = {
  //  matrix(x)(y) = "X"
  //}
  //def placeO(x:Int, y:Int) = {
  //  matrix(x)(y) = "O"
  //}

  def field = eol + edgetop + (top + bot) * lines
