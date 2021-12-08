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
import scalafx.scene.layout.HBox
import scalafx.scene.text.Text
import scalafx.scene.layout.StackPane
import scalafx.scene.paint.LinearGradient
import scalafx.scene.paint.Stops
import scalafx.geometry.Orientation
import javafx.scene.layout.BorderPane
import scalafx.scene.layout.Pane
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.canvas.Canvas
import scalafx.scene.Group
import scalafx.geometry.Insets
import controller.GameStatus._

class GUI(controller: Controller) extends JFXApp3 with Observer {
    controller.add(this)
    val size = 40

    def setMouse(p: Hex, i: Int, j: Int) = {
        p.setOnMouseClicked(x => {
            controller.gamestatus match {
                case TURNPLAYER1 => controller.place('X', i, j)
                case TURNPLAYER2 => controller.place('O', i, j)
                case IDLE => controller.place('X', i, j)
                case GAMEOVER => println("GAME OVER")
            }
        })
    }
    override def start() = {
        stage = new JFXApp3.PrimaryStage {
            title.value = "HEXXAGON"
            scene = new Scene(800, 800) {
                val border = new BorderPane()
                fill = LightBlue
                val pane = Pane()
                pane.setLayoutX(800)
                pane.setLayoutY(800)
                pane.setMinSize(500, 500)
                var tmp: Hex = Hex(0,0,0,' ')
                for (j <- 0 to controller.hexfield.lines - 1) {
                    controller.hexfield.matrix.matrix(j).zipWithIndex.foreach {
                        (x, i) => 
                            if i == 0 then 
                                tmp = Hex(size, 0, 0, x); tmp.relocate(i * size*2, j * size*1.8); setMouse(tmp, i, j); pane.children += tmp
                            else i % 2 match {
                            case 0 => {
                                tmp = Hex(size, 0, 0, x)
                                tmp.relocate(i * size*2 - (size/2.2) * i, j * size*1.8)
                                setMouse(tmp, i, j)
                                pane.children += tmp
                            }
                            case _ => {
                                tmp = Hex(size, 0, 0, x)
                                tmp.relocate(i * size*2 - (size/2.2) * i, j * size*1.8 + size/1.1)
                                setMouse(tmp, i, j)
                                pane.children += tmp
                            }
                        }
                    }
                }
                border.padding = Insets(50)
                border.center = pane

                root = border
            }

        }
    }

    override def update = {
        start()
    }
}
class Hex(size: Double, x: Double, y: Double,var t: Char) extends StackPane {
    private val cons = sqrt(3)/2
    var pol = Polygon()
    pol.points ++= Seq(
                    x, y,
                    x + size, y,
                    x + size*(3.0/2.0), y + size*cons,
                    x + size, y + size*sqrt(3),
                    x, y + size*sqrt(3),
                    x - (size / 2.0), y + size*cons)
    pol.fill = White
    pol.setStroke(Black)

    var text = Text(x, y, t.toString())
    text.setTextAlignment(scalafx.scene.text.TextAlignment.Center)
    text.style = s"-fx-font: $size arial"
    text.fill = t match {
        case 'X' => new LinearGradient(endX = 0, stops = Stops(Black, DarkBlue))
        case 'O' => new LinearGradient(endX = 0, stops = Stops(Black, DarkRed))
        case _ => White
    }

    this.getChildren().addAll(pol, text)
}