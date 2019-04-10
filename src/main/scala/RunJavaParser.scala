import java.io.{File, FileInputStream}

import japa.parser.JavaParser
import japa.parser.ast.CompilationUnit
import org.apache.commons.io.FileUtils

import scala.collection.JavaConverters._

object RunJavaParser {
  val ROOT_FOLDER_PATH = "src/main/resources"

  def main(args: Array[String]): Unit = {
    val javaRootDir: File = new File(ROOT_FOLDER_PATH)
    val javaFiles = FileUtils.listFiles(javaRootDir, Array[String]("java"), true).asScala

    for(javaFile <- javaFiles){
      val is = new FileInputStream(javaFile)
      val cu = compile(is)

      cu.foreach{ c =>
        val classes = c.getTypes.asScala.toArray

        for(jc <- classes){
          println(JavaClass(jc))
        }
      }
    }
  }

  private def compile(is: FileInputStream): Option[CompilationUnit] = {
    try {
      Some(JavaParser.parse(is))
    }
    catch {
      case e: Exception => None
    } finally {
      is.close
    }
  }

}
