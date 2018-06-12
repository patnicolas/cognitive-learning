package com.cognitivelearning.tutorial.coding

import com.github.javaparser.JavaParser
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.printer._


/**
  * Generation for Abstract Syntax tree
  * @param code
  */
private[cognitivelearning] final class AstGenerator protected(code: String) {
  import JavaParser._

  private[this] val cu: CompilationUnit = parse(code)

  override def toString: String = (new YamlPrinter(false)).output(cu)

  def print: String = {
    val config = new PrettyPrinterConfiguration
    config.setPrintComments(false)
    config.setPrintJavadoc(false)

    val prettyPrinter = new PrettyPrinter(config)
    prettyPrinter.print(cu)
  }

  def toJson: String = {
    (new JsonPrinter(false)).output(cu)
  }

  def toDot: String = {
    (new DotPrinter(false)).output(cu)
  }


  def toGraph(input: String): Unit = {
    val runnable = new Runnable {
      override def run: Unit = {
        getGraph(input)
      }
    }
    (new Thread(runnable)).start
    Thread.sleep(1000)
  }

  private def getGraph(input: String = "ast"): Unit = {
      import java.io.{FileWriter, IOException, PrintWriter}

      var printWriter: PrintWriter = null
      try {
        val dotFile = s"${input}.dot"
        val outputFile = s"${input}.png"
        val fileWriter = new FileWriter(dotFile)
        printWriter = new PrintWriter(fileWriter)
/*
        import sys.process.Process
        import java.io.File
        import com.cognitivelearn.util.DrawUtil._

        val args = Seq[String]("dot", "-Tjpg", "asta.dot")
        val cmd = Process(args) #> new File("aa.jpg")
        val exitValue = cmd.run

        //Thread.sleep(2000)
        display("/Users/pnicolas/IdeaProjects/learning/aa.jpg")
        */
      }
      finally {
        try {
          if (printWriter != null)
            printWriter.close
        }
        catch {
          case e: IOException => println("Error, cannot generate graph file")
        }
      }
    }
}



private[cognitivelearning] final object AstGenerator {
  def apply(code: String): AstGenerator = new AstGenerator(code)
}
