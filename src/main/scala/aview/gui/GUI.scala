package aview.gui

import scalafx.scene.layout.{HBox, StackPane, BorderPane, Pane, VBox, Background, BackgroundFill, CornerRadii}
import controller.controllerComponent.ControllerInterface
import scalafx.scene.paint.{Stops, LinearGradient}
import scalafx.scene.control.{Button, Label}
import model.fieldComponent.FieldInterface
import scalafx.scene.text.{Text, Font}
import scalafx.scene.shape.Polygon
import scalafx.application.JFXApp3
import scalafx.scene.paint.Color._
import scalafx.scene.image.Image
import scalafx.geometry.Insets
import controller.GameStatus._
import scalafx.geometry.Pos._
import scalafx.scene.Scene
import scalafx.stage.Stage
import scalafx.Includes._
import HexModule.{given}
import scala.math.sqrt
import util.Observer


class GUI(using controller: ControllerInterface[Char]) extends JFXApp3 with Observer {
    controller.add(this)
    controller.save
    val size = 40
    private val font = "Hexa"
    private val fontsize = size * 1.5
    private val load = Font.loadFont(getClass.getResource("/Hexa.ttf").toExternalForm, 20)
    //private val load2 = Font.loadFont(getClass.getResource("/Hexag.ttf").toExternalForm, 20)

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
            icons += new Image(getClass.getResource("/logo.png").toExternalForm, 100, 100, true, true)
            resizable = false
            title.value = "HEXXAGON"
            scene = new Scene((controller.hexfield.matrix.col+2) * size*2, 800) {                
                val border = new BorderPane()
                border.setBackground(new Background(Array(new BackgroundFill(new LinearGradient(endX = 0, stops = Stops(LightGrey, LightSteelBlue)) , CornerRadii(size), Insets(10)))))
                val pane = Pane()
                var tmp: Hex = Hex(0,0,0,' ')
                for (j <- 0 to controller.hexfield.matrix.row - 1) {
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
                border.top = {
                    val l1 = new Label(controller.gamestatus.message())
                    l1.style = s"-fx-font: $fontsize $font; -fx-text-fill: linear-gradient(darkblue, red);"
                    l1.padding = Insets(0, 0, size, size * (controller.hexfield.matrix.col/2 - 1))
                    l1
                }
                border.bottom = new HBox {
                    padding = Insets(size, 0, size / 2, size * 0.75)
                    if controller.gamestatus == GAMEOVER then
                        val O = controller.hexfield.matrix.Ocount
                        val X = controller.hexfield.matrix.Xcount
                        val winner = if O < X then "PLAYER 1 WON" else if O == X then "DRAW" else "PLAYER 2 WON"
                        val over = new Label(winner)
                        over.textAlignment = scalafx.scene.text.TextAlignment.Center
                        if winner == "PLAYER 1 WON" then
                            over.style = s"-fx-font: $fontsize $font; -fx-text-fill: linear-gradient(darkblue, blue);"
                        else
                            over.style = s"-fx-font: $fontsize $font; -fx-text-fill: linear-gradient(black, red);"
                        this.padding = Insets(size, 0, size, size * (controller.hexfield.matrix.col/2 - 1))
                        this.children += over
                    else
                        val xcount = new Label("X: " + controller.hexfield.matrix.Xcount)
                        val ocount = new Label("O: " + controller.hexfield.matrix.Ocount)
                        xcount.textAlignment = scalafx.scene.text.TextAlignment.Center
                        ocount.textAlignment = scalafx.scene.text.TextAlignment.Center
                        xcount.style = s"-fx-font: $fontsize $font; -fx-text-fill: linear-gradient(darkblue, blue);"
                        ocount.style = s"-fx-font: $fontsize $font; -fx-text-fill: linear-gradient(black, red);"
                        this.children += xcount
                        this.children += ocount
                        this.setSpacing(size*controller.hexfield.matrix.col - size)
                }

                border.right = {
                    val css = s"""
                    #dark-blue {
                        -fx-background-color: 
                            #090a0c,
                            linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),
                            linear-gradient(#20262b, #191d22),
                            radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));
                        -fx-background-radius: 5,4,3,5;
                        -fx-background-insets: 0,1,2,0;
                        -fx-text-fill: black;
                        -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );
                        -fx-font-family: $font;
                        -fx-text-fill: linear-gradient(red, blue);
                        -fx-font-size: 20px;
                        -fx-padding: 10 20 10 20;
                    }
                    #dark-blue Text {
                        -fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.9) , 1, 0.0 , 0 , 1 );
                    }
                    """
                    val b1 = new Button("UNDO")
                    b1.setOnMouseClicked(x => controller.undo)
                    b1.style = css
                    val b2 = new Button("REDO")
                    b2.setOnMouseClicked(x => controller.redo)
                    b2.style = css
                    val b3 = new Button("FILL")
                    b3.setOnMouseClicked(x => controller.fillAll('X'))
                    b3.style = css
                    val b4 = new Button("RESET")
                    b4.setOnMouseClicked(x => controller.reset)
                    b4.style = css
                    val b5 = new Button("SAVE")
                    b5.setOnMouseClicked(x => controller.save)
                    b5.style = css
                    val b6 = new Button("LOAD")
                    b6.setOnMouseClicked(x => controller.load)
                    b6.style = css
                    val vb = new VBox(b1, b2, b3, b4, b5, b6)
                    vb.spacing = size
                    vb
                }

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