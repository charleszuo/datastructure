package com.charles.graph;

/**
 * 
 * @author charleszuo@126.com 
 * 基于邻接矩阵的迪杰斯特拉算法实现, 用于计算单源点到其他各点的最短距离
 */
public class ShortestPathDijkstra {

	/**
	 * 
	 * @param mGraph
	 * @param sourceNode
	 * 
	 *            迪杰斯特拉算法的核心和普里姆算法很相似，都是先找最近距离的点，然后再修正最短距离集合。
	 *            不同点是普里姆算法找的是U到V集合中的点的最短距离，是多点对多点，最短距离集合里面存的是多个点到V的最短距离
	 *            迪杰斯特拉算法是计算源点到其他各点的最小距离，最短距离集合里面存的是源点到各点的最短距离
	 */
	public void dijikstra(MatrixGraph mGraph) {
		int numVertex = mGraph.getNumVertex();
		// 各边的权重
		int[][] pathWeight = mGraph.getEdges();

		int[] shortestPathValue = new int[numVertex];
		int[] previousVertex = new int[numVertex];
		boolean[] found = new boolean[numVertex];

		for (int i = 0; i < numVertex; i++) {
			// 初始化 0点到其他各点的最短距离
			shortestPathValue[i] = pathWeight[0][i];
		}

		found[0] = true;
		int m = 0;
		for (int j = 1; j < numVertex; j++) {
			int min = MatrixGraph.INFINITY;
			for (int k = 0; k < numVertex; k++) {
				if (!found[k] && shortestPathValue[k] < min) {
					min = shortestPathValue[k];
					m = k;
				}
			}
			found[m] = true;
			// 修正起点到各点的最短距离
			for (int n = 0; n < numVertex; n++) {
				if (!found[n]
						&& shortestPathValue[m] + pathWeight[m][n] < shortestPathValue[n]) {
					shortestPathValue[n] = shortestPathValue[m]
							+ pathWeight[m][n];
					previousVertex[n] = m;
				}
			}
		}

		// 打印结果
		for (int w = 1; w < shortestPathValue.length; w++) {
			System.out.println("Source vertex " + 0 + " to vertex " + w
					+ " shortest path = " + shortestPathValue[w]);
			StringBuilder sb = new StringBuilder();
			sb.append(w);
			sb.append(" >-- ");
			int previous = w;
			while (previousVertex[previous] != 0) {
				sb.append(previousVertex[previous]);
				sb.append(" >-- ");
				previous = previousVertex[previous];
			}
			sb.append(0);
			sb.reverse();
			System.out.println("Path is " + sb.toString());
			System.out.println("------------------");
		}
	}

	public static void main(String[] args) {
		MatrixGraph mGraph = new MatrixGraph(9);
		int[][] edges = mGraph.getEdges();
		edges[0][1] = 1;
		edges[0][2] = 5;
		edges[1][0] = 1;
		edges[1][2] = 3;
		edges[1][3] = 7;
		edges[1][4] = 5;
		edges[2][0] = 5;
		edges[2][1] = 3;
		edges[2][4] = 1;
		edges[2][5] = 7;
		edges[3][1] = 7;
		edges[3][4] = 2;
		edges[3][6] = 3;
		edges[4][1] = 5;
		edges[4][2] = 1;
		edges[4][3] = 2;
		edges[4][5] = 3;
		edges[4][6] = 6;
		edges[4][7] = 9;
		edges[5][2] = 7;
		edges[5][4] = 3;
		edges[5][7] = 5;
		edges[6][3] = 3;
		edges[6][4] = 6;
		edges[6][2] = 2;
		edges[6][8] = 7;
		edges[7][4] = 9;
		edges[7][5] = 5;
		edges[7][6] = 2;
		edges[7][8] = 4;
		edges[8][6] = 7;
		edges[8][7] = 4;

		ShortestPathDijkstra shortestPath = new ShortestPathDijkstra();
		shortestPath.dijikstra(mGraph);
	}
}
