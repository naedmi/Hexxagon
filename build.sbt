val scala3Version = "3.0.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq("com.novocode" % "junit-interface" % "0.11" % "test",
    "org.scalactic" %% "scalactic" % "3.2.10", 
    "org.scalatest" %% "scalatest" % "3.2.10" % "test"),

    jacocoExcludes := Seq("**/Main*.*")

    /*jacocoCoverallsServiceName := "github-actions", 
    jacocoCoverallsBranch := sys.env.get("CI_BRANCH"),
    jacocoCoverallsPullRequest := sys.env.get("GITHUB_EVENT_NAME"),
    jacocoCoverallsRepoToken := sys.env.get("COVERALLS_REPO_TOKEN")*/
  )
.enablePlugins(JacocoCoverallsPlugin)
