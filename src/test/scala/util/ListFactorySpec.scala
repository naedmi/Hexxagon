package util

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class ListFactorySpec extends AnyWordSpec {
    "A ListFactory" when {
        "given a set of coordinates and borders" in {
            ListFactory(0, 0, 1, 2) should be (List((0, 1),(1, 0)))
            ListFactory(3, 3, 4, 4) should be (List((3, 2),(3, 4),(2, 3),(2, 4),(4, 3), (4, 4)))
            ListFactory(2, 7, 0, 0) should be (List())
            ListFactory(0, 0, 0, 0) should be (List())
            ListFactory(0, 0, 0, 1) should be (List((0, 1)))
            ListFactory(4, 4, 3, 3) should be (List())
            ListFactory(4, 4, 4, 4) should be (List((3, 4),(3, 3),(4, 3)))
            ListFactory(0, 4, 0, 5) should be (List((0, 3),(0, 5)))
            ListFactory(0, 5, 0, 5) should be (List((0, 4)))
            ListFactory(0, 0, 2, 0) should be (List((1, 0)))
            ListFactory(1, 0, 2, 0) should be (List((0, 0),(2, 0)))
            ListFactory(0, 5, 8, 5) should be (List((0, 4),(1, 5),(1, 4)))
            ListFactory(3, 0, 8, 5) should be (List((3, 1),(2, 0),(2, 1),(4, 0),(4, 1)))
            ListFactory(2, 0, 8, 5) should be (List((2, 1),(1, 0),(3, 0)))
            ListFactory(5, 4, 5, 5) should be (List((5, 3),(5, 5),(4, 4),(4, 5)))
            ListFactory(3, 0, 3, 3) should be (List((2, 0),(3, 1)))
            ListFactory(8, 1, 8, 5) should be (List((8, 0),(8, 2),(7, 0),(7, 1)))
            ListFactory(0, 4, 8, 5) should be (List((0, 3),(0, 5),(1, 3),(1, 4)))
        }
        "applied" in {
            ListFactory.apply(0,0,0,0) should be (ListFactory(0,0,0,0))
        }
    }
}