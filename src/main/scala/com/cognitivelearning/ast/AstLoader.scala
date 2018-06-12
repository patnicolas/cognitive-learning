package com.cognitivelearning.ast

import java.io.{FileNotFoundException, IOException}

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.mxgraph.view.mxGraph

import scala.io.BufferedSource
import scala.io.Source._


/**
  *
  * @param astFile
  */
private[cognitivelearning] final class AstLoader protected(astFile: String) {

  private[this] val content: Option[String] = {
    var src: BufferedSource = null
    try {
      src = fromFile(astFile)
      val output: String = src.mkString
      src.close
      Some(output)
    }
    catch {
      case e: FileNotFoundException =>
        println(s"Failed to locate AST file ${e.getMessage}")
        None
      case e: IOException =>
        println(s"Failed to load AST file ${e.getMessage}")
        None
      case _: Throwable =>
        println("Undefined error loading AST")
        None
    }
    finally {
      if( src != null)
        try {
          src.close
        }
      catch {
        case e: IOException =>
          println(s"Failed to close AST file ${e.getMessage}")
          None
      }
    }
  }

  @inline
  final def getAstObject: Option[AstObject] = content.map( AstLoader.getAstObject(_) )


  def getVisual(): Unit = ???
}


/**
  * Companion singleton for AstLoader
  */
private[cognitivelearning] final object AstLoader {

  final val mapper = new ObjectMapper with ScalaObjectMapper
  mapper.registerModule(DefaultScalaModule)
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

  def getAstVariable(content: String): AstVariable = mapper.readValue(content, classOf[AstVariable])
  def getAstValue(content: String): AstValue = mapper.readValue(content, classOf[AstValue])
  def getAstName(content: String): AstName = mapper.readValue(content, classOf[AstName])
  def getAstStatement(content: String): AstStatement = mapper.readValue(content, classOf[AstStatement])
  def getAstBody(content: String): AstBody = mapper.readValue(content, classOf[AstBody])
  def getAstParameter(content: String): AstParameter = mapper.readValue(content, classOf[AstParameter])
  def getAstType(content: String): AstType = mapper.readValue(content, classOf[AstType])
  def getAstObject(content: String): AstObject = mapper.readValue(content, classOf[AstObject])
  def getAstExpression(content: String): AstExpression = mapper.readValue(content, classOf[AstExpression])


  def write(astName: AstName): String = mapper.writeValueAsString(astName)
  def write(astVariable: AstVariable): String = mapper.writeValueAsString(astVariable)
  def write(astObject: AstObject): String = mapper.writeValueAsString(astObject)
  def write(astType: AstType): String = mapper.writeValueAsString(astType)
  def write(astVarType: AstVarType): String = mapper.writeValueAsString(astVarType)
  def write(astTarget: AstTarget): String = mapper.writeValueAsString(astTarget)
  def write(astParameter: AstParameter): String = mapper.writeValueAsString(AstParameter)
  def write(astExpression: AstExpression): String = mapper.writeValueAsString(AstExpression)
  def write(astStatement: AstStatement): String = mapper.writeValueAsString(AstStatement)
  def write(astValue: AstValue): String = mapper.writeValueAsString(astValue)
  def write(astBody: AstBody): String = mapper.writeValueAsString(astBody)


  /**
    * Implicit conversion from an astObject and graph
    */
  implicit val astObject2Graph = (astObject: AstObject, graph: mxGraph) => {
    val parent = graph.getDefaultParent

    var x: Int = 10
    var y: Int = 10

    astObject.types.foreach(_type => {
      val classType = if( _type.isInterface.toBoolean ) "interface" else "class"
      val className = s"$classType: ${_type.name.identifier}"
      val vClass = graph.insertVertex(parent, null, className, x, y, 100, 20)
      val vVariables: AstVariables = _type.members.head.asInstanceOf[AstVariables]

      x += 30
      vVariables.variables.foreach(variable => {
        y += 30
        val cName = s"${variable.name.identifier}: ${variable._type._type}"
        val vVariable = graph.insertVertex(parent, null, cName, x, y, 100, 20)
        graph.insertEdge(parent, null, "", vClass, vVariable)
      })
      x -= 30
      val astMethod: AstMethod = _type.members(1).asInstanceOf[AstMethod]
      val astParameters = astMethod.parameters
        .parameters
        .map(param => s"${param.name.identifier}: ${param._type._type}").mkString(", ")

      val astName = s"${astMethod.name.identifier}: $astParameters"
      y += 30
      val vMethod = graph.insertVertex(parent, null, astName, x, y, 200, 20)
      graph.insertEdge(parent, null, "", vClass, vMethod)
      x += 30
      astMethod.body.statements.foreach(statement => {
        y += 30
        val exp = statement.expression
        val cStatement = s"${exp.target.name.identifier}, ${exp.operator}, ${exp.value.name.identifier}"
        val vStatement = graph.insertVertex(parent, null, cStatement, x, y, 200, 20)
        graph.insertEdge(parent, null, "", vClass, vStatement)
      })
    })
  }

  def apply(astFile: String): AstLoader = new AstLoader(astFile)
}
