package model.fileIOComponent.fileIOXMLImpl

import model.fileIOComponent.FileIOInterface
import model.fieldComponent.FieldInterface
import scala.xml.{ NodeSeq, PrettyPrinter }

class FileIO extends FileIOInterface {
    override def load: FieldInterface[Char] = {
        var field: FieldInterface[Char] = null
        val file = scala.xml.XML.loadFile("field.xml")
        val rows = (file \\ "field" \ "@rows")
        val cols = (file \\ "field" \ "@cols")
        field = FlexibleModule(rows.text.toInt, cols.text.toInt).given_FieldInterface_Char

        val cells = (file \\ "cell")
        for (cell <- cells) {
            val r: Int = (cell \ "@row").text.trim.toInt
            val c: Int = (cell \ "@col").text.trim.toInt
            val value: Char = {
                cell.text.trim match {
                    case "" => ' '
                    case "X" => 'X'
                    case "O" => 'O'
                }
            }
            field = field.placeAlways(value, c, r)
        }
        field
    }

    override def save(field: FieldInterface[Char]): Unit = {
        import java.io._
        val pw = new PrintWriter(new File("field.xml"))
        val prettyPrinter = new PrettyPrinter(120, 4)
        val xml = prettyPrinter.format(fieldToXml(field))
        pw.write(xml)
        pw.close
    }

    override def exportGame(field: FieldInterface[Char], xcount: Int, ocount: Int, turn: Int): String =
        gameToXml(field, xcount, ocount, turn).toString

    def fieldToXml(field: FieldInterface[Char]) = {
        <field rows={ field.matrix.row.toString } cols={ field.matrix.col.toString }>
            {
            for {
                l <- 0 until field.matrix.row
                i <- 0 until field.matrix.col
            } yield cellToXml(field, l, i)
            }
        </field>
    }

    def cellToXml(field: FieldInterface[Char], row: Int, col: Int) = {
        <cell row={ row.toString } col={ col.toString }>
            { field.matrix.cell(col, row) }
        </cell>
    }

    def gameToXml(field: FieldInterface[Char], xcount: Int, ocount: Int, turn: Int) = {
        <field rows={ field.matrix.row.toString } cols={ field.matrix.col.toString }>
            {
            <xcount>{ xcount }</xcount>
              <ocount>{ ocount }</ocount>
              <turn>{ turn }</turn>
            }
            {
            for {
                l <- 0 until field.matrix.row
                i <- 0 until field.matrix.col
            } yield cellToXml(field, l, i)
            }
        </field>
    }
}