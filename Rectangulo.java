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
import java.awt.geom.Rectangle2D;

public class Rectangulo extends FiguraConRelleno{
    private int ancho;

    private int alto;           
    public Rectangulo(Point2D punto, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamaño){
        super(punto, colorBorde, colorRelleno, tamaño);
        setAncho(ancho);
        setAlto(alto);
    }

    
    public Rectangulo(int x, int y, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamaño){
        super(x, y, colorBorde, colorRelleno, tamaño);
        setAncho(ancho);
        setAlto(alto);
    }

    public int getAlto() {
        return alto;
    }
    public void setAlto(int alto) {
        this.alto = alto;
    }
    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }


    @Override
    public void dibujar(Graphics g){
        // Objetos
        Graphics2D g2;
        Rectangle2D r2;
        Stroke bordeFigura;

        // Si el color del relleno es null significa que no tiene relleno
        if(getColorRelleno() != null){
            g.setColor(getColorRelleno());
            g.fillRect((int)getInicio().getX(), (int)getInicio().getY(),
                    getAncho(), getAlto());
        }
        r2 = new Rectangle2D.Float((int)getInicio().getX(), (int)getInicio().getY(),
                    getAncho(), getAlto());
        g2 = (Graphics2D)g;
        bordeFigura = new BasicStroke(getTamaño(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2.setColor(getColorBorde());
        g2.setStroke(bordeFigura);
        g2.draw(r2);
    }

    /**
     * @(#)Rectangulo.java
     * @author Chuidiang
     * @co-author Modyfy fires
     * Sitio: http://www.chuidiang.com/
     * Licencia original de Chuidiang: Esta obra está bajo una licencia de Creative Commons.
     * Mi licencia (fires): BSD
     * Fecha: 21/07/2010 04:52
    */
    /**
     * Debe devolver true si <b>x</b> e <b>y</b> están dentro del rectangulo;
     * false, en caso contrario.
     *
     * @param x La coordenada x del rectangulo
     * @param y La coordenada y del rectangulo
     *
     * @return True si esta dentro.
     * @since 1.6
     */
    @Override
    public boolean estaDentro(int x, int y){
        if((x > getInicio().getX()) && (x < (getInicio().getX() + getAncho())) &&
                (y > getInicio().getY()) && (y < (getInicio().getY() + getAlto()))){
            return true;
        }
        return false;
    }

    /**
     * @(#)Rectangulo.java
     * @author Chuidiang
     * @co-author Modyfy fires
     * Sitio: http://www.chuidiang.com/
     * Licencia original de Chuidiang: Esta obra está bajo una licencia de Creative Commons.
     * Mi licencia (fires): BSD
     * Fecha: 21/07/2010 04:52
    */
    /**
     * Establece la posición en la que se debe dibujar el rectangulo.
     *
     * @param x La coordenada x del rectangulo
     * @param y La coordenada y del rectangulo
     * @since 1.6
     */
    @Override
    public void setPosicion(int x, int y){
        setInicio(x, y);
    }
}