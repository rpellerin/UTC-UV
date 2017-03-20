/*
 * Created on 12 sept. 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package logogui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Traceur {
	private static Traceur instance;
	private Group g;
	private double initx = 300, inity = 300;   // position initiale
	private double posx = initx, posy = inity; // position courante
	private int angle = 90;
	private double teta;
	private boolean leve_crayon = false;
	private Color color = Color.BLACK;
	
	public Traceur() {
		setTeta();
	}

	public void setGraphics(Group g) {
		this.g = g;	
	}
	
	private int toInt(double a) {
		return (int) Math.round(a);
	}
	
	/**
	 * Avancer
	 * @param r Distance
	 */
	public void avance(double r) {
		double a = posx + r * Math.cos(teta) ;
		double b = posy - r * Math.sin(teta) ;
		int x1 = toInt(posx);
		int y1 = toInt(posy);
		int x2 = toInt(a);
		int y2 = toInt(b);
		if (!leve_crayon) {
			Line line = new Line(x1,y1,x2,y2);
			line.setStroke(color);
			g.getChildren().add(line);
		}
		posx = a;
		posy = b;
	}
	
	/**
	 * Reculer
	 * @param r Distance
	 */
	public void re(double r) {
		avance(-r);
	}
	
	/**
	 * Tourne droite
	 * @param r Rotation (angle en deg)
	 */
	public void td(double r) {
		angle = (angle - toInt(r)) % 360;
		setTeta();
	}
	
	/**
	 * Tourne gauche
	 * @param r Rotation
	 */
	public void tg(double r) {
		angle = (angle + toInt(r)) % 360;
		setTeta();
	}
	
	/**
	 * Lève le crayon
	 */
	public void lc() {
		leve_crayon = true;
	}
	
	/**
	 * Baisse le crayon
	 */
	public void bc() {
		leve_crayon = false;
	}
	
	/**
	 * Efface l'écran
	 */
	public void ve() {
		g.getChildren().clear();
	}
	
	/**
	 * Changer position absolue
	 * @param x
	 * @param y
	 */
	public void fpos(double x, double y) {
		posx = x;
		posy = y;
	}
	
	/**
	 * Changer couleur
	 * @param c
	 */
	public void fcc(int c) {
		switch (c) {
		case 1:
			color = Color.GREEN;
			break;
		case 2:
			color = Color.VIOLET;
			break;
		case 3:
			color = Color.RED;
			break;
		default:
			color = Color.BLACK;
			break;
		}
	}
	
	private void setTeta() {
		teta = Math.toRadians(angle);
	}
}
