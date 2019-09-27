package com.tianbin.snake;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 游戏的窗口实现
 * 
 * @author Administrator
 *
 */
public class Yard extends Frame {
	public static void main(String[] args) {
		Yard yard = new Yard();

	}
	
	public static int nodeSize = 15; // 每个结点的像素尺寸
	public static int nodeCount = 30; // 每行每列的结点数量
	public static int areaSize = nodeSize * nodeCount; // 小蛇的活动区域尺寸

	public static int x = areaSize / 2; // 小蛇活动区域的绘图坐标x,y
	public static int y = areaSize / 2;
	Snake snake = new Snake();
	Food food = new Food();
	
	Yard() {
		this.setSize(areaSize * 2, areaSize * 2);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				snake.keyPressed(e);
			}
		});
		
		
		while (true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			this.repaint();
		}
	}

	@Override
	public void paint(Graphics g) {
		//画蛇的活动区域
		Color c = g.getColor();
		//每次重画前,清空之前的内容,把区域全部涂成白色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.BLACK);
		
		for (int i = 0; i <= nodeCount; i++) {
			g.drawLine(x, y + nodeSize * i, x + areaSize, y + nodeSize * i);
			g.drawLine(x + nodeSize * i, y, x + nodeSize * i, y + areaSize);
		}
		
		
			
			snake.paint(g);
			food.paint(g);
			snake.eat(food);
			snake.AI(food);
			g.setColor(c);
		}
	
	
	
	//double buffer
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(this.getWidth(), this.getHeight());
        }
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0,  null);
    }

	}


