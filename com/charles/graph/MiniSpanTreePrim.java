package com.charles.graph;

/**
 * 
 * @author charleszuo@126.com
 * 基于邻接矩阵的普里姆算法实现
 */
public class MiniSpanTreePrim {
	
	public static void prim(MatrixGraph graph){
		int numVertex = graph.getNumVertex();
		int[][] edges = graph.getEdges();
		// U集合中的点到V集合中的点的最短距离, lowCost = 0 表示该点已经进到U
		int[] lowCost = new int[numVertex];
		// 对应 lowCost，存lowCost的min是从哪个顶点到哪个顶点. adjvex[终点] = 起点
		int[] adjvex = new int[numVertex];
		
		
		for(int i = 0; i < numVertex; i++){
			lowCost[i] = edges[0][i];
			adjvex[i] = 0;
		}
		
		int m = 0;
		
		for(int j = 1; j < numVertex; j++){
			//先根据lowCost找到V到U最短距离的点，打印点的信息，然后设置lowCost = 0把该点加到U
			int min = MatrixGraph.INFINITY;
			for(int k = 1; k < numVertex; k++){
				if(lowCost[k] != 0 && lowCost[k] < min){
					min = lowCost[k];
					m = k;
				}
			}
			
			System.out.println("Edge (" + adjvex[m] + ", " + m + ")");
			lowCost[m] = 0;
			
			//把最小距离的点加到U后，修正V中的点到U中的点的最短距离 lowCost,并设置相应的adjtex的值
			for(int n = 1; n < numVertex; n++){
				if(lowCost[n] != 0 && edges[m][n] < lowCost[n]){
					lowCost[n] = edges[m][n];
					adjvex[n] = m;
				}
			}
		}
		
		
		
	}

	public static void main(String[] args){
		MatrixGraph mGraph = new MatrixGraph(9);
		String[] vertexs = mGraph.getVertexs();
		vertexs[0] = "V0";
		vertexs[1] = "V1";
		vertexs[2] = "V2";
		vertexs[3] = "V3";
		vertexs[4] = "V4";
		vertexs[5] = "V5";
		vertexs[6] = "V6";
		vertexs[7] = "V7";
		vertexs[8] = "V8";
		
		int[][] edges = mGraph.getEdges();
		edges[0][1] = 10;
		edges[0][5] = 11;
		edges[1][0] = 10;
		edges[1][2] = 18;
		edges[1][6] = 16;
		edges[1][8] = 12;
		edges[2][1] = 18;
		edges[2][3] = 22;
		edges[2][8] = 8;
		edges[3][2] = 22;
		edges[3][4] = 20;
		edges[3][6] = 24;
		edges[3][7] = 16;
		edges[3][8] = 21;
		edges[4][3] = 20;
		edges[4][5] = 26;
		edges[4][7] = 7;
		edges[5][0] = 11;
		edges[5][4] = 26;
		edges[5][6] = 17;
		edges[6][1] = 16;
		edges[6][3] = 24;
		edges[6][5] = 17;
		edges[6][7] = 19;
		edges[7][3] = 16;
		edges[7][4] = 7;
		edges[7][6] = 19;
		edges[8][1] = 12;
		edges[8][2] = 8;
		edges[8][3] = 21;
		
		prim(mGraph);
	}
}
