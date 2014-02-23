package com.charles.graph;

/**
 * 
 * @author charleszuo@126.com
 * AOV网，AOE网的拓扑排序
 */
public class TopologicalSort {
	
	public static int sort(AdjListGraph adjGraph){
		int numVertex = adjGraph.getNumVertex();
		VertexNode[] vertexs = adjGraph.getVertexs();
		int count = 0;
		java.util.Stack<Integer> stack = new java.util.Stack<Integer>();
		for(int i = 0; i < numVertex; i++){
			VertexNode vertex = vertexs[i];
			if(vertex.getIn() == 0){
				stack.push(i);
			}
		}
		while(stack.size() > 0){
			Integer top = stack.pop();
			count ++;
			System.out.println("Node " + vertexs[top].getValue());
			EdgeNode edge = vertexs[top].getFirstEdge();
			while(edge != null){
				VertexNode vertex = vertexs[edge.getAdjvex()];
				vertex.setIn(vertex.getIn() - 1);
				if(vertex.getIn() == 0){
					stack.push(edge.getAdjvex());
				}
				edge = edge.getNextEdge();
			}
		}
		return count < numVertex ? -1 : 0;
	}
	
	public static void main(String[] args){
		AdjListGraph adjGraph = new AdjListGraph(14);
		VertexNode[] vertexs = adjGraph.getVertexs();
		vertexs[0] = new VertexNode("V0");
		vertexs[1] = new VertexNode("V1");
		vertexs[2] = new VertexNode("V2");
		vertexs[3] = new VertexNode("V3");
		vertexs[4] = new VertexNode("V4");
		vertexs[5] = new VertexNode("V5");
		vertexs[6] = new VertexNode("V6");
		vertexs[7] = new VertexNode("V7");
		vertexs[8] = new VertexNode("V8");
		vertexs[9] = new VertexNode("V9");
		vertexs[10] = new VertexNode("V10");
		vertexs[11] = new VertexNode("V11");
		vertexs[12] = new VertexNode("V12");
		vertexs[13] = new VertexNode("V13");
		
		EdgeNode edgeNode04 = new EdgeNode(4);
		EdgeNode edgeNode05 = new EdgeNode(5);
		EdgeNode edgeNode011 = new EdgeNode(11);
		vertexs[0].setFirstEdge(edgeNode011);
		edgeNode05.setNextEdge(vertexs[0].getFirstEdge());
		vertexs[0].setFirstEdge(edgeNode05);
		edgeNode04.setNextEdge(vertexs[0].getFirstEdge());
		vertexs[0].setFirstEdge(edgeNode04);
		
		EdgeNode edgeNode12 = new EdgeNode(2);
		EdgeNode edgeNode14 = new EdgeNode(4);
		EdgeNode edgeNode18 = new EdgeNode(8);
		vertexs[1].setFirstEdge(edgeNode18);
		edgeNode14.setNextEdge(vertexs[1].getFirstEdge());
		vertexs[1].setFirstEdge(edgeNode14);
		edgeNode12.setNextEdge(vertexs[1].getFirstEdge());
		vertexs[1].setFirstEdge(edgeNode12);
		
		EdgeNode edgeNode25 = new EdgeNode(5);
		EdgeNode edgeNode26 = new EdgeNode(6);
		EdgeNode edgeNode29 = new EdgeNode(9);
		vertexs[2].setFirstEdge(edgeNode29);
		edgeNode26.setNextEdge(vertexs[2].getFirstEdge());
		vertexs[2].setFirstEdge(edgeNode26);
		edgeNode25.setNextEdge(vertexs[2].getFirstEdge());
		vertexs[2].setFirstEdge(edgeNode25);
		
		EdgeNode edgeNode32 = new EdgeNode(2);
		EdgeNode edgeNode313 = new EdgeNode(13);
		vertexs[3].setFirstEdge(edgeNode313);
		edgeNode32.setNextEdge(vertexs[3].getFirstEdge());
		vertexs[3].setFirstEdge(edgeNode32);
		
		EdgeNode edgeNode47 = new EdgeNode(7);
		vertexs[4].setFirstEdge(edgeNode47);
		
		EdgeNode edgeNode58 = new EdgeNode(8);
		EdgeNode edgeNode512 = new EdgeNode(12);
		vertexs[5].setFirstEdge(edgeNode512);
		edgeNode58.setNextEdge(vertexs[5].getFirstEdge());
		vertexs[5].setFirstEdge(edgeNode58);
		
		EdgeNode edgeNode65 = new EdgeNode(5);
		vertexs[6].setFirstEdge(edgeNode65);
		
		
		EdgeNode edgeNode87 = new EdgeNode(7);
		vertexs[8].setFirstEdge(edgeNode87);
		
		EdgeNode edgeNode910 = new EdgeNode(10);
		EdgeNode edgeNode911 = new EdgeNode(11);
		vertexs[9].setFirstEdge(edgeNode911);
		edgeNode910.setNextEdge(vertexs[9].getFirstEdge());
		vertexs[5].setFirstEdge(edgeNode910);
		
		EdgeNode edgeNode1013 = new EdgeNode(13);
		vertexs[10].setFirstEdge(edgeNode1013);
		
		EdgeNode edgeNode129 = new EdgeNode(9);
		vertexs[12].setFirstEdge(edgeNode129);
		
		vertexs[0].setIn(0);
		vertexs[1].setIn(0);
		vertexs[2].setIn(2);
		vertexs[3].setIn(0);
		vertexs[4].setIn(2);
		vertexs[5].setIn(3);
		vertexs[6].setIn(1);
		vertexs[7].setIn(2);
		vertexs[8].setIn(2);
		vertexs[9].setIn(1);
		vertexs[10].setIn(1);
		vertexs[11].setIn(2);
		vertexs[12].setIn(1);
		vertexs[13].setIn(2);
		
		TopologicalSort.sort(adjGraph);
	}
}
