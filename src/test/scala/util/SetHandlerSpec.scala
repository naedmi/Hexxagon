package util

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import SetHandling._
import model.Matrix

class SetHandlerSpec extends AnyWordSpec {
   "Looking at indices and a matrix a SetHandler" when {
       "created and used by a client" should {
           var mat = new Matrix(5, 5)
           "give back the same matrix when nothing fits" in {
               new DefaultSetHandler().createSetandHandle('X', -1, 5, mat.matrix) should be (mat.matrix)
           }
           "handle each case with a different class" in {
               new DefaultSetHandler().createSetandHandle('O', 0, 2, mat.matrix) should be (new SideSetHandler().createSetandHandle('O', 0, 2, mat.matrix))
               new DefaultSetHandler().createSetandHandle('O', 0, 0, mat.matrix) should be (new CornerSetHandler().createSetandHandle('O', 0, 0, mat.matrix))
               new DefaultSetHandler().createSetandHandle('X', 2, 0, mat.matrix) should be (new TopBotSetHandler().createSetandHandle('X', 2, 0, mat.matrix))
           }
       }
   }
}