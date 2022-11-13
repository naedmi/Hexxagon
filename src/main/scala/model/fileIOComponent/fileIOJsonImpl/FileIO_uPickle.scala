package model.fileIOComponent.fileIOJsonImpl

import model.fileIOComponent.FileIOInterface
import model.fieldComponent.FieldInterface
import upickle.default._
import scala.io.Source
import ujson.Obj

class FileIO_uPickle extends FileIOInterface {
    override def load: FieldInterface[Char] = {
        val json = ujson.read(Source.fromFile("field.json").getLines.mkString)
        val rows = json("rows").num.toInt
        val cols = json("cols").num.toInt
        var field = FlexibleModule(rows, cols).given_FieldInterface_Char
        val cells = json("cells")
        
        for (index <- 0 until rows * cols) {
            val row = cells(index)("row").num.toInt
            val col = cells(index)("col").num.toInt
            val cell = cells(index)("cell").str.head
            field = field.placeAlways(cell, col, row)
        }
        field
    }

    override def save(field: FieldInterface[Char]): Unit = {
        import java.io._
        val pw = new PrintWriter(new File("field.json"))
        pw.write(ujson.transform(fieldToJson(field), ujson.StringRenderer(indent = 4)).toString)
        pw.close
    }

    // not working with xcount, ocount, turn yet
    override def exportGame(field: FieldInterface[Char], xcount: Int, ocount: Int, turn: Int): String = {
        fieldToJson(field).toString
    }

    def fieldToJson(field: FieldInterface[Char]) = {
        ujson.Obj(
            "rows" -> ujson.Num(field.matrix.row),
            "cols" -> ujson.Num(field.matrix.col),
            "cells" -> (
                for {
                    row <- 0 until field.matrix.row;
                    col <- 0 until field.matrix.col
                } yield cellToJson(field, row, col)
            )
        )
    }

    def cellToJson(field: FieldInterface[Char], row: Int, col: Int) = {
    ujson.Obj(
        "row" -> row,
        "col" -> col,
        "cell" -> field.matrix.cell(col, row).toString
    )
  }
}