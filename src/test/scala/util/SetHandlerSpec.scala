package util

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import SetHandling._
import model.fieldComponent.fieldBaseImpl.Matrix

class SetHandlerSpec extends AnyWordSpec {
   "Looking at indices and a matrix a SetHandler" when {
       "created and used by a client" should {
           var mat = new Matrix(5, 5)
           "give back the same matrix when nothing fits" in {
                mat = mat.fillAll('O')
                new DefaultSetHandler().createSetandHandle('X', -1, 5, mat.matrix) should be (mat.matrix)
                new DefaultSetHandler().createSetandHandle('X', 6, 5, mat.matrix) should be (new CornerSetHandler().createSetandHandle('X', 6, 6, mat.matrix))
           }
           "handle each case with a different class" in {
                mat = mat.fillAll('O')
                new DefaultSetHandler().createSetandHandle('X', 2, 0, mat.matrix) should be (new TopBotSetHandler().createSetandHandle('X', 2, 0, mat.matrix))
                new DefaultSetHandler().createSetandHandle('X', 0, 2, mat.matrix) should be (new SideSetHandler().createSetandHandle('X', 0, 2, mat.matrix))
                new DefaultSetHandler().createSetandHandle('X', 0, 0, mat.matrix) should be (new CornerSetHandler().createSetandHandle('X', 0, 0, mat.matrix))
                new CornerSetHandler().createSetandHandle('X', 0, 0, mat.matrix) should be (new DefaultSetHandler().createSetandHandle('X', 0, 0, mat.matrix))
                new CornerSetHandler().createSetandHandle('X', 1, 1, mat.matrix) should not be (new DefaultSetHandler().createSetandHandle('X', 1, 1, mat.matrix))
           }
           "have a certain List of Sets for each Situation" in {
                var d = new DefaultSetHandler()
                d.createSetandHandle('X', 3, 3, mat.matrix)
                d.tolookat should contain allElementsOf(List(Set((3, 2),(3, 4),(2, 3),(2, 4),(4, 3),(4, 4))))
                d = new TopBotSetHandler()
                d.createSetandHandle('X', 3, 0, mat.matrix)
                d.tolookat should contain allElementsOf(List(Set((2, 1),(2, 0),(3, 1),(4, 0),(4, 1)),
                                                            Set((3, -1),(2, 4),(4, 1),(4, 4),(2, 1))))    //Bot even tho we are at the top
                d = new SideSetHandler()
                d.createSetandHandle('X', 0, 3, mat.matrix)
                d.tolookat should contain allElementsOf(List(Set((0, 2),(0, 4),(1, 2),(1, 3)),
                                                            Set((0, 2),(0, 4),(3, 2),(3, 3))))
                d = new CornerSetHandler()
                d.createSetandHandle('X', 0, 0, mat.matrix)
                d.tolookat should contain allElementsOf(List(Set((0, 1),(1, 0)),
                                                            Set((3, 0),(4, 1)),
                                                            Set((0, 3),(1, 4),(1, 3)),
                                                            Set((3, -1),(3, 0),(4, -1))))
           }
       }
   }
}