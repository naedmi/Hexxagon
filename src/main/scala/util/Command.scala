package util

trait Command[T] {

  def noStep(t: T): T
  def doStep(t: T): T
  def undoStep(t: T): T
  def redoStep (t: T): T

}