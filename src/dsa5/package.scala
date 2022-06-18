import org.scalajs.dom.{Element, document}

package object dsa5 {
  implicit class RichObject[T] (t: T) {
    def tap(f: T => Any): T = { f(t); t }
  }

  implicit class RichElement(element: Element) {
    def _id(id: String): Element = element.tap(_.id = id)
    def _class(_class: String): Element = element.tap(_.setAttribute("class", _class))
    def _child(tagName: String): Element = document.createElement(tagName).tap(element.append(_))
  }
}
