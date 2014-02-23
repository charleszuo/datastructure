package com.charles.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author charleszuo@126.com
 * 基于边集数组的克鲁斯卡尔算法实现
 */
public class EdgeGraphKruskal {
	
	public void sortByWeight(List<Edge> edgeList, Edge[] edges){
		Collections.sort(edgeList);
		edgeList.toArray(edges);
	}
	
	/**
	 *  需要选对边集数组按照权值从小到大排序，
	 *  再把排序后的边加到TE集合（miniSpanEdges数组）里面来，如果待加的边跟现有的连通分量构成回路，那么这条边不能加入TE集合，直到TE集合中的数量 = n -1
	 */
	public void kruskalMinispanTree(List<Edge> edgeList, EdgeGraph graph){
		Edge[] edges = graph.getEdges();
		sortByWeight(edgeList, edges);
		int numVertex = graph.getNumVertex();
		int numEdge = graph.getNumEdge();
		
		int[] miniSpanEdges = new int[numVertex];
		int count = 0;
		int i = 0;
		while(i < numEdge && count < numVertex){
			// 考察这条边能不能加到TE集合里面来
			Edge edge = edges[i];
			int begin = findPosition(miniSpanEdges, edge.getBegin());
			int end = findPosition(miniSpanEdges, edge.getEnd());
			if(begin != end){
				System.out.println("Edge (" + edge.getBegin() + ", " + edge.getEnd() + ")");
				miniSpanEdges[begin] = end;
			}
			i ++;
		}
		
	}
	
	/* 克鲁斯卡尔算法的关键在于findPosition函数，在TE集合里面查找加入的顶点在当前TE集合的连通分量中的下一个位置
	*  miniSpanEdges 里面的边最后也是构成一棵生成树
	*/
	private int findPosition(int[] miniSpanEdges, int nodeIndex){
		// n个顶点的miniSpanEdges里面，总会有一个位置的值是0
		while(miniSpanEdges[nodeIndex] > 0){
			nodeIndex = miniSpanEdges[nodeIndex];
		}
		return nodeIndex;
	}
	
	public static void main(String[] args){
		EdgeGraph edgeGraph = new EdgeGraph(9, 15);
		List<Edge> edgeList = new ArrayList<Edge>();
		edgeList.add(new Edge(0, 1, 10));
		edgeList.add(new Edge(0, 5, 11));
		edgeList.add(new Edge(1, 2, 18));
		edgeList.add(new Edge(2, 3, 22));
		edgeList.add(new Edge(3, 4, 20));
		edgeList.add(new Edge(4, 5, 26));
		edgeList.add(new Edge(1, 6, 16));
		edgeList.add(new Edge(1, 8, 12));
		edgeList.add(new Edge(2, 8, 8));
		edgeList.add(new Edge(3, 8, 21));
		edgeList.add(new Edge(3, 6, 24));
		edgeList.add(new Edge(3, 7, 16));
		edgeList.add(new Edge(4, 7, 7));
		edgeList.add(new Edge(5, 6, 17));
		edgeList.add(new Edge(6, 7, 19));
		
		EdgeGraphKruskal edgeGraphKruskal = new EdgeGraphKruskal();
		edgeGraphKruskal.kruskalMinispanTree(edgeList, edgeGraph);
		
	}
	
}
