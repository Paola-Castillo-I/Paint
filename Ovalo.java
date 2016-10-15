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
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Ovalo extends Rectangulo{
    public Ovalo(Point2D punto, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamaño){
        super(punto, ancho, alto, colorBorde, colorRelleno, tamaño);
    }

    public Ovalo(int x, int y, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamaño){
        super(x, y, ancho, alto, colorBorde, colorRelleno, tamaño);
    }

    @Override
    public void dibujar(Graphics g){
        Graphics2D g2;
        Ellipse2D e2;
        Stroke bordeFigura;

        if(getColorRelleno() != null){
            g.setColor(getColorRelleno());
            g.fillOval((int)getInicio().getX(), (int)getInicio().getY(),
                    getAncho(), getAlto());
        }
        e2 = new Ellipse2D.Float((int)getInicio().getX(), (int)getInicio().getY(),
                    getAncho(), getAlto());
        g2 = (Graphics2D)g;
        bordeFigura = new BasicStroke(getTamaño(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2.setColor(getColorBorde());
        g2.setStroke(bordeFigura);
        g2.draw(e2);
    }
}