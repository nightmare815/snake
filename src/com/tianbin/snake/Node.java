package com.tianbin.snake;

import java.awt.Color;
import java.awt.Graphics;

public class Node {
	int row;	//节点所在行
	int col;	//节点所在列
	int x;		//节点的坐标
	int y;
	Node prev;	//结点的前驱节点
	Node next;	//节点的后继节点
	
	public Node(int row, int col) {
		this.row = row;
		this.col = col;
		x = col * Yard.nodeSize + Yard.x;
		y = row * Yard.nodeSize + Yard.y;
	}
	
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillRect(x, y, Yard.nodeSize, Yard.nodeSize);
		
		g.setColor(c);
	}
	
}
