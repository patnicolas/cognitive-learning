package com.cognitivelearning.tutorial.util

import com.cognitivelearning.tutorial.ast._
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by pnicolas on 6/10/18.
  */
final class DrawUtilTest extends FlatSpec with Matchers {
/*
  it should "succeed drawing a given graph" in {
    val graph = new mxGraph
    val parent = graph.getDefaultParent

    graph.getModel.beginUpdate

    try {
      val v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80, 30)
      val v2 = graph.insertVertex(parent, null, "World!", 240, 150, 80, 30)
      graph.insertEdge(parent, null, "Edge", v1, v2)
    } finally
      graph.getModel.endUpdate

    val graphComponent = new mxGraphComponent(graph)

    val frame: JFrame = new JFrame
    frame.add(graphComponent)
    frame.pack
    frame.setVisible(true)
    Thread.sleep(4000)
  }
*/

  it should "succeed drawing a graph from an AstObject" in {
    import AstLoader._
    DrawUtil.display(getAstObj)
  }

  private def getAstObj: AstObject = {
    val astName = AstName("12L")
    val astVarType = AstVarType("INT")
    val astVariable1 = AstVariable(astName, astVarType)
    val astVariables = AstVariables(Array[AstVariable](astVariable1))
    val astValue1 = AstValue(astName)
    val astValue2 = AstValue(AstName("999"))
    val astTarget1 = AstTarget(astName, "All")
    val astTarget2 = AstTarget(astName, "Partial")
    val astExpression1 = AstExpression("Add", astTarget1, astValue2)
    val astExpression2 = AstExpression("Mult", astTarget2, astValue1)
    val astStatement1 = AstStatement(astExpression1)
    val astStatement2 = AstStatement(astExpression2)
    val astBody = AstBody(List[AstStatement](astStatement1, astStatement2))
    val astParameter = AstParameter("true", astName, astVarType)
    val astParameters = AstParameters(Array[AstParameter](astParameter))
    val astMethod = AstMethod(astBody, astName, astParameters)
    val membersEl = Array[Any](astVariables, astMethod)
    val astType = AstType("true", astName, membersEl)

    AstObject(Array[AstType](astType))
  }
}
