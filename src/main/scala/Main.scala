object Main extends App {
  def lines = 6
  def top = "/   \\___/   \\___/   \\___/   \\___/   \\ \n"
  def bot = "\\___/   \\___/   \\___/   \\___/   \\___/ \n"
  def edgetop = " ___     ___     ___     ___     ___ \n"

  def field = edgetop + (top + bot) * lines

  print(field)
}

  /*print("""
 ___     ___     ___     ___     ___  
/   \___/   \___/   \___/   \___/   \
\___/   \___/   \___/   \___/   \___/
/   \___/   \___/   \___/   \___/   \
\___/   \___/   \___/   \___/   \___/
/   \___/   \___/   \___/   \___/   \
\___/   \___/   \___/   \___/   \___/
/   \___/   \___/   \___/   \___/   \
\___/   \___/   \___/   \___/   \___/
/   \___/   \___/   \___/   \___/   \
\___/   \___/   \___/   \___/   \___/
/   \___/   \___/   \___/   \___/   \
\___/   \___/   \___/   \___/   \___/
  """)*/