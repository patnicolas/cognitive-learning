package com.cognitivelearning.tutorial.ast

import com.github.javaparser.ast.Node
import com.github.javaparser.metamodel.PropertyMetaModel
import com.github.javaparser.printer.DotPrinter

/**
  * Created by pnicolas on 6/5/18.
  */
private[cognitivelearning] final class AstPrinter extends DotPrinter(false) {
/*
  override def output(node: Node, parent: String, name: String, builder: StringBuilder): Unit = {
    val metaModel = node.getMetaModel
    val allPropertyMetaModels: Int = metaModel.getAllPropertyMetaModels().asScalaList
    val attributes = allPropertyMetaModels.filter(_.isAttribute)
      .filter(PropertyMetaModel::isSingular).collect(toList());
    List<PropertyMetaModel> subNodes = allPropertyMetaModels.stream().filter(PropertyMetaModel::isNode)
      .filter(PropertyMetaModel::isSingular).collect(toList());
    List<PropertyMetaModel> subLists = allPropertyMetaModels.stream().filter(PropertyMetaModel::isNodeList)
      .collect(toList());

    String ndName = nextNodeName();
    if (outputNodeType)
      builder.append(System.lineSeparator() + ndName + " [label=\"" + name + " (" + metaModel.getTypeName()
        + ")\"];");
    else
      builder.append(System.lineSeparator() + ndName + " [label=\"" +name + "\"];");

    if (parentNodeName != null)
      builder.append(System.lineSeparator() + parentNodeName + " -> " + ndName + ";");

    for (PropertyMetaModel a : attributes) {
      String attrName = nextNodeName();
      builder.append(System.lineSeparator() + " [label=\"" + a.getName() + "='"
        + a.getValue(node).toString() + "'\"];");
      builder.append(System.lineSeparator() + ndName + " -> " + attrName + ";");

    }

    for (PropertyMetaModel sn : subNodes) {
      Node nd = (Node) sn.getValue(node);
      if (nd != null)
        output(nd, ndName, sn.getName(), builder);
    }

    for (PropertyMetaModel sl : subLists) {
      NodeList<? extends Node> nl = (NodeList<? extends Node>) sl.getValue(node);
      if (nl != null && nl.isNonEmpty()) {
        String ndLstName = nextNodeName();
        builder.append(System.lineSeparator() + ndLstName + " [label=\"" + sl.getName() + "\"];");
        builder.append(System.lineSeparator() + ndName + " -> " + ndLstName + ";");
        String slName = sl.getName().substring(0, sl.getName().length() - 1);
        for (Node nd : nl)
        output(nd, ndLstName, slName, builder);
      }
    }
  }
  }
  */
}


/*

  public DotPrinter(boolean outputNodeType) {
        this.outputNodeType = outputNodeType;
    }

    public String output(Node node) {
        nodeCount = 0;
        StringBuilder output = new StringBuilder();
        output.append("digraph {");
        output(node, null, "root", output);
        output.append(System.lineSeparator() + "}");
        return output.toString();
    }

    public void output(Node node, String parentNodeName, String name, StringBuilder builder) {
        assertNotNull(node);
        NodeMetaModel metaModel = node.getMetaModel();
        List<PropertyMetaModel> allPropertyMetaModels = metaModel.getAllPropertyMetaModels();
        List<PropertyMetaModel> attributes = allPropertyMetaModels.stream().filter(PropertyMetaModel::isAttribute)
                .filter(PropertyMetaModel::isSingular).collect(toList());
        List<PropertyMetaModel> subNodes = allPropertyMetaModels.stream().filter(PropertyMetaModel::isNode)
                .filter(PropertyMetaModel::isSingular).collect(toList());
        List<PropertyMetaModel> subLists = allPropertyMetaModels.stream().filter(PropertyMetaModel::isNodeList)
                .collect(toList());

        String ndName = nextNodeName();
        if (outputNodeType)
            builder.append(System.lineSeparator() + ndName + " [label=\"" + name + " (" + metaModel.getTypeName()
                    + ")\"];");
        else
            builder.append(System.lineSeparator() + ndName + " [label=\"" +name + "\"];");

        if (parentNodeName != null)
            builder.append(System.lineSeparator() + parentNodeName + " -> " + ndName + ";");

        for (PropertyMetaModel a : attributes) {
            String attrName = nextNodeName();
            builder.append(System.lineSeparator() + " [label=\"" + a.getName() + "='"
                    + a.getValue(node).toString() + "'\"];");
            builder.append(System.lineSeparator() + ndName + " -> " + attrName + ";");

        }

        for (PropertyMetaModel sn : subNodes) {
            Node nd = (Node) sn.getValue(node);
            if (nd != null)
                output(nd, ndName, sn.getName(), builder);
        }

        for (PropertyMetaModel sl : subLists) {
            NodeList<? extends Node> nl = (NodeList<? extends Node>) sl.getValue(node);
            if (nl != null && nl.isNonEmpty()) {
                String ndLstName = nextNodeName();
                builder.append(System.lineSeparator() + ndLstName + " [label=\"" + sl.getName() + "\"];");
                builder.append(System.lineSeparator() + ndName + " -> " + ndLstName + ";");
                String slName = sl.getName().substring(0, sl.getName().length() - 1);
                for (Node nd : nl)
                    output(nd, ndLstName, slName, builder);
            }
        }
    }
 */