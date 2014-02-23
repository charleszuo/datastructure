package com.charles.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author charleszuo@126.com
 * 图的邻接矩阵表示法
 */
public class MatrixGraph {
	private String[] vertexs;
	
	private int[][] edges;
	
	private int numVertex;
	
	public static int INFINITY = 65535;
	
	private boolean[] visited;
	
	public MatrixGraph(int numVertex){
		this.numVertex = numVertex;
		vertexs = new String[numVertex];
		edges = new int[numVertex][numVertex];
		for(int i = 0; i < numVertex; i++){
			for(int j = 0; j < numVertex; j++){
				if(i == j){
					edges[i][j] = 0;
				}else{
					edges[i][j] = INFINITY;
				}
			}
		}
		visited = new boolean[numVertex];
	}
	
	public String[] getVertexs() {
		return vertexs;
	}

	public void setVertexs(String[] vertexs) {
		this.vertexs = vertexs;
	}

	public int[][] getEdges() {
		return edges;
	}

	public void setEdges(int[][] edges) {
		this.edges = edges;
	}
	
	public int getNumVertex() {
		return numVertex;
	}

	public boolean[] getVisited() {
		return visited;
	}

	/**
	 * 
	 * @param index 顶点的序号
	 *  对连通分量的深度优先搜索
	 */
	public void DFS(int index){
		visited[index] = true;
		System.out.println("Node " + vertexs[index]);
		for(int j = 0; j < numVertex; j++){
			if(visited[j] != true && edges[index][j] == 1){
				DFS(j);
			}
		}
	}
	
	/**
	 * 对所有顶点进行深度优先搜索, 采用递归
	 * 如果存在多个连通分量，对每个连通分量都做DFS
	 */
	public void DFSTraverse(){
		for(int i = 0; i < numVertex; i++){
			if(visited[i] != true){
				DFS(i);
			}
		}
	}
	
	/**
	 * 对所有顶点进行深度优先搜索，采用队列
	 * 如果存在多个连通分量，对每个连通分量都做BFS
	 */
	public void BFSTraverse(){
		List<Integer> queue = new ArrayList<Integer>();
		for(int i = 0; i < numVertex; i++){
			if(visited[i] != true){
				visited[i] = true;
				queue.add(i);
				while(queue.size() != 0){
					int vertexIndex = queue.remove(0);
					System.out.println("Node " + vertexs[vertexIndex]);
					for(int j = 0; j < numVertex; j++){
						if(visited[j] != true && edges[vertexIndex][j] == 1){
							queue.add(j);
							visited[j] = true;
						}
					}
				}
			}
		}
		
		
		
		
		
	}
	
	public static void main(String[] args){
		MatrixGraph mGraph = new MatrixGraph(9);
		String[] vertexs = mGraph.getVertexs();
		vertexs[0] = "A";
		vertexs[1] = "B";
		vertexs[2] = "C";
		vertexs[3] = "D";
		vertexs[4] = "E";
		vertexs[5] = "F";
		vertexs[6] = "G";
		vertexs[7] = "H";
		vertexs[8] = "I";
		
		int[][] edges = mGraph.getEdges();
		edges[0][1] = 1;
		edges[0][5] = 1;
		edges[1][0] = 1;
		edges[1][2] = 1;
		edges[1][6] = 1;
		edges[1][8] = 1;
		edges[2][1] = 1;
		edges[2][3] = 1;
		edges[2][8] = 1;
		edges[3][2] = 1;
		edges[3][4] = 1;
		edges[3][6] = 1;
		edges[3][7] = 1;
		edges[3][8] = 1;
		edges[4][3] = 1;
		edges[4][5] = 1;
		edges[4][7] = 1;
		edges[5][0] = 1;
		edges[5][4] = 1;
		edges[5][6] = 1;
		edges[6][1] = 1;
		edges[6][3] = 1;
		edges[6][5] = 1;
		edges[6][7] = 1;
		edges[7][3] = 1;
		edges[7][4] = 1;
		edges[7][6] = 1;
		edges[8][1] = 1;
		edges[8][2] = 1;
		edges[8][3] = 1;
		
//		mGraph.DFSTraverse();
		mGraph.BFSTraverse();
	}
}
