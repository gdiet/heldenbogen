### Compilieren

Inkrementelles Kompilieren funktioniert nicht zuverlässig genug, deswegen empfiehlt sich ein "clean" bei jedem Build.

In SBT stehen die Tasks `createPage` (schneller Entwickler-Build) und `fullOptPage` (Release-Build) zur Verfügung um das Script im `docs` Verzeichnis zu aktualisieren.

`sbt> ;clean;createPage`

oder für Build bei jeder Änderung

`sbt> ~;clean;createPage`

Das Verzeichnis muss `docs` heißen damit GitHub Pages verwendet werden kann.
