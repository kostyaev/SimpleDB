
import scala.swing._

object runner extends App {
  println("Ready")

  val frame = new MainFrame {
    title = "My First GUI"
    contents = Button("Click Me!")(println("Button was clicked."))
    size = new Dimension(500,500)
    centerOnScreen
  }

  frame.visible = true

}
