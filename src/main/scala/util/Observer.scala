package scala
package util

trait Observer:
    def update:Unit // in TUI implementieren
    
trait Observable:
    var subscribers: Vector[Observer] = Vector()
    def add(s:Observer) = subscribers = subscribers:+s
    def remove(s:Observer) = subscribers = subscribers.filterNot(o => o==s) // observer == subscriber, den wir abmelden wollen
    def notifyObservers = subscribers.foreach(o => o.update)