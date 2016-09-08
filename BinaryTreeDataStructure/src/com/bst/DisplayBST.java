package com.bst;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Deque;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import com.bst.BST;
import com.logfile.LogFile;

public class DisplayBST extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final int FRAME_WIDTH = 1500;
	private static final int FRAME_HEIGHT = 1000;
	private static final int ROOT_HEIGHT = 50;
	private static final int DIAMETER = 40;
	private static final int RADIUS = DIAMETER / 2;
	private BST<?, ?> tree;
	Timer timer = new Timer(7, this);
	private Font font;
	private Ellipse2D circle;
	private double x_root_coord, y_root_coord;
	
	public DisplayBST(BST<?, ?> tree){
		this.setTree(tree);
		x_root_coord = FRAME_WIDTH / 2;
		y_root_coord = ROOT_HEIGHT;
		font = new Font("TimesRoman", Font.BOLD, 18);
		JFrame frame = new JFrame("Binary Search Tree");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public BST<?, ?> getTree() {
		return tree;
	}

	public void setTree(BST<?, ?> tree) {
		this.tree = tree;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.GRAY);
		Graphics2D g2 = (Graphics2D) g;
		drawTree(getTree().getRoot(), g2);
		timer.start();
	}
	
	public void drawTree(BST<?,?>.Node node, Graphics g) {
		try {
			if(node == null) throw new IllegalArgumentException();
		} catch(IllegalArgumentException e){
			LogFile.warn(this, "drawTree(BST<?,?>.Node " + node + ", Graphics g)", "Null value not allowed as a Node", e);
			return;
		}
		Graphics2D g2 = (Graphics2D) g;
		Line2D lineLeft, lineRight;
		g2.setFont(font);
		int count1 = 0, count2 = 1, count3 = 1;
		Deque<BST<?,?>.Node> deque = new LinkedList<BST<?,?>.Node>();
		Deque<double[]> dequePoints = new LinkedList<double[]>();
		deque.push(node);
		double[] points = {x_root_coord, y_root_coord};
		dequePoints.push(points);
		while(!deque.isEmpty()){
			count2--;
			BST<?,?>.Node currNode = deque.removeLast();
			points = dequePoints.removeLast();
			double x_left = points[0];
			double y_left = points[1];
			double x_right = points[0];
			double y_right = points[1];
			int x_prev = (int) x_left;
			int y_prev = (int) y_left;
			Object key = currNode.getKey();
			String keyStr = key.toString();
			if(currNode.getLeft() != null){
				count1++;
				deque.push(currNode.getLeft());
				if(currNode == getTree().getRoot()){
					lineLeft = new Line2D.Double(x_left, y_left, x_left - 230, y_left + ((DIAMETER / 2) * 3));
					circle = new Ellipse2D.Double(x_left - RADIUS, y_left - RADIUS, DIAMETER, DIAMETER);
					x_prev = (int) x_left;
					y_prev = (int) y_left;
					x_left = x_left - 230;
					y_left = y_left + ((DIAMETER / 2) * 3);
				}
				else if(count3 % 2 == 0){
					lineLeft = new Line2D.Double(x_left, y_left, x_left - (DIAMETER * 2), y_left + (DIAMETER * 2));
					circle = new Ellipse2D.Double(x_left - RADIUS, y_left - RADIUS, DIAMETER, DIAMETER);
					x_prev = (int) x_left;
					y_prev = (int) y_left;
					x_left = x_left - (DIAMETER * 2);
					y_left = y_left + (DIAMETER * 2);
				}
				else {
					lineLeft = new Line2D.Double(x_left, y_left, x_left - (DIAMETER), y_left + (DIAMETER * 2));
					circle = new Ellipse2D.Double(x_left - RADIUS, y_left - RADIUS, DIAMETER, DIAMETER);
					x_prev = (int) x_left;
					y_prev = (int) y_left;
					x_left = x_left - (DIAMETER);
					y_left = y_left + (DIAMETER * 2);
				}
				g2.setColor(Color.BLACK);
				g2.draw(lineLeft);
				g2.setColor(Color.LIGHT_GRAY);
				g2.fill(circle);
				g2.setColor(Color.BLACK);
				g2.drawString(keyStr, x_prev - 5, y_prev + 5);
				double[] pts = {x_left, y_left};
				dequePoints.push(pts);
			}
			if(currNode.getRight() != null){
				count1++;
				deque.push(currNode.getRight());
				if(currNode == getTree().getRoot()){
					lineRight = new Line2D.Double(x_right, y_right, x_right + 230, y_right + ((DIAMETER / 2) * 3));
					circle = new Ellipse2D.Double(x_right - RADIUS, y_right - RADIUS, DIAMETER, DIAMETER);
					x_prev = (int) x_right;
					y_prev = (int) y_right;
					x_right = x_right + 230;
					y_right = y_right + ((DIAMETER / 2) * 3);
				}
				else if(count3 % 2 == 0){
					lineRight = new Line2D.Double(x_right, y_right, x_right + (DIAMETER * 2), y_right + (DIAMETER * 2));
					circle = new Ellipse2D.Double(x_right - RADIUS, y_right - RADIUS, DIAMETER, DIAMETER);
					x_prev = (int) x_right;
					y_prev = (int) y_right;
					x_right = x_right + (DIAMETER * 2);
					y_right = y_right + (DIAMETER * 2);
				}
				else {
					lineRight = new Line2D.Double(x_right, y_right, x_right + (DIAMETER), y_right + (DIAMETER * 2));
					circle = new Ellipse2D.Double(x_right - RADIUS, y_right - RADIUS, DIAMETER, DIAMETER);
					x_prev = (int) x_right;
					y_prev = (int) y_right;
					x_right = x_right + (DIAMETER);
					y_right = y_right + (DIAMETER * 2);
				}
				g2.setColor(Color.BLACK);
				g2.draw(lineRight);
				g2.setColor(Color.LIGHT_GRAY);
				g2.fill(circle);
				g2.setColor(Color.BLACK);
				g2.drawString(keyStr, x_prev - 5, y_prev + 5);
				double[] pts = {x_right, y_right};
				dequePoints.push(pts);
			}
			if(count2 == 0){
				count2 = count1;
				count1 = 0;
				count3++;
			}
			g2.setColor(Color.LIGHT_GRAY);
			circle = new Ellipse2D.Double(x_left - RADIUS, y_left - RADIUS, DIAMETER, DIAMETER);
			g2.fill(circle);
			g2.setColor(Color.BLACK);
			g2.drawString(keyStr, x_prev - 5, y_prev + 5);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
