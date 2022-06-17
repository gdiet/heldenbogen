package dsa5

import org.scalajs.dom.html.{Table, TableCell, TableRow}
import org.scalajs.dom.{Element, Node, document}

import scala.scalajs.js.|

object HtmlUtils {
  def table(id: String): Table =
    document.createElement("table").tap(_.id = id).tap(document.body.append(_)).asInstanceOf[Table]
  def tr(table: Table): TableRow =
    document.createElement("tr").tap(table.append(_)).asInstanceOf[TableRow]
  def td(nodes: (Node | String)*): Node =
    document.createElement("td").tap(_.append(nodes:_*)).asInstanceOf[TableCell]
}
