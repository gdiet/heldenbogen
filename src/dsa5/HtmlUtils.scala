package dsa5

import org.scalajs.dom.html.Input
import org.scalajs.dom.{Element, Event, Node, document}

import scala.scalajs.js.|

object HtmlUtils {
  /** Used as implicit to provide changing context. Note that instances of this (mutable) class
    * MUST NOT be references by event listeners. */
  class Elem(parent: Element) {
    private var stack: List[Element] = List(parent)
    def apply(): Element = stack.head
    private[HtmlUtils] def pop(): Unit = stack = stack.tail
    private[HtmlUtils] def push(element: Element): Unit = stack ::= element
  }
  def body: Elem = new Elem(document.body)

  private def add[T](tagName: String)(contents: => T)(implicit parent: Elem): T = {
    parent.push(document.createElement(tagName).tap(parent().append(_)))
    contents.tap(_ => parent.pop())
  }

  def p[T](contents: => T)(implicit parent: Elem): T = add("p")(contents)

  def table[T](contents: => T)(implicit parent: Elem): T = add("table")(contents)
  def tr[T](contents: => T)(implicit parent: Elem): T = add("tr")(contents)
  def th[T](contents: => T)(implicit parent: Elem): T = add("th")(contents)
  def td[T](contents: => T)(implicit parent: Elem): T = add("td")(contents)
  def colgroup[T](contents: => T)(implicit parent: Elem): T = add("colgroup")(contents)
  def col[T](contents: => T)(implicit parent: Elem): T = add("col")(contents)

  def id(id: String)(implicit current: Elem): Unit = current().id = id
  def clazz(clazz: String)(implicit current: Elem): Unit = current().setAttribute("class", clazz)
  def attr(attr: String, value: String)(implicit current: Elem): Unit = current().setAttribute(attr, value)
  def append(nodes: (Node | String)*)(implicit current: Elem): Unit = current().append(nodes:_*)

  def setClassVisibility(clazz: String, visibility: String): Unit =
    document.getElementsByClassName(clazz).foreach(
      _.setAttribute("style", s"visibility:$visibility")
    )

  def checkbox(label: String, checked: Boolean = true, labelRight: Boolean = false)(listener: Input => Any)(implicit parent: Elem): Input = {
    document.createElement("input").asInstanceOf[Input]
      .tap(_.`type` = "checkbox")
      .tap(_.checked = checked)
      .tap { input =>
        input.addEventListener("input", { _: Event => listener(input) } )
        document.createElement("label").tap { labelElement =>
          if (labelRight) labelElement.append(input, label) else labelElement.append(label, input)
          parent().append(labelElement)
        }
      }
  }

  def numberInput(min: Int, max: Int, value: Int)(listener: Input => Any)(implicit parent: Elem): Input = {
    document.createElement("input").asInstanceOf[Input]
      .tap(_.`type` = "number")
      .tap(_.min = min.toString)
      .tap(_.max = max.toString)
      .tap(_.value = value.toString)
      .tap(input => input.addEventListener("input", { _: Event => listener(input) } ))
      .tap(parent().append(_))
  }
}
