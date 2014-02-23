package com.charles.graph;


/**
 * 
 * @author charleszuo@126.com
 * 图的边集数组表示法
 */
public class EdgeGraph {
	private int numEdge;
	
	private int numVertex;
	
	private Edge[] edges;
	
	public EdgeGraph(int numVertex, int numEdge){
		this.numVertex = numVertex;
		this.numEdge = numEdge;
		edges = new Edge[numEdge];
	}

	public int getNumEdge() {
		return numEdge;
	}

	public int getNumVertex() {
		return numVertex;
	}

	public Edge[] getEdges() {
		return edges;
	}
	
}

class Edge implements Comparable<Edge>{
	private int begin;
	
	private int end;
	
	private int weight;
	
	public Edge(int begin, int end, int weight){
		this.begin = begin;
		this.end = end;
		this.weight = weight;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		if(this.weight > o.getWeight()){
			return 1;
		}
		
		return this.weight < o.getWeight() ? -1 : 0;
	}
	
}

