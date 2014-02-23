package com.charles.graph;

/**
 * 
 * @author charleszuo@126.com 
 * AOE网的关键路径算法，关键是计算etv, ltv, ete, lte的值
 * etv: 事件最早开始时间，是起点到每个点的最长路径
 * ltv: 事件最晚开始时间，最后一个顶点的ltv = etv。其他顶点的ltv = min(下一个顶点的ltv - 弧长)
 * ete: 活动最早开始时间，等于弧起点的etv
 * lte: 活动最晚开始时间，等于弧终点的ltv - 弧长
 * 当一条弧的ete = lte，这条弧就是关键路径
 * 图的邻接表表示法
 */
public class CriticalPath {
	/**
	 * 
	 * @param numVertex
	 * 
	 */
	public CriticalPath(int numVertex){
		etv = new int[numVertex];
		ltv = new int[numVertex];
		ltvStack = new java.util.Stack<Integer>();
	}
	
	// 事件最早完成时间
	private int[] etv;
	
	// 事件最晚完成时间
	private int[] ltv;
	
	private java.util.Stack<Integer> ltvStack;
	
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
		int[] previousVertex = new int[numVertex];
		
		while(stack.size() > 0){
			int top = stack.pop();
			count ++;
			// 加入ltvStack
			ltvStack.push(top);
			
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

	public void criticalPath(AdjListGraph adjGraph){
		int numVertex = adjGraph.getNumVertex();
		VertexNode[] vertexs = adjGraph.getVertexs();
		calcETV(adjGraph);
		// ltv[numVertex - 1] = etv[numVertex -1] 整个工程的最早完成时间 = 最晚完成时间 = 最长的路径
		for(int i = 0; i < numVertex; i++){
			ltv[i] = etv[numVertex - 1];
		}
		while(ltvStack.size() > 0){
			int top = ltvStack.pop();
			
			EdgeNode point = vertexs[top].getFirstEdge();
			while(point != null){
				int adjvex = point.getAdjvex();
				if(ltv[adjvex] - point.getWeight() < ltv[top]){
					ltv[top] = ltv[adjvex] - point.getWeight();
				}
				point = point.getNextEdge();
			}
		}
		
		// 从起点开始求每条弧的ete, lte
		for(int begin = 0; begin < numVertex; begin++){
			EdgeNode point = vertexs[begin].getFirstEdge();
			while(point != null){
				int end = point.getAdjvex();
				// 计算ete, lte
				int ete = etv[begin];
				int lte = ltv[end] - point.getWeight();
				if(ete == lte){
					System.out.println("Critical path: (" + begin + " , " + end + ")");
				}
				point = point.getNextEdge();
			}
		}
		
	
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
		
		CriticalPath cPath = new CriticalPath(adjGraph.getNumVertex());
		cPath.criticalPath(adjGraph);
	}
}
