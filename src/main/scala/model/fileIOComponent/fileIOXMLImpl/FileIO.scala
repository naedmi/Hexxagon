package model.fileIOComponent.fileIOXMLImpl

import model.fileIOComponent.FileIOInterface
import model.fieldComponent.FieldInterface
import scala.xml.{ NodeSeq, PrettyPrinter }

class FileIO extends FileIOInterface {
    override def load: FieldInterface[Char] = {
        var field: FieldInterface[Char] = null
        val file = scala.xml.XML.loadFile("field.xml")

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

    def fieldToXml(field: FieldInterface[Char]) = {
        <field>

        </field>
    }
}