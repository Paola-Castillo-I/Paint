/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAINT2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

public abstract class Figura {
    private Point2D inicio;
    private Color colorBorde;
    private int tamaño;               

    public Figura(){
        setInicio(new Point2D.Double(0, 0));    
        setColorBorde(Color.BLACK);             
        setTamaño(1);                         
    }

    public Figura(Point2D punto, Color colorBorde, int tamaño){
        setInicio(punto);               
        setColorBorde(colorBorde);      
        setTamaño(tamaño);          
    }

    public Figura(int x, int y, Color colorBorde, int tamaño){
        setInicio(new Point2D.Double(x, y));        
        setColorBorde(colorBorde);                  
        setTamaño(tamaño);                     
    }

    public Color getColorBorde() {
        return colorBorde;
    }
    public void setColorBorde(Color colorBorde) {
        this.colorBorde = colorBorde;
    }
    public Point2D getInicio() {
        return inicio;
    }
    public void setInicio(Point2D inicio) {
        this.inicio = inicio;
    }
    public void setInicio(int x, int y) {
        Point2D inicioCoordenada = new Point2D.Double(x, y);
        this.inicio = inicioCoordenada;
    }
    public int getTamaño() {
        return tamaño;
    }
    public void setTamaño(int tamanhio) {
        this.tamaño = tamaño;
    }

    public void dibujar(Graphics g){}
    public abstract boolean estaDentro(int x, int y);
    public abstract void setPosicion(int x, int y);
}