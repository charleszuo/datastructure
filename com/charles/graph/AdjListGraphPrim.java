package com.charles.graph;

/**
 * 
 * @author charleszuo@126.com
 * 基于邻接表的普里姆算法实现
 */
public class AdjListGraphPrim {

	public void prim(AdjListGraph adjGraph){
		int numVertex = adjGraph.getNumVertex();
		VertexNode[] vertexs = adjGraph.getVertexs();
		int INFINITY = AdjListGraph.INFINITY;
		
		int[] lowCost = new int[numVertex];
		int[] adjvex = new int[numVertex];
		
		//初始化 
		for(int i = 0; i < numVertex; i++){
			lowCost[i] = INFINITY;
			adjvex[i] = 0;
		}
		
		// V0 先进入U集合
		lowCost[0] = 0;
		EdgeNode point = vertexs[0].getFirstEdge();
		while(point != null){
			int vexIndex = point.getAdjvex();
			lowCost[vexIndex] = point.getWeight();
			point = point.getNextEdge();
		}
		
		int m = 0;
		
		for(int j = 1; j < numVertex; j++){
			int min = INFINITY;
			for(int k = 1; k < numVertex; k++){
				if(lowCost[k] != 0 && lowCost[k] < min){
					min = lowCost[k];
					m = k;
				}
			}
			
			System.out.println("Edge (" + adjvex[m] + ", " + m + ")");
			lowCost[m] = 0;
			
			point = vertexs[m].getFirstEdge();
			while(point != null){
				int vexIndex = point.getAdjvex();
				if(lowCost[vexIndex] != 0 && point.getWeight() < lowCost[vexIndex]){
					lowCost[vexIndex] = point.getWeight();
					adjvex[vexIndex] = m;
				}
				point = point.getNextEdge();
			}
		}
		
	}
	

	public static void main(String[] args){
		AdjListGraph adjGraph = new AdjListGraph(9);
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
		
		EdgeNode edgeNode01 = new EdgeNode(1, 10);
		EdgeNode edgeNode05 = new EdgeNode(5, 11);
		vertexs[0].setFirstEdge(edgeNode05);
		edgeNode01.setNextEdge(vertexs[0].getFirstEdge());
		vertexs[0].setFirstEdge(edgeNode01);
		
		EdgeNode edgeNode10 = new EdgeNode(0, 10);
		EdgeNode edgeNode12 = new EdgeNode(2, 18);
		EdgeNode edgeNode16 = new EdgeNode(6, 16);
		EdgeNode edgeNode18 = new EdgeNode(8, 12);
		vertexs[1].setFirstEdge(edgeNode18);
		edgeNode16.setNextEdge(vertexs[1].getFirstEdge());
		vertexs[1].setFirstEdge(edgeNode16);
		edgeNode12.setNextEdge(vertexs[1].getFirstEdge());
		vertexs[1].setFirstEdge(edgeNode12);
		edgeNode10.setNextEdge(vertexs[1].getFirstEdge());
		vertexs[1].setFirstEdge(edgeNode10);
		
		EdgeNode edgeNode21 = new EdgeNode(1, 18);
		EdgeNode edgeNode23 = new EdgeNode(3, 22);
		EdgeNode edgeNode28 = new EdgeNode(8, 8);
		vertexs[2].setFirstEdge(edgeNode28);
		edgeNode23.setNextEdge(vertexs[2].getFirstEdge());
		vertexs[2].setFirstEdge(edgeNode23);
		edgeNode21.setNextEdge(vertexs[2].getFirstEdge());
		vertexs[2].setFirstEdge(edgeNode21);
		
		EdgeNode edgeNode32 = new EdgeNode(2, 22);
		EdgeNode edgeNode34 = new EdgeNode(4, 20);
		EdgeNode edgeNode36 = new EdgeNode(6, 24);
		EdgeNode edgeNode37 = new EdgeNode(7, 16);
		EdgeNode edgeNode38 = new EdgeNode(8, 21);
		vertexs[3].setFirstEdge(edgeNode38);
		edgeNode37.setNextEdge(vertexs[3].getFirstEdge());
		vertexs[3].setFirstEdge(edgeNode37);
		edgeNode36.setNextEdge(vertexs[3].getFirstEdge());
		vertexs[3].setFirstEdge(edgeNode36);
		edgeNode34.setNextEdge(vertexs[3].getFirstEdge());
		vertexs[3].setFirstEdge(edgeNode34);
		edgeNode32.setNextEdge(vertexs[3].getFirstEdge());
		vertexs[3].setFirstEdge(edgeNode32);
		
		EdgeNode edgeNode43 = new EdgeNode(3, 20);
		EdgeNode edgeNode45 = new EdgeNode(5, 26);
		EdgeNode edgeNode47 = new EdgeNode(7, 7);
		vertexs[4].setFirstEdge(edgeNode47);
		edgeNode45.setNextEdge(vertexs[4].getFirstEdge());
		vertexs[4].setFirstEdge(edgeNode45);
		edgeNode43.setNextEdge(vertexs[4].getFirstEdge());
		vertexs[4].setFirstEdge(edgeNode43);
		
		EdgeNode edgeNode50 = new EdgeNode(0, 11);
		EdgeNode edgeNode54 = new EdgeNode(4, 26);
		EdgeNode edgeNode56 = new EdgeNode(6, 17);
		vertexs[5].setFirstEdge(edgeNode56);
		edgeNode54.setNextEdge(vertexs[5].getFirstEdge());
		vertexs[5].setFirstEdge(edgeNode54);
		edgeNode50.setNextEdge(vertexs[5].getFirstEdge());
		vertexs[5].setFirstEdge(edgeNode50);
		
		EdgeNode edgeNode61 = new EdgeNode(1, 16);
		EdgeNode edgeNode63 = new EdgeNode(3, 24);
		EdgeNode edgeNode65 = new EdgeNode(5, 17);
		EdgeNode edgeNode67 = new EdgeNode(7, 19);
		vertexs[6].setFirstEdge(edgeNode67);
		edgeNode65.setNextEdge(vertexs[6].getFirstEdge());
		vertexs[6].setFirstEdge(edgeNode65);
		edgeNode63.setNextEdge(vertexs[6].getFirstEdge());
		vertexs[6].setFirstEdge(edgeNode63);
		edgeNode61.setNextEdge(vertexs[6].getFirstEdge());
		vertexs[6].setFirstEdge(edgeNode61);
		
		EdgeNode edgeNode73 = new EdgeNode(3, 16);
		EdgeNode edgeNode74 = new EdgeNode(4, 7);
		EdgeNode edgeNode76 = new EdgeNode(6, 19);
		vertexs[7].setFirstEdge(edgeNode76);
		edgeNode74.setNextEdge(vertexs[7].getFirstEdge());
		vertexs[7].setFirstEdge(edgeNode74);
		edgeNode73.setNextEdge(vertexs[7].getFirstEdge());
		vertexs[7].setFirstEdge(edgeNode73);
		
		EdgeNode edgeNode81 = new EdgeNode(1, 12);
		EdgeNode edgeNode82 = new EdgeNode(2, 8);
		EdgeNode edgeNode83 = new EdgeNode(3, 21);
		vertexs[8].setFirstEdge(edgeNode83);
		edgeNode82.setNextEdge(vertexs[8].getFirstEdge());
		vertexs[8].setFirstEdge(edgeNode82);
		edgeNode81.setNextEdge(vertexs[8].getFirstEdge());
		vertexs[8].setFirstEdge(edgeNode81);

		AdjListGraphPrim adjListGraphPrim = new AdjListGraphPrim();
		adjListGraphPrim.prim(adjGraph);
	}
}


