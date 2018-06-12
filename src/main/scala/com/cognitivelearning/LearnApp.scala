package com.cognitivelearning

import com.cognitivelearning.coding.AstGenerator
import com.github.javaparser.JavaParser
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.printer.YamlPrinter

/**
  * Created by pnicolas on 6/3/18.
  */
object LearnApp extends App {
  val code = "public class MyClass {\nprivate final int n;\npublic MyClass(final int n) {\nthis.n = n;\n}\n}"

  val astGen = AstGenerator(code)
  println(astGen.print)

  println(astGen.toString)
  println(astGen.toJson)
  astGen.toGraph("abc")
  // astGen.toGraph("asta")

}
