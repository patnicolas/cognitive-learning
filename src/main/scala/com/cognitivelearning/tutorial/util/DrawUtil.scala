package com.cognitivelearning.tutorial.util

import java.awt.Color
import javax.swing.{JFrame, JPanel}

import com.cognitivelearning.tutorial.ast.AstObject
import com.mxgraph.swing.mxGraphComponent
import com.mxgraph.view.mxGraph


/**
  * Created by pnicolas on 6/3/18.
  */
private[cognitivelearning] final object DrawUtil {

  final def display(astObject: AstObject)(implicit gen: (AstObject, mxGraph) => Unit): Unit = {
    val graph = new mxGraph

    graph.getModel.beginUpdate
    try {
      gen(astObject, graph)
    }
    finally
      graph.getModel.endUpdate

    val graphComponent = new mxGraphComponent(graph)
    val frame: JFrame = new JFrame
    frame.add(graphComponent)
    frame.pack
    frame.setVisible(true)
  }

  final def display(filename: String): Unit = {
    import javax.swing.{JFrame, ImageIcon, JLabel}

    val frame = new JFrame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.setSize(450, 360)

    val jPanel = new JPanel
    jPanel.setBackground(Color.WHITE)
 //   jPanel.setSize(450, 360)
    val icon = new ImageIcon("/Users/pnicolas/IdeaProjects/learning/")
    val label = new JLabel(icon)
    jPanel.add(label)
    frame.getContentPane().add(jPanel)
   // frame.pack
    frame.setVisible(true)
  }
/*
  final def displayGraph(inputFile: String): Unit = {
    val mxGraph: MxGraph = new MxGraph
    val parent = graph.getDefaultParent

    mxGraph.getModel.beginUpdate
    try {

    } finally {
      mxGraph.getModel.endUpdate
    }
  }
  */
}
