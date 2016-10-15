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
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

public class RectanguloConCurvasRedondas extends Rectangulo{
    
    private int curvaEsquinaRedondeada;

    public RectanguloConCurvasRedondas(Point2D punto, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamaño){
        super(punto, ancho, alto, colorBorde, colorRelleno, tamaño);
        setCurvaEsquinaRedondeada(25);
    }

   
    public RectanguloConCurvasRedondas(int x, int y, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamaño){
        super(x, y, ancho, alto, colorBorde, colorRelleno, tamaño);
        setCurvaEsquinaRedondeada(25);
    }

    public int getCurvaEsquinaRedondeada() {
        return curvaEsquinaRedondeada;
    }
    public void setCurvaEsquinaRedondeada(int curvaEsquinaRedondeada) {
        this.curvaEsquinaRedondeada = curvaEsquinaRedondeada;
    }

    @Override
    public void dibujar(Graphics g){
        // Objetos
        Graphics2D g2;
        RoundRectangle2D rr2;
        Stroke bordeFigura;

        // Si el color del relleno es null significa que no tiene relleno
        if(getColorRelleno() != null){
            g.setColor(getColorRelleno());
            g.fillRoundRect((int)getInicio().getX(), (int)getInicio().getY(),
                    getAncho(), getAlto(), getCurvaEsquinaRedondeada(),
                    getCurvaEsquinaRedondeada());
        }
        rr2 = new RoundRectangle2D.Float((int)getInicio().getX(), (int)getInicio().getY(),
                    getAncho(), getAlto(), getCurvaEsquinaRedondeada(),
                    getCurvaEsquinaRedondeada());
        g2 = (Graphics2D)g;
        bordeFigura = new BasicStroke(getTamaño(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2.setColor(getColorBorde());
        g2.setStroke(bordeFigura);
        g2.draw(rr2);
    }
}