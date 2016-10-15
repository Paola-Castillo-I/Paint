/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAINT2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

public class Texto {
    
    private String contenidoTexto;
    private String tipo;
    private int estilo;
    private int tamaño;
    private int posicionInicialX;
    private int posicionInicialY;
    private Color color;


    public Texto(String contenidoTexto, String tipo, int estilo, int tamaño,
            Color color, int posicionInicialX, int posicionInicialY){
        setContenidoTexto(contenidoTexto);
        setTipo(tipo);
        setEstilo(estilo);
        setTamaño(tamaño);
        setColor(color);
        setPosicionInicialX(posicionInicialX);
        setPosicionInicialY(posicionInicialX);
    }

    public Texto(String contenidoTexto, String tipo, int estilo, int tamaño,
            Color color){
        setContenidoTexto(contenidoTexto);
        setTipo(tipo);
        setEstilo(estilo);
        setTamaño(tamaño);
        setColor(color);
        setPosicionInicialX(0);
        setPosicionInicialY(0);
    }

    public String getContenidoTexto() {
        return contenidoTexto;
    }

    public void setContenidoTexto(String contenidoTexto) {
        this.contenidoTexto = contenidoTexto;
    }
    public int getEstilo() {
        return estilo;
    }

    public void setEstilo(int estilo) {
        this.estilo = estilo;
    }

    public int getTamanio() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public int getPosicionInicialX() {
        return posicionInicialX;
    }
    public void setPosicionInicialX(int posicionInicialX) {
        this.posicionInicialX = posicionInicialX;
    }
    public int getPosicionInicialY() {
        return posicionInicialY;
    }
    public void setPosicionInicialY(int posicionInicialY) {
        this.posicionInicialY = posicionInicialY;
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public void dibujar(Graphics g){
        Graphics2D g2;
        FontRenderContext contextoFuente;
        Font fuente;
        TextLayout layout;

        g2 = (Graphics2D)g;
        contextoFuente = g2.getFontRenderContext();
        fuente = new Font(getTipo(), getEstilo(), getTamanio());
        layout = new TextLayout(getContenidoTexto(), fuente, contextoFuente );
        g2.setColor(getColor());
        layout.draw( g2,getPosicionInicialX(), getPosicionInicialY());
    }
}