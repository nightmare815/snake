package com.tianbin.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Food {
	Random rand = new Random();
	int row = 0;
	int col = 0;
	int x,y;

	public void paint(Graphics g) {
		x = col * Yard.nodeSize + Yard.x;
		y = row * Yard.nodeSize + Yard.y;
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(this.x, this.y, Yard.nodeSize, Yard.nodeSize);
		
		g.setColor(c);
	}
	
	public void reAppear() {
        this.row = rand.nextInt(Yard.nodeCount);
        this.col = rand.nextInt(Yard.nodeCount);
    }
}
