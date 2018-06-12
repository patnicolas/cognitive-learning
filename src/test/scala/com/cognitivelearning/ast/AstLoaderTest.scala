package com.cognitivelearning.ast


import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by pnicolas on 6/6/18.
  */
final class AstLoaderTest extends FlatSpec with Matchers {

  it should "succeed write JSON components" in {
    AstGen.print
  }


  it should "succeed read JSON object" in {
    val input = """{"types":[{"isInterface":"false","name":{"identifier":"MyClass"},"members":[{"variables":[{"name":{"identifier":"n"},"_type":{"_type":"INT"}}]},{"body":{"statements":[{"expression":{"operator":"ASSIGN","target":{"name":{"identifier":"n"},"scope":{}},"value":{"name":{"identifier":"n"}}}}]},"name":{"identifier":"MyClass"},"parameters":[{"isVarArgs":"false","name":{"identifier":"n"},"_type":{"_type":"INT"}}]}]}]}"""

   // val input = """{"types":[{"isInterface":"true","name":{"identifier":"12L"},"members":[{"variables":[{"name":{"identifier":"1212"},"_type":{"_type":"INT"}},{"name":{"identifier":"12L"},"_type":{"_type":"LONG"}}]},"body":{"statements":[{"expression":{"operator":"Mult","target":{"name":{"identifier":"12L"},"scope":"Partial"},"value":{"name":{"identifier":"1212"}}}},{"expression":{"operator":"Add","target":{"name":{"identifier":"1212"},"scope":"All"},"value":{"name":{"identifier":"999"}}}}]},"name":{"identifier":"1212"},"parameters":[{"isVarArgs":"true","name":{"identifier":"1212"},"_type":{"_type":"LONG"}}]}]}]}"""

    //  val astObject = AstLoader.getAstObject(input)

  //  val output: String = AstLoader.write(AstGen.astObject)
  //  println(s"astOutput: ${output}")
    val astObjectD: AstObject = AstLoader.getAstObject(input)
  }


  /*
  it should "succeed parsing JSON name" in {
    // val input = "{\"identifier\":\"77\"}"
    val input = """{"identifier":"77"}"""

    val astName = AstLoader.write(AstName("1111"))
    println(s"astName: $astName")
    val astNameR: AstName = AstLoader.getAstName(input)
  }


  it should "succeed parsing JSON value" in {
    val input = """{"name":{"identifier":"n"}}"""

    val astValue = AstLoader.getAstValue(input)
    println(s"astValue ${astValue.toString}")
  }

  it should "succeed parsing JSON variable" in {
    val input = """{"name":{"identifier":"8"},"_type":{"_type":"INT"}}"""

    val astVar = AstLoader.write(AstVariable(AstName("1111"), AstVarType("INT")))
    println(s"astVar ${astVar.toString}")
    val astVariable = AstLoader.getAstVariable(astVar)
    println(s"astVariable ${astVariable.toString}")
  }

  it should "succeed parsing JSON statement" in {
    val input = """{"statement":{"expression":{"operator":"ASSIGN","target":{"name":{"identifier":"88"},"scope":{}},"value":{"name":{"identifier":"88"}}}}"""
    val astStatement = AstLoader.getAstStatement(input)
    println(s"astStatement ${astStatement.toString}")
  }

  it should "succeed parsing JSON parameter" in {
    val input = """{"isVarArgs":"false","name":{"identifier":"12L"},"type":{"type":"LONG"}}"""
    val astParameter = AstLoader.getAstParameter(input)
    println(s"astParameter ${astParameter.toString}")
  }

  it should "succeed parsing JSON expression" in {
    val input = """{"expression":{"operator":"ASSIGN","target":{"name":{"identifier":"889"},"scope":{}},"value":{"name":{"identifier":"91"}}}}"""
    val astExpression = AstLoader.getAstExpression(input)
    println(s"astExpression ${astExpression.toString}")
  }

  it should "succeed parsing JSON body" in {
    val input = """{"body":{"statements":[{"expression":{"operator":"ASSIGN","target":{"name":{"identifier":"889"},"scope":{}},"value":{"name":{"identifier":"91"}}}}]}"""
    val astBody = AstLoader.getAstBody(input)
    println(s"astBody ${astBody.toString}")
  }


  it should "succeed parsing JSON object" in {
    val input = """{"types":[{"isInterface":"false","name":{"identifier":"MyClass"},"members":[{"variables":[{"name":{"identifier":"n"},"type":{"type":"INT"}}]},{"body":{"statements":[{"expression":{"operator":"ASSIGN","target":{"name":{"identifier":"n"},"scope":{}},"value":{"name":{"identifier":"n"}}}}]},"name":{"identifier":"MyClass"},"parameters":[{"isVarArgs":"false","name":{"identifier":"n"},"type":{"type":"INT"}}]}]}]"""

    val astType1 = AstVarType("INT")
    val astType2 = AstVarType("LONG")
    val astName1 = AstName("1212")
    val astName2 = AstName("12L")
    val astVariable1 = AstVariable(astName1, astType1)
    val astVariable2 = AstVariable(astName2, astType2)
   // val astMember = AstMember(Array[AstVariable](astVariable1, astVariable2))
    /// / val astObject = AstObject(Array[AstType]())
    val astObjectD = AstLoader.getAstObject(input)
    println(s"astObject ${astObjectD.toString}")
  }
*/

  final object AstGen {
    val astVarType1 = AstVarType("INT")
    val astVarType2 = AstVarType("LONG")
    val astName1 = AstName("1212")
    val astName2 = AstName("12L")
    val astVariable1 = AstVariable(astName1, astVarType1)
    val astVariable2 = AstVariable(astName2, astVarType2)
    val astValue1 = AstValue(astName1)
    val astValue2 = AstValue(AstName("999"))
    val astTarget1 = AstTarget(astName1, "All")
    val astTarget2 = AstTarget(astName2, "Partial")
    val astExpression1 = AstExpression("Add", astTarget1, astValue2)
    val astExpression2 = AstExpression("Mult", astTarget2, astValue1)
    val astExpression3 = AstExpression("Minue", astTarget2, astValue2)
    val astStatement1 = AstStatement(astExpression1)
    val astStatement2 = AstStatement(astExpression2)
    val astParameter1 = AstParameter("true", astName1, astVarType2)
    val astParameter2 = AstParameter("false", astName2, astVarType1)
    val astBody1 = AstBody(List[AstStatement](astStatement1, astStatement2))
    val astBody2 = AstBody(List[AstStatement](astStatement2))

    val astVariables = AstVariables(Array[AstVariable](astVariable1))
    val astParameters = AstParameters(Array[AstParameter](astParameter1))
    val astMethod = AstMethod(astBody1, astName1, astParameters)
    val membersEl = Array[Any](astVariables, astMethod)

    val astType1 = AstType("true", astName2, membersEl)
    val astObject = AstObject(Array[AstType](astType1))
    /*
    sInterface: Boolean, name: AstName, members: Array[AstMember])
     */
    def print: Unit = {
      println(s"astType: ${AstLoader.write(astType1)}")
      println(s"astName: ${AstLoader.write(astName1)}")
      println(s"astTarget: ${AstLoader.write(astTarget1)}")
      println(s"astValue: ${AstLoader.write(astValue1)}")
      println(s"astVariable: ${AstLoader.write(astVariable1)}")
      println(s"astExpression: ${AstLoader.write(astExpression1)}")
      println(s"astParameter: ${AstLoader.write(astParameter1)}")
      println(s"astStatement: ${AstLoader.write(astStatement1)}")
      println(s"astBody: ${AstLoader.write(astBody1)}")
      println(s"astType: ${AstLoader.write(astType1)}")
      println(s"astObject: ${AstLoader.write(astObject)}")
    }

  }

}

/*
{"types":[{"isInterface":"false","name":{"identifier":"MyClass"},"members":[{"variables":[{"name":{"identifier":"n"},"type":{"type":"INT"}}]},
{"body":{"statements":[{"expression":{"operator":"ASSIGN","target":{"name":{"identifier":"n"},"scope":{}},"value":{"name":{"identifier":"n"}}}}]}
,"name":{"identifier":"MyClass"},"parameters":[{"isVarArgs":"false","name":{"identifier":"n"},"type":{"type":"INT"}}]}]}]}

 */

/*
root:
    types:
        - type:
            isInterface: "false"
            name:
                identifier: "MyClass"
            members:
                - member:
                    variables:
                        - variable:
                            name:
                                identifier: "n"
                            type:
                                type: "INT"
                - member:
                    body:
                        statements:
                            - statement:
                                expression:
                                    operator: "ASSIGN"
                                    target:
                                        name:
                                            identifier: "n"
                                        scope:
                                    value:
                                        name:
                                            identifier: "n"
                    name:
                        identifier: "MyClass"
                    parameters:
                        - parameter:
                            isVarArgs: "false"
                            name:
                                identifier: "n"
                            type:
                                type: "INT"
 */