package com.tianbin.snake;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Snake {
	static int i = 1;

	Node head; // 蛇身的头
	Node tail; // 蛇身的尾
	Dir dir = Dir.R;

	Snake() {
		head = new Node(10, 10);
		tail = head;
	}

	public void deleteTail() {
		if (tail == null)
			return;

		tail = tail.prev;
		tail.next.prev = null;
		tail.next = null;
	}

	public void addToHead() {
		Node n = null;
		switch (dir) {
		case U:
			n = new Node(head.row - 1, head.col);
			break;
		case D:
			n = new Node(head.row + 1, head.col);
			break;
		case L:
			n = new Node(head.row, head.col - 1);
			break;
		case R:
			n = new Node(head.row, head.col + 1);
			break;
		}

		head.prev = n;
		n.next = head;
		head = n;
	}

	public void paint(Graphics g) {

		Node n = head;
		while (n != null) {
			// 超出边框部分不打印(如果食物出现在边框边界处,吃完食物后,addToHead就会在边框外部添加一节身体,
			// 遍历输出的时候跳过边框外部的节点)
			if (n.x >= Yard.x && n.x <= Yard.x + (Yard.nodeCount - 1) * Yard.nodeSize && n.y >= Yard.y
					&& n.y <= Yard.y + (Yard.nodeCount - 1) * Yard.nodeSize) {
				n.paint(g);
			}
			n = n.next;
		}
		move();
	}

	public void move() {
		addToHead();
		deleteTail();
		boundaryCheck();
		gameOver();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_UP:
			if(dir == Dir.D) {
				dir = Dir.D;
			}else {
				dir = Dir.U;
			}
			break;
		case KeyEvent.VK_DOWN:
			if(dir == Dir.U) {
				dir = Dir.U;
			}else {
				dir = Dir.D;
			}
			break;
		case KeyEvent.VK_LEFT:
			if(dir == Dir.R) {
				dir = Dir.R;
			}else {
				dir = Dir.L;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(dir == Dir.L) {
				dir = Dir.L;
			}else {
				dir = Dir.R;
			}
			break;

		}
	}

	private void boundaryCheck() {
		/*
		 * 按照行列进行边界检测时,会出现异常情况
		 * if(head.row <= 0) { head.row = Yard.nodeCount-1; } else if(head.row
		 * >= Yard.nodeCount-1){ head.row = 0; } if(head.col <= 0) { head.col =
		 * Yard.nodeCount-1; } else if(head.col >= Yard.nodeCount-1) { head.col
		 * = 0; }
		 */
		
		//按照坐标检测,则不会出现异常
		if (head.x > (Yard.nodeCount - 1) * Yard.nodeSize + Yard.x) {
			head.x = Yard.x;
			head.col = 0;
		} else if (head.x < Yard.x) {
			head.x = (Yard.nodeCount - 1) * Yard.nodeSize + Yard.x;
			head.col = Yard.nodeCount - 1;
		}

		if (head.y > (Yard.nodeCount - 1) * Yard.nodeSize + Yard.y) {
			head.y = Yard.y;
			head.row = 0;
		} else if (head.y < Yard.y) {
			head.y = (Yard.nodeCount - 1) * Yard.nodeSize + Yard.y;
			head.row = Yard.nodeCount - 1;
		}
	}

	public void eat(Food food) {
		if (head.x == food.x && head.y == food.y) {
			addToHead();
			System.out.println(++i);
			food.reAppear();
		}
	}
	
	public void gameOver() {
		Node n = head;
		while(n.next !=null) {
			n = n.next;
			if(head.row == n.row && head.col == n.col) {
				try {
					Thread.sleep(1000 * 3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(-1);
			}
		}
	}
	
	public void AI(Food food) {
		if(head.x > food.x) {
			if(dir != Dir.R) {
				dir = Dir.L;
			}
		}else if(head.x < food.x) {
			/*if(dir != Dir.L)
				dir = Dir.R;*/
			dir = Dir.L;
		}else {
			if(head.y == food.y) {
			}else {
				if(dir != Dir.U) {
					
					dir = Dir.D;
				}
			}
		}
		
		
		
		/*if(head.x != food.x) {
			if(head.x > food.x) {
				if(dir != Dir.R) {
					dir = Dir.L;
				}
			}else {
				if(dir != Dir.L)
				dir = Dir.R;
			}
		}else if(head.x == food.x) {
			if(head.y > food.y) {
				if(dir != Dir.D) {
					
					dir = Dir.U;
				}
			}else if(head.y == food.y) {
			}else {
				if(dir != Dir.U) {
					
					dir = Dir.D;
				}
			}
		}*/
	}

}
