package model.fileIOComponent.fileIOJsonImpl

import model.fileIOComponent.FileIOInterface
import model.fieldComponent.FieldInterface
import scala.io.Source

class FileIO extends FileIOInterface {
    override def load: FieldInterface[Char] = {
        var field: FieldInterface[Char] = null
        val source: String = Source.fromFile("field.json").getLines.mkString

        field
    }

    override def save(field: FieldInterface[Char]): Unit = {
        
    }
}