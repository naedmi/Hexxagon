package model.fileIOComponent.fileIOJsonImpl

/*import model.fileIOComponent.FileIOInterface
import model.fieldComponent.FieldInterface
import scala.io.Source
import upickle.default._
import ujson.Obj

class FileIO extends FileIOInterface {
    override def load: FieldInterface[Char] = {
        val source: String = Source.fromFile("field.json").getLines.mkString
        val json = ujson.read(source)
        var field = FlexibleModule(json("rows").num.toInt, json("cols").num.toInt).given_FieldInterface_Char
        val cells = json("cells")
        
        for (index <- 0 until json("rows").num.toInt * json("cols").num.toInt) {
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

    def fieldToJson(field: FieldInterface[Char]) = {
        ujson.Obj(
            "rows" -> ujson.Num(field.matrix.row),
            "cols" -> ujson.Num(field.matrix.col),
            "cells" -> (
                for {
                    row <- 0 until field.matrix.row;
                    col <- 0 until field.matrix.col
                } yield {
                    ujson.Obj(
                    "row" -> row,
                    "col" -> col,
                    "cell" -> field.matrix.cell(col, row).toString
                    )
                }
            )
        )
    }
}*/

import model.fileIOComponent.FileIOInterface
import model.fieldComponent.FieldInterface
import scala.io.Source
import play.api.libs.json._

class FileIO extends FileIOInterface {

  override def load: FieldInterface[Char] = {
    var grid: FieldInterface[Char] = null
    val source: String = Source.fromFile("field.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val rows = (json \ "field" \ "rows").get.toString.toInt
    val cols = (json \ "field" \ "cols").get.toString.toInt

    var field = FlexibleModule(rows, cols).given_FieldInterface_Char
  
    for (index <- 0 until rows * cols) {
      val row = (json \\ "row")(index).as[Int]
      val col = (json \\ "col")(index).as[Int]
      val cell = (json \\ "cell")(index).as[String]
      field = field.placeAlways(cell.head, col, row)
    }
    field
  }

  override def save(field: FieldInterface[Char]): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("field.json"))
    pw.write(Json.prettyPrint(fieldToJson(field)))
    pw.close
  }

  def fieldToJson(field: FieldInterface[Char]) = {
    Json.obj(
      "field" -> Json.obj(
        "rows" -> JsNumber(field.matrix.row),
        "cols" -> JsNumber(field.matrix.col),
        "cells" -> Json.toJson(
          for {
            row <- 0 until field.matrix.row;
            col <- 0 until field.matrix.col
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "cell" -> Json.toJson(field.matrix.cell(col, row).toString)
            )
          }
        )
      )
    )
  }
}