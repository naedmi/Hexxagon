package util

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class ObserverSpec extends AnyWordSpec {
   "An observable" when {
        "added a new observer" should {
            var updated = false
            val observable = new Observable {}
            val observer = new Observer {
                override def update: Unit = updated = true
            }
            observable.add(observer)

            "have a subscriber" in {
                observable.subscribers.contains(observer) should be(true)
            }

            "have a subscriber removed" in {
                observable.remove(observer)
                observable.subscribers.contains(observer) should be(false)
            }
        }
    }

    "An Observer" should {
        "update its state" in {
            var updated = false
            val observer = new Observer {
                override def update: Unit = updated = true
            }
            observer.update
            updated should be(true)
        }
    }
}