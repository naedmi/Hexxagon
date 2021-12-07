package aview.gui

import util.Observer
import controller.Controller
import scalafx.scene.layout.GridPane
import scalafx.scene.control.Button
import scalafx.application.JFXApp3;
import scalafx.scene.Scene;
import scalafx.stage.Stage;
import scalafx.application.JFXApp3
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import scalafx.Includes._
import scalafx.geometry.Pos._
import scalafx.scene.shape.Polygon
import scala.math.sqrt

class GUI(controller: Controller) extends JFXApp3 with Observer {
    controller.add(this)
    var gp = GridPane()

    var i, j = 0
    controller.hexfield.matrix.matrix.foreach {
        x => x.foreach(y => {
            gp.add(Button(y.toString), j, i)
            j += 1 })
        i += 1
        j = 0
    }

    override def start() = {
        stage = new JFXApp3.PrimaryStage {
            title.value = "HEXXAGON"
            width = 800
            height = 800
            scene = new Scene {
              fill = LightBlue
              //content += gp
              content += hex(100, 200, 200)
            }
        }
    }

    override def update = {
        gp.getChildren().removeAll()
        controller.hexfield.matrix.matrix.foreach {
            x => x.foreach(y => {
                gp.add(Button(y.toString), j, i)
                j += 1 })
            i += 1
            j = 0
        }
        start()
    }

    def hex(size: Double, x: Double, y: Double): Polygon = {
        val cons = sqrt(3)/2
        var pol = Polygon()
        pol.points ++= Seq(
                        x, y,
                        x + size, y,
                        x + size*(3.0/2.0), y + size*cons,
                        x + size, y + size*sqrt(3),
                        x, y + size*sqrt(3),
                        x - (size / 2.0), y + size*cons)
        pol.fill = Black
        pol.setStroke(White)
        pol.alignmentInParent = Center
        pol
    }
}