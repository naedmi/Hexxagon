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
        }
    }
}