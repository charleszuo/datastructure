package com.charles.graph;

/**
 * 
 * @author charleszuo@126.com
 * AOE网的关键路径算法，计算etv的时候就是计算起点到各点的最长距离的过程。这个过程中可以记录下起点到各点的最长路径
 * 这个路径应该就是关键路径 ???
 */
public class CriticalPath_Charles {
	
	public CriticalPath_Charles(int numVertex){
		etv = new int[numVertex];
		previousVertex = new int[numVertex];
	}
	
	// 事件最早完成时间
	private int[] etv;
	
	// 记录起点到各点的最长路径经过的点
	private int[] previousVertex;
	
	public int calcETV(AdjListGraph adjGraph){
		int numVertex = adjGraph.getNumVertex();
		VertexNode[] vertexs = adjGraph.getVertexs();
		// etv 事件最早完成时间就是起点到各点的最长所需的时间。 求etv的过程就是对AOV拓扑排序的过程
		java.util.Stack<Integer> stack = new java.util.Stack<Integer>();
		// 检查是否有回环
		int count = 0;
		// 初始化，把入度为0的顶点进栈
		for(int i = 0; i < numVertex; i++){
			if(vertexs[i].getIn() == 0){
				stack.push(i);
			}
		}
		
		while(stack.size() > 0){
			int top = stack.pop();
			count ++;
		
			EdgeNode point = vertexs[top].getFirstEdge();
			while(point != null){
				int adjvex = point.getAdjvex();
				vertexs[adjvex].setIn(vertexs[adjvex].getIn() - 1);
				if(vertexs[adjvex].getIn() == 0){
					stack.push(adjvex);
				}
				//计算到起点到各点的最长距离
				if(etv[top] + point.getWeight() > etv[adjvex]){
					etv[adjvex] = etv[top] + point.getWeight();
					previousVertex[adjvex] = top;
				}
				point = point.getNextEdge();
			}
		}
		
		
		return count == numVertex ? 0 : -1;
	}
	
	public int[] getPreviousVertex(){
		return this.previousVertex;
	}

	public static void main(String[] args){
		AdjListGraph adjGraph = new AdjListGraph(10);
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
		
		EdgeNode edgeNode01 = new EdgeNode(1, 3);
		EdgeNode edgeNode02 = new EdgeNode(2, 4);
		vertexs[0].setFirstEdge(edgeNode01);
		edgeNode02.setNextEdge(vertexs[0].getFirstEdge());
		vertexs[0].setFirstEdge(edgeNode02);
		
		EdgeNode edgeNode13 = new EdgeNode(3, 5);
		EdgeNode edgeNode14 = new EdgeNode(4, 6);
		vertexs[1].setFirstEdge(edgeNode13);
		edgeNode14.setNextEdge(vertexs[1].getFirstEdge());
		vertexs[1].setFirstEdge(edgeNode14);
		
		EdgeNode edgeNode23 = new EdgeNode(3, 8);
		EdgeNode edgeNode25 = new EdgeNode(5, 7);
		vertexs[2].setFirstEdge(edgeNode23);
		edgeNode25.setNextEdge(vertexs[2].getFirstEdge());
		vertexs[2].setFirstEdge(edgeNode25);
		
		EdgeNode edgeNode34 = new EdgeNode(4, 3);
		vertexs[3].setFirstEdge(edgeNode34);
		
		EdgeNode edgeNode46 = new EdgeNode(6, 9);
		EdgeNode edgeNode47 = new EdgeNode(7, 4);
		vertexs[4].setFirstEdge(edgeNode46);
		edgeNode47.setNextEdge(vertexs[4].getFirstEdge());
		vertexs[4].setFirstEdge(edgeNode47);
		
		EdgeNode edgeNode57 = new EdgeNode(7, 6);
		vertexs[5].setFirstEdge(edgeNode57);
		
		EdgeNode edgeNode69 = new EdgeNode(9, 2);
		vertexs[6].setFirstEdge(edgeNode69);
		
		
		EdgeNode edgeNode78 = new EdgeNode(8, 5);
		vertexs[7].setFirstEdge(edgeNode78);
		
		EdgeNode edgeNode89 = new EdgeNode(9, 3);
		vertexs[8].setFirstEdge(edgeNode89);
		
		vertexs[0].setIn(0);
		vertexs[1].setIn(1);
		vertexs[2].setIn(1);
		vertexs[3].setIn(2);
		vertexs[4].setIn(2);
		vertexs[5].setIn(1);
		vertexs[6].setIn(1);
		vertexs[7].setIn(2);
		vertexs[8].setIn(1);
		vertexs[9].setIn(2);
		
		CriticalPath_Charles cPath = new CriticalPath_Charles(adjGraph.getNumVertex());
		if(cPath.calcETV(adjGraph) == 0){
			System.out.println("It is a topological graph, the critical path is below:");
			int[] previousVertex = cPath.getPreviousVertex();
			
			StringBuilder sb = new StringBuilder();
			int endVertex = previousVertex.length - 1;
			sb.append(endVertex);
			sb.append(" >-- ");
			int previous = endVertex;
			while (previousVertex[previous] != 0) {
				sb.append(previousVertex[previous]);
				sb.append(" >-- ");
				previous = previousVertex[previous];
			}
			sb.append(0);
			sb.reverse();
			System.out.println(sb.toString());
			
		}else{
			System.out.println("It is not a topological graph, no critical path.");
		}
	}
}
