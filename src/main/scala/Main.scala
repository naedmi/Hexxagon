package scala

@main def Hex: Unit = {
  val h = new HexField(0, 0);
  //h.placeX(0, 0)
  //h.placeX(0, 1)
  print(h.field);
}

case class HexField(val lines:Int = 6, val col:Int = 9):
  var condition = 1;
  if (col == 0 || lines == 0) {
    condition = 0;
  }
  
  val usecol = col / 2
  val matrix = Array.ofDim[Char](lines + 1, usecol + 1)
  val stone = ' ';
  if (condition != 0) {
    
    // Initialisation
    for(i <- 0 until lines) {
      for(j <- 0 until usecol) {
        matrix(i)(j) = stone;
      }
    }
  } else {
    matrix(0)(0) = stone
  }
  
  val eol = "\n" * condition
  
  //def top = "/   \\" * condition + "___/   \\" * usecol + eol
  def bot = "\\___/" * condition + "   \\___/" * usecol + eol
  def edgetop = " ___ " * condition + "    ___ " * usecol + eol
  

  def placeX(x:Int, y:Int) = {
    matrix(x)(y) = 'X'
  }
  def placeO(x:Int, y:Int) = {
    matrix(x)(y) = 'O'
  }

  def top(line:Int): String = {
    
    var res = "/ " * condition + matrix(line)(0).toString * condition + " \\" * condition
    for (x <- 0 until usecol) {
      res += "___/ " + matrix(line)(x) + " \\" * usecol
    }
    res + "\n" * condition
  }
  def field = {
    var res = eol + edgetop
    for(l <- 0 until lines) {
      res += (top(l) + bot)
    }
    res
  }