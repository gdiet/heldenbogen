package dsa5

import org.scalajs.dom.{Element, Node, document}

import scala.scalajs.js.|

object HtmlUtils {
  // Used as implicits below to provide changing context.
  class Elem(parent: Element) {
    private var stack: List[Element] = List(parent)
    private[HtmlUtils] def apply(): Element = stack.head
    private[HtmlUtils] def pop(): Unit = stack = stack.tail
    private[HtmlUtils] def push(element: Element): Unit = stack ::= element
  }
  def body: Elem = new Elem(document.body)

  private def add[T](tagName: String)(contents: => T)(implicit parent: Elem): T = {
    parent.push(document.createElement(tagName).tap(parent().append(_)))
    contents.tap(_ => parent.pop())
  }

  def table[T](contents: => T)(implicit parent: Elem): T = add("table")(contents)
  def tr[T](contents: => T)(implicit parent: Elem): T = add("tr")(contents)
  def th[T](contents: => T)(implicit parent: Elem): T = add("th")(contents)
  def td[T](contents: => T)(implicit parent: Elem): T = add("td")(contents)

  def id(id: String)(implicit current: Elem): Unit = current().id = id
  def clazz(clazz: String)(implicit current: Elem): Unit = current().setAttribute("class", clazz)
  def append(nodes: (Node | String)*)(implicit current: Elem): Unit = current().append(nodes:_*)
}
