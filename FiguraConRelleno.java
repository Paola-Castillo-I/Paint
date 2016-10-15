/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAINT2;

import java.awt.Color;
import java.awt.geom.Point2D;

public abstract class FiguraConRelleno extends Figura{
        private Color colorRelleno;         

        public FiguraConRelleno(){
        super();
        setColorRelleno(Color.WHITE);           
    }

    public FiguraConRelleno(Point2D punto, Color colorBorde, Color colorRelleno,
             int tamaño){
        super(punto, colorBorde, tamaño);
        setColorRelleno(colorRelleno);  
    }

    public FiguraConRelleno(int x, int y, Color colorBorde, Color colorRelleno,
             int tamaño){
        super(x, y, colorBorde, tamaño);
        setColorRelleno(colorRelleno);              
    }

    public Color getColorRelleno() {
        return colorRelleno;
    }
        public void setColorRelleno(Color colorRelleno) {
        this.colorRelleno = colorRelleno;
    }
}