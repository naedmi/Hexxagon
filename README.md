[![Scala CI](https://github.com/naedmi/Hexxagon/actions/workflows/scala.yaml/badge.svg)](https://github.com/naedmi/Hexxagon/actions/workflows/scala.yml) [![codecov](https://codecov.io/gh/naedmi/Hexxagon/branch/master/graph/badge.svg?token=1GU15EM8AA)](https://codecov.io/gh/naedmi/Hexxagon)
# Project

This project is developed as an example for the lecture Software Engineering at the HTWG Konstanz. 

You can compile code with `sbt compile`, run it with `sbt run`, and `sbt console` will start a Scala 3 REPL.

# Hexxagon

Hexxagon is a strategy board game played in a 2 player mode. The goal is to capture as many fields as possible on a hexagonal grid. The player with the most stones on the board wins.

You can place your stones by inputting a two-indexed location. 
[If you move 1 space, you clone the stone. If you jump 2 spaces, you just move it.]