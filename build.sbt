name := "JavaParserTest"

version := "0.1"

scalaVersion := "2.11.12"

crossScalaVersions := Seq("2.11.12")

libraryDependencies ++= Seq(
  "com.google.code.javaparser"	% "javaparser"        % "1.0.11",
  "commons-io"									% "commons-io"        % "2.4",
  "org.scalatest"     					%% "scalatest" 		    % "2.2.5" % "test"
)
