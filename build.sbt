enablePlugins(ScalaJSPlugin)

name := "Heldenbogen"
scalaVersion := "2.13.8"
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.2.0"

// Kein "test" Verzeichnis benötigt
Compile / scalaSource := baseDirectory.value / "src"

scalaJSUseMainModuleInitializer := true
