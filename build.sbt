name := "ScalaPlayground"

version := "1.0"

scalaVersion := "2.11.7"

resolvers ++= Seq(
  "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/"
  )


libraryDependencies ++= Seq(
  "org.reactivemongo" %% "reactivemongo" % "0.11.7",
  "com.github.nscala-time" %% "nscala-time" % "2.2.0"
)