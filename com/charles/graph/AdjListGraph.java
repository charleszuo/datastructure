package com.charles.graph;

/**
 * 
 * @author charleszuo@126.com
 * 图的邻接表表示法
 */
public class AdjListGraph {
	
	private VertexNode[] vertexs;

	private int numVertex;

	private boolean[] visitied;

	public static int INFINITY = 65535;

	public AdjListGraph(int numVertex) {
		this.numVertex = numVertex;
		this.vertexs = new VertexNode[numVertex];
		this.visitied = new boolean[numVertex];
	}

	public VertexNode[] getVertexs() {
		return vertexs;
	}

	public int getNumVertex() {
		return numVertex;
	}

	public boolean[] getVisitied() {
		return visitied;
	}

	public static int getINFINITY() {
		return INFINITY;
	}
	
}

class EdgeNode {
	int adjvex; // 顶点所在的下标

	EdgeNode nextEdge;

	int weight; // 权值

	public EdgeNode(int adjvex, int weight) {
		this.adjvex = adjvex;
		this.weight = weight;
	}
	
	public EdgeNode(int adjvex) {
		this.adjvex = adjvex;
	}

	public int getAdjvex() {
		return adjvex;
	}

	public void setAdjvex(int adjvex) {
		this.adjvex = adjvex;
	}

	public EdgeNode getNextEdge() {
		return nextEdge;
	}

	public void setNextEdge(EdgeNode nextEdge) {
		this.nextEdge = nextEdge;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}

class VertexNode {
	private int in;
	
	private String value;

	private EdgeNode firstEdge;

	public VertexNode(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public EdgeNode getFirstEdge() {
		return firstEdge;
	}

	public void setFirstEdge(EdgeNode firstEdge) {
		this.firstEdge = firstEdge;
	}

	public int getIn() {
		return in;
	}

	public void setIn(int in) {
		this.in = in;
	}

}
