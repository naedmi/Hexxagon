package scala

@main def Hex: Unit = {
  val h = new HexField(6, 4);
  print(h.field);
}

case class HexField(val lines:Int, val col:Int):
  def top = "/   \\" + "___/   \\" * col + " \n"
  def bot = "\\___/" + "   \\___/" * col + " \n"
  def edgetop = " ___ " + "    ___ " * col + " \n"

  def field = "\n" + edgetop + (top + bot) * lines
