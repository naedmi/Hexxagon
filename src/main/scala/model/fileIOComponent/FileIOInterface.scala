package model.fileIOComponent

import model.fieldComponent.FieldInterface

/** Interface to implement file IO for the current game status. */
trait FileIOInterface {
    /** Loads field from a file.
     * 
     * @return the loaded field
    */
    def load: FieldInterface[Char]

    /** Saves field to a file.
     * 
     * @param field the field to be saved
    */
    def save(field: FieldInterface[Char]): Unit
}