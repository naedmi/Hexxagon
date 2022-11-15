package model.fileIOComponent.fileIOJsonImpl

import model.fileIOComponent.FileIOInterface
import model.fieldComponent.FieldInterface
import play.api.libs.json._
import scala.io.Source

class FileIO extends FileIOInterface {

  override def load: FieldInterface[Char] = {
    val json: JsValue = Json.parse(Source.fromFile("field.json").getLines.mkString)
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

  // not working with xcount, ocount, turn yet
  override def exportGame(field: FieldInterface[Char], xcount: Int, ocount: Int, turn: Int): String =
    gameToJson(field, xcount, ocount, turn).toString

  def fieldToJson(field: FieldInterface[Char]) = {
    Json.obj(
      "field" -> Json.obj(
        "rows" -> JsNumber(field.matrix.row),
        "cols" -> JsNumber(field.matrix.col),
        "cells" -> Json.toJson(
          for {
            row <- 0 until field.matrix.row;
            col <- 0 until field.matrix.col
          } yield cellToJson(field, row, col)
        )
      )
    )
  }

  def cellToJson(field: FieldInterface[Char], row: Int, col: Int) = {
    Json.obj(
      "row" -> row,
      "col" -> col,
      "cell" -> Json.toJson(field.matrix.cell(col, row).toString)
    )
  }
  
  def gameToJson(field: FieldInterface[Char], xcount: Int, ocount: Int, turn: Int) = {
    Json.obj(
      "xcount" -> JsNumber(xcount),
      "ocount" -> JsNumber(ocount),
      "turn" -> JsNumber(turn),
      "field" -> fieldToJson(field)
    )
  }
}