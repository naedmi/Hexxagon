val x = 1 + 2

def line(): String = 
    ("+" + "-" * x) * x + "+"
def bar(): String = 
    ("|" + " " * x) * x + "|"

line()
bar()
