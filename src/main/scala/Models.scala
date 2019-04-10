import japa.parser.ast.`type`.Type
import japa.parser.ast.body.{ClassOrInterfaceDeclaration, FieldDeclaration, MethodDeclaration, Parameter, TypeDeclaration}

import scala.collection.mutable.ArrayBuffer
import scala.collection.JavaConverters._

case class Variable(name: String, tp: Type) {
  override def toString: String = s"$tp $name"
}

case class Method(name: String, retTp: Type, args: Array[Variable]){
  override def toString: String = {
    val buf = new StringBuffer()
    buf.append(s"$retTp $name(")
    args.foreach(v => buf.append(s"${v.tp} ${v.name}"))
    buf.append(")")
    buf.toString
  }
}
case class JavaClass(name: String, variables:Array[Variable], methods: Array[Method]){
  override def toString: String = {
    val buf = new StringBuffer()
    buf.append(s"Class Name: $name\n")
    buf.append(s"Variables: ${variables.map(_.toString).mkString(", ")}\n")
    methods.foreach(m => buf.append(s"Method: ${m.toString}\n"))
    buf.toString
  }
}

object Method extends Serializable {
  def apply(md: MethodDeclaration): Method = {
    val name = md.getName
    val retTp = md.getType
    val params = Option(md.getParameters)

    val args = params match {
      case Some(v) => v.asScala.map {
        param: Parameter => Variable(param.getId.getName, param.getType)
      }.toArray[Variable]
      case None => Array.empty[Variable]
    }

    Method(name, retTp, args)
  }
}

object JavaClass extends Serializable {
  private def getFields(fd: FieldDeclaration): List[Variable] = {
    val variables = Option(fd.getVariables)

    variables match  {
      case Some(vs) => vs.asScala.map(v => Variable(v.getId.getName, fd.getType)).toList
      case None => List.empty[Variable]
    }
  }

  def apply(td: TypeDeclaration): JavaClass = {
    val name = td.getName
    val members = Option(td.getMembers)

    val variables = new ArrayBuffer[Variable]()
    val methods = new ArrayBuffer[Method]()

    members.foreach{ items =>
      items.asScala.toArray.foreach {
        case v: FieldDeclaration => variables.appendAll(getFields(v))
        case m: MethodDeclaration => methods.append(Method(m))
        case c: ClassOrInterfaceDeclaration => println(s"Ignoring inner class ${c.getName}")
        case _ => println("Not none")
      }
    }

    JavaClass(name, variables.toArray, methods.toArray)
  }

}
