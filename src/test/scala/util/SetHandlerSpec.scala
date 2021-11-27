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
                mat = mat.fillAll('O')
                new DefaultSetHandler().createSetandHandle('X', -1, 5, mat.matrix) should be (mat.matrix)
                new DefaultSetHandler().createSetandHandle('X', 5, 5, mat.matrix) should be (new CornerSetHandler().createSetandHandle('X', 6, 6, mat.matrix))
           }
           "handle each case with a different class" in {
                mat = mat.fillAll('O')
                new DefaultSetHandler().createSetandHandle('X', 2, 0, mat.matrix) should be (new TopBotSetHandler().createSetandHandle('X', 2, 0, mat.matrix))
                new DefaultSetHandler().createSetandHandle('X', 0, 2, mat.matrix) should be (new SideSetHandler().createSetandHandle('X', 0, 2, mat.matrix))
                new DefaultSetHandler().createSetandHandle('X', 0, 0, mat.matrix) should be (new CornerSetHandler().createSetandHandle('X', 0, 0, mat.matrix))
                new DefaultSetHandler().createSetandHandle('X', 0, 4, mat.matrix) should be (new CornerSetHandler().createSetandHandle('X', 0, 4, mat.matrix))
                new DefaultSetHandler().createSetandHandle('X', 4, 0, mat.matrix) should be (new CornerSetHandler().createSetandHandle('X', 4, 0, mat.matrix))
                new DefaultSetHandler().createSetandHandle('X', 4, 4, mat.matrix) should be (new CornerSetHandler().createSetandHandle('X', 4, 4, mat.matrix))
           }
           "have the corner case be the lowest subclass" in {
                mat = mat.fillAll('O')
                new DefaultSetHandler().createSetandHandle('X', 0, 0, mat.matrix) should be (new CornerSetHandler().createSetandHandle('X', 0, 0, mat.matrix))
                new TopBotSetHandler().createSetandHandle('X', 0, 4, mat.matrix) should be (new CornerSetHandler().createSetandHandle('X', 0, 4, mat.matrix))
                new SideSetHandler().createSetandHandle('X', 4, 0, mat.matrix) should be (new CornerSetHandler().createSetandHandle('X', 4, 0, mat.matrix))
                new CornerSetHandler().createSetandHandle('X', 4, 4, mat.matrix) should be (new CornerSetHandler().createSetandHandle('X', 4, 4, mat.matrix))
           }
           "have the side case be the 2nd lowest subclass" in {
                mat = mat.fillAll('O')
                new DefaultSetHandler().createSetandHandle('X', 0, 1, mat.matrix) should be (new SideSetHandler().createSetandHandle('X', 0, 1, mat.matrix))
                new TopBotSetHandler().createSetandHandle('X', 4, 1, mat.matrix) should be (new SideSetHandler().createSetandHandle('X', 4, 1, mat.matrix))
                new SideSetHandler().createSetandHandle('X', 4, 2, mat.matrix) should be (new SideSetHandler().createSetandHandle('X', 4, 2, mat.matrix))
                new CornerSetHandler().createSetandHandle('X', 0, 2, mat.matrix).equals(new SideSetHandler().createSetandHandle('X', 0, 2, mat.matrix)) should not be (true)
           }
           "have the Top & Bot case be the 3rd lowest subclass" in {
                mat = mat.fillAll('O')
                new DefaultSetHandler().createSetandHandle('X', 1, 4, mat.matrix) should be (new TopBotSetHandler().createSetandHandle('X', 1, 4, mat.matrix))
                new TopBotSetHandler().createSetandHandle('X', 2, 0, mat.matrix) should be  (new TopBotSetHandler().createSetandHandle('X', 2, 0, mat.matrix))
                new SideSetHandler().createSetandHandle('X', 3, 4, mat.matrix) should not be (new TopBotSetHandler().createSetandHandle('X', 3, 4, mat.matrix))
                new CornerSetHandler().createSetandHandle('X', 1, 0, mat.matrix) should not be (new TopBotSetHandler().createSetandHandle('X', 1, 0, mat.matrix))
            }
            "have the Default case be the highest subclass and therefore be irreplacable" in {
                mat = mat.fillAll('O')
                new DefaultSetHandler().createSetandHandle('X', 1, 1, mat.matrix) should be (new DefaultSetHandler().createSetandHandle('X', 1, 1, mat.matrix))
                new TopBotSetHandler().createSetandHandle('X', 2, 2, mat.matrix) should not be  (new DefaultSetHandler().createSetandHandle('X', 2, 2, mat.matrix))
                new SideSetHandler().createSetandHandle('X', 3, 3, mat.matrix) should not be (new DefaultSetHandler().createSetandHandle('X', 3, 3, mat.matrix))
                new CornerSetHandler().createSetandHandle('X', 3, 3, mat.matrix) should not be (new DefaultSetHandler().createSetandHandle('X', 3, 3, mat.matrix))
            }
       }
   }
}