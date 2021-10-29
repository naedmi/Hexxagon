package scala

@main def Hex: Unit = {
  val h = new HexField(0, 0);
  h.placeX(0, 0)
  //h.placeX(0, 1)
  print(h.field);
}

case class HexField(var lines:Int = 6, var col:Int = 9):
  var condition = 1;
  if (col == 0 || lines == 0) {
    condition = 0;
  }
  
  val usecol = col / 2
  if lines == 0 then lines = 1
  if col == 0 then col = 1
  val matrix = Array.ofDim[Char](lines, col)
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
  //def bot = "\\___/" * condition + "   \\___/" * usecol + eol
  def edgetop = " ___ " * condition + "    ___ " * usecol + eol
  

  def placeX(x:Int, y:Int) = {
    matrix(x)(y) = 'X'
  }
  def placeO(x:Int, y:Int) = {
    matrix(x)(y) = 'O'
  }

  def bot(line:Int): String = {
    
    var res = "\\___/" * condition
    for (x <- 1 until col - 1 by 2) {
      res += " " + matrix(line)(x).toString * condition + " \\___/" * condition
    }
    res + "\n" * condition
  }

  def top(line:Int): String = {
    
    var res = "/ " * condition + matrix(line)(0).toString * condition + " \\" * condition
    for (x <- 2 until col by 2) {
      res += "___/ " + matrix(line)(x) + " \\"
    }
    res + "\n" * condition
  }

  def field = {
    var res = eol + edgetop
    for(l <- 0 until lines) {
      res += (top(l) + bot(l))
    }
    res
  }