import design.hamu._

lazy val commonSettings = Seq(
  scalaVersion := "2.12.11",
  organization := "design.hamu",
  version := "0.0.1",
  scalacOptions := Seq("-Ypartial-unification", "-deprecation"),
  dependencyUpdatesFilter -= moduleFilter(name = "scala-library"),
  dependencyUpdatesFailBuild := true
)

lazy val publishSettings = Seq(
  coverageMinimum := 55,
  coverageFailOnMinimum := true
)

lazy val root = project
  .in(file("."))
  .aggregate(core)

lazy val core = project
  .in(file("core"))
  .settings(
    name := "core",
    commonSettings,
    publishSettings,
    libraryDependencies ++= Seq(
      Dependencies.Cats.core,
      Dependencies.Circe.core,
      Dependencies.Circe.parser,
      Dependencies.Circe.generic
    ) ++ Seq(
      Dependencies.Scalatest.core
    ).map(_ % "test"),
    addCompilerPlugin(CompilerPlugins.MacroParadise.core cross CrossVersion.full)
  )

