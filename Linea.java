/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAINT2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Linea extends Figura{
        private Point2D fin;                                

    public Linea(){
        super();
        setFin(new Point2D.Double(0, 0));               
    }

    public Linea(Point2D puntoInicio, Point2D puntoFin, Color colorBorde, int tamaño){
        super(puntoInicio, colorBorde, tamaño);
        setFin(puntoFin);               
    }

    public Linea(int inicioX, int inicioY, int finX, int finY, Color colorBorde, int tamaño){
        super(inicioX, inicioY, colorBorde, tamaño);
        setFin(new Point2D.Double(finX, finY));        // Punto final
    }

    public Point2D getFin() {
        return fin;
    }
    public void setFin(Point2D fin) {
        this.fin = fin;
    }
    public void setFin(int finX, int finY) {
        Point2D finCoordenada = new Point2D.Double(finX, finY);
        this.fin = finCoordenada;
    }

    @Override
    public void dibujar(Graphics g){
        Graphics2D g2;
        Line2D line2D;
        Stroke bordeFigura;

        line2D = new Line2D.Float((int)getInicio().getX(), (int)getInicio().getY(),
                (int)getFin().getX(), (int)getFin().getY());
        g2 = (Graphics2D)g;
        bordeFigura = new BasicStroke(getTamaño(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2.setColor(getColorBorde());
        g2.setStroke(bordeFigura);
        g2.draw(line2D);
    }

    @Override
    public boolean estaDentro(int x, int y) {
        return false; 
    }

    @Override
    public void setPosicion(int x, int y) {
        
    }
}