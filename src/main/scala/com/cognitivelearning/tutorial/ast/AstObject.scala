package com.cognitivelearning.tutorial.ast


private[cognitivelearning] sealed trait AstRoot
private[cognitivelearning] case class AstName(identifier: String)
private[cognitivelearning] case class AstVarType(_type: String)
private[cognitivelearning] case class AstVariable(name: AstName, _type: AstVarType)
private[cognitivelearning] case class AstTarget(name: AstName, scope: String)
private[cognitivelearning] case class AstValue(name: AstName)
private[cognitivelearning] case class AstExpression(operator: String, target: AstTarget, value: AstValue)
private[cognitivelearning] case class AstStatement(expression: AstExpression)
private[cognitivelearning] case class AstStatements(statements: Array[AstStatement])
private[cognitivelearning] case class AstVariables(variables: Array[AstVariable])
private[cognitivelearning] case class AstParameters(parameters: Array[AstParameter])
private[cognitivelearning] case class AstBody(statements: List[AstStatement]) // , name: AstName, parameters: Array[AstParameter])
private[cognitivelearning] case class AstParameter(isVarArgs: String, name: AstName, _type: AstVarType)
private[cognitivelearning] case class AstMethod(body: AstBody, name: AstName, parameters: AstParameters)
private[cognitivelearning] case class AstType(isInterface: String, name: AstName, members: Array[_])

/**
  * Created by pnicolas on 6/6/18.
  */
private[cognitivelearning] case class AstObject(types: Array[AstType])
