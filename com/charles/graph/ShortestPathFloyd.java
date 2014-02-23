package com.charles.graph;


/**
 * 
 * @author charleszuo@126.com
 * 弗洛伊德算法计算的是各点到各点的最短距离，相当与对各点做迪杰斯特拉算法
 * 注意弗洛伊德算法的一个小问题是记录经过的路径时，只有当起点小于终点时才能正确显示路径，比如0到8的路径可以正确显示，8到0的路径不能正确显示
 *
 */
public class ShortestPathFloyd {

	/**
	 * 
	 * @param mGraph
	 * @param sourceNode
	 */
	public void floyd(MatrixGraph mGraph) {
		int numVertex = mGraph.getNumVertex();
		// 各边的权重
		int[][] pathWeight = mGraph.getEdges();
		int INIFITY = MatrixGraph.INFINITY;

		int[][] shortestPathValue = new int[numVertex][numVertex];
		int[][] previousVertex = new int[numVertex][numVertex];

		for (int i = 0; i < numVertex; i++) {
			for(int j = 0; j < numVertex; j++){
				// 初始化 0点到其他各点的最短距离
				shortestPathValue[i][j] = pathWeight[i][j];
				// 初始时设置为起点
				previousVertex[i][j] = i;
			}
		}

		/**
		 * 弗洛伊德算法的核心是3重循环，第一重循环指定了通过当前循环的点来对各点到各点求最短距离。第二重循环指定了起点，第三重循环指定了终点 
		 * 比较起点到终点的直接距离是不是大于起点通过中转点到终点的距离，如果大的话，就修改起点到终点的最短路径，记录中转点
		 */
		for (int u = 0; u < numVertex; u++) {
			for (int v = 0; v < numVertex; v++) {
				for (int w = 0; w < numVertex; w++) {
					if(shortestPathValue[v][u] != INIFITY && shortestPathValue[u][w] != INIFITY
							&& shortestPathValue[v][w] > shortestPathValue[v][u] + shortestPathValue[u][w]){
						shortestPathValue[v][w] = shortestPathValue[v][u] + shortestPathValue[u][w];
						// v -> w改成 v -> u, u -> w，所以w的前一个点是u 
						previousVertex[v][w] = u;
					}
				}
			}
			
		}
		
		for (int m = 0; m < numVertex; m++) {
			for (int n = 0; n < numVertex; n++) {
				// 注意弗洛伊德算法的一个小问题是记录经过的路径时，只有当起点小于终点时才能正确显示路径，比如0到8的路径可以正确显示，8到0的路径不能正确显示
				if(m <= n){
					System.out.println("vertex " + m + " to vertex " + n
							+ " shortest path = " + shortestPathValue[m][n]);
					StringBuilder sb = new StringBuilder();
					sb.append(n);
					sb.append(" >-- ");
					int previous = n;
					while (previousVertex[m][previous] != m) {
						sb.append(previousVertex[m][previous]);
						sb.append(" >-- ");
						previous = previousVertex[m][previous];
					}
					sb.append(m);
					sb.reverse();
					System.out.println("Path is " + sb.toString());
					System.out.println("------------------");
				}
			}
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
		edges[6][7] = 2;
		edges[6][8] = 7;
		edges[7][4] = 9;
		edges[7][5] = 5;
		edges[7][6] = 2;
		edges[7][8] = 4;
		edges[8][6] = 7;
		edges[8][7] = 4;
		ShortestPathFloyd shortestPaht = new ShortestPathFloyd();
		shortestPaht.floyd(mGraph);
	}
}
