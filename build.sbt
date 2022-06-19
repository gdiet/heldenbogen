enablePlugins(ScalaJSPlugin)

name := "Heldenbogen"
scalaVersion := "2.13.8"
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.2.0"

// Kein "test" Verzeichnis benötigt
Compile / scalaSource := baseDirectory.value / "src"

scalaJSUseMainModuleInitializer := true

lazy val createPage = taskKey[Unit]("Build and copy the JavaScript code.")
createPage := {
  val _ = (Compile / fastOptJS).value
  val pageDir = file("docs")
  IO.copyFile(file("target/scala-2.13/heldenbogen-fastopt/main.js"), pageDir / "main.js")
}
