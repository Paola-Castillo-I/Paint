/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAINT2;

import PAINT2.Constantes;
import PAINT2.FiltroArchivo;
import PAINT2.Texto;
import PAINT2.Circulo;
import PAINT2.Figura;
import PAINT2.Linea;
import PAINT2.Ovalo;
import PAINT2.Rectangulo;
import PAINT2.RectanguloConCurvasRedondas;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class PanelDibujo extends javax.swing.JPanel implements Serializable, Printable{
    private final static int CIRCULO = 1, RECTANGULO = 2, OVALO = 3,
            RECTANGULO_CON_CURVAS_REDONDAS = 4, PINCEL = 5, LINEA = 6, LAPIZ = 7,
            TEXTO = 8, BORRADOR = 9, ARRASTRAR = 10, NULO = 0;
    private int coordenadasInicioX, coordenadasFinX;
    private int coordenadasInicioY, coordenadasFinY;
    private int modoDibujar;
    private int lineaX1, lineaX2, lineaY1, lineaY2;
    private Color colorFondoPantallaDibujo;
    private Color colorBorde;
    private Color colorRelleno;
    private float tamañoBorde;
    private File nombreArchivo;
    private int longitudBorrador;
    private VentanaTexto ventanaTexto;                      
    private boolean habilitarDibujarTexto;
    private Texto texto;
    private boolean conRelleno = false;
    private BufferedImage imagen;
    private double escala = 1.0;
    private Cursor cursorActual;
    private LinkedList<Figura> listaFiguras = new LinkedList<Figura>();
    private LinkedList<Texto> listaTexto = new LinkedList<Texto>();
    private Figura figuraArrastrandose;
    private Figura eliminarObjetoIndivial;
    private int xAnteriorRaton;
    private int yAnteriorRaton;
    private Stack desHacerPila;
    private Point2D ubicacionDeImagen;
    boolean archivoGuardadoUltimaVersion;

    public PanelDibujo() {
        initComponents();
        setModoDibujar(NULO);
        setTamañoBorde(1.0f);
        longitudBorrador = 5;
        habilitarDibujarTexto = false;
        archivoGuardadoUltimaVersion = false;
        ubicacionDeImagen = null;
        nombreArchivo = null;
        ventanaTexto = null;
        texto = null;
        figuraArrastrandose = null;
        eliminarObjetoIndivial = null;
        desHacerPila = new Stack();
        colorFondoPantallaDibujo    = Color.WHITE;          
        colorBorde                  = Color.BLACK;          
        colorRelleno                = null;                    
        setBackground(getColorFondoPantallaDibujo());
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 810, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 471, Short.MAX_VALUE)
        );
    }

    private void formMousePressed(java.awt.event.MouseEvent evt) {
        setCoordenadasInicioX(evt.getX());
        setLineaX1(evt.getX());
        setLineaX2(evt.getX());
        setCoordenadasInicioY(evt.getY());
        setLineaY1(evt.getY());
        setLineaY2(evt.getY());
        GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
    }

    private void formMouseReleased(java.awt.event.MouseEvent evt) {
        if(modoDibujar == getLINEA()){
            Linea linea = new Linea(getCoordenadasInicioX(), getCoordenadasInicioY(),
                    getCoordenadasFinX(), getCoordenadasFinY(), getColorBorde(), 
                    (int)getTamañoBorde());    
            agregarFigura(linea);
            desHacerPila.push(linea);
        }else if (modoDibujar == getCIRCULO()){
            double radio = Math.sqrt(Math.pow(getCoordenadasFinX() - getCoordenadasInicioX(),2) +
                        Math.pow(getCoordenadasFinY() - getCoordenadasInicioY(),2));
            Circulo circulo =  new Circulo(getCoordenadasInicioX(), 
                    getCoordenadasInicioY(), (int)radio, getColorBorde(),
                    getColorRelleno(), (int)getTamañoBorde());
            agregarFigura(circulo);
            desHacerPila.push(circulo);
        }else if(modoDibujar == getRECTANGULO()){
            Rectangulo rectangulo = new Rectangulo(Math.min(getCoordenadasInicioX(), getCoordenadasFinX()),
                        Math.min(getCoordenadasInicioY(), getCoordenadasFinY()),
                        Math.abs(getCoordenadasInicioX() - getCoordenadasFinX()),
                        Math.abs(getCoordenadasInicioY() - getCoordenadasFinY()),
                        getColorBorde(), getColorRelleno(), (int)getTamañoBorde());
            agregarFigura(rectangulo);
            desHacerPila.push(rectangulo);
        }else if(modoDibujar == getOVALO()){
            Ovalo ovalo = new Ovalo(Math.min(getCoordenadasInicioX(), getCoordenadasFinX()),
                        Math.min(getCoordenadasInicioY(), getCoordenadasFinY()),
                        Math.abs(getCoordenadasInicioX() - getCoordenadasFinX()),
                        Math.abs(getCoordenadasInicioY() - getCoordenadasFinY()),
                        getColorBorde(), getColorRelleno(), (int)getTamañoBorde());
            agregarFigura(ovalo);
            desHacerPila.push(ovalo);
        }else if(modoDibujar == getRECTANGULO_CON_CURVAS_REDONDAS()){
            RectanguloConCurvasRedondas rectanguloConCurvasRedondas = new RectanguloConCurvasRedondas(
                    Math.min(getCoordenadasInicioX(), getCoordenadasFinX()),
                    Math.min(getCoordenadasInicioY(), getCoordenadasFinY()),
                    Math.abs(getCoordenadasInicioX() - getCoordenadasFinX()),
                    Math.abs(getCoordenadasInicioY() - getCoordenadasFinY()),
                    getColorBorde(), getColorRelleno(), (int)getTamañoBorde());
            agregarFigura(rectanguloConCurvasRedondas);
            desHacerPila.push(rectanguloConCurvasRedondas);
        }
        GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
    }

    private void formMouseDragged(java.awt.event.MouseEvent evt) {
        archivoGuardadoUltimaVersion = false;
        setCoordenadasFinX(evt.getX());
        setCoordenadasFinY(evt.getY());
        
         if (modoDibujar == getLAPIZ()) {
            setLineaX1(getLineaX2());
            setLineaY1(getLineaY2());
            setLineaX2(getCoordenadasFinX());
            setLineaY2(getCoordenadasFinY());

            setTamañoBorde((int)1);
            Linea linea = new Linea(getLineaX1(), getLineaY1(), getLineaX2(),
                    getLineaY2(), getColorBorde(), (int)getTamañoBorde());
            agregarFigura(linea);
            desHacerPila.push(linea);
        }else if (modoDibujar == getPINCEL()) {
            setLineaX1(getLineaX2());
            setLineaY1(getLineaY2());
            setLineaX2(getCoordenadasFinX());
            setLineaY2(getCoordenadasFinY());

            Linea linea = new Linea(getLineaX1(), getLineaY1(), getLineaX2(),
                    getLineaY2(), getColorBorde(), (int)getTamañoBorde());
            agregarFigura(linea);
            desHacerPila.push(linea);
        }
        if(modoDibujar == getARRASTRAR()){
            if (figuraArrastrandose == null){
                xAnteriorRaton = evt.getX();
                yAnteriorRaton = evt.getY();

                figuraArrastrandose = dameFigura(evt);
            }else{
                figuraArrastrandose.setPosicion((int)figuraArrastrandose.getInicio().getX()
                        + (evt.getX() - xAnteriorRaton), (int)figuraArrastrandose.getInicio().getY()
                        + (evt.getY() - yAnteriorRaton));

                xAnteriorRaton = evt.getX();
                yAnteriorRaton = evt.getY();

                repaint();
            }
        }
        GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
        repaint();
    }

    private void formMouseMoved(java.awt.event.MouseEvent evt) {
        figuraArrastrandose = null;
        GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
    }

    private void formMouseExited(java.awt.event.MouseEvent evt) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        GUI_Principal.jLabelCoordenadasPuntero.setText("");
    }

    private void formMouseEntered(java.awt.event.MouseEvent evt) {
        setCursor(getCursorActual());
    }

    private void formMouseClicked(java.awt.event.MouseEvent evt) {
        if(modoDibujar == getTEXTO()){
            mostrarVentanaTexto();
            if(ventanaTexto.isDibujaTexto()){
                texto = ventanaTexto.getTexto();
                setHabilitarDibujarTexto(true);
                desHacerPila.push(texto);
                agregarTexto(texto);
            }else{
                setHabilitarDibujarTexto(false);
            }
        }
        if(modoDibujar == getBORRADOR()){
            eliminarObjetoIndivial = dameFigura(evt);
            eliminarFigura(eliminarObjetoIndivial);
        }
        repaint();
    }

public int getCoordenadasFinX() {
        return coordenadasFinX;
    }

    public void setCoordenadasFinX(int coordenadasFinX) {
        if(coordenadasFinX >= Constantes.MINIMO_LARGO_PANTALLA_DIBUJO
                && coordenadasFinX <= Constantes.MAXIMO_LARGO_PANTALLA_DIBUJO){
           this.coordenadasFinX = coordenadasFinX;
        }
    }
    public int getCoordenadasFinY() {
        return coordenadasFinY;
    }
    public void setCoordenadasFinY(int coordenadasFinY) {
        if(coordenadasFinY >= Constantes.MINIMO_ANCHO_PANTALLA_DIBUJO
                && coordenadasFinY <= Constantes.MAXIMO_ANCHO_PANTALLA_DIBUJO){
            this.coordenadasFinY = coordenadasFinY;
        }
    }
    public int getCoordenadasInicioX() {
        return coordenadasInicioX;
    }
    public void setCoordenadasInicioX(int coordenadasInicioX) {
        if(coordenadasInicioX >= Constantes.MINIMO_LARGO_PANTALLA_DIBUJO
                && coordenadasInicioX <= Constantes.MAXIMO_LARGO_PANTALLA_DIBUJO){
            this.coordenadasInicioX = coordenadasInicioX;
        }
    }
    public int getCoordenadasInicioY() {
        return coordenadasInicioY;
    }
    public void setCoordenadasInicioY(int coordenadasInicioY) {
        if(coordenadasInicioY >= Constantes.MINIMO_ANCHO_PANTALLA_DIBUJO
                && coordenadasInicioY <= Constantes.MAXIMO_ANCHO_PANTALLA_DIBUJO){
            this.coordenadasInicioY = coordenadasInicioY;
        }
    }
    public Color getColorBorde() {
        return colorBorde;
    }

    public void setColorBorde(Color colorBorde) {
        this.colorBorde = colorBorde;
    }
    public Color getColorFondoPantallaDibujo() {
        return colorFondoPantallaDibujo;
    }
    public void setColorFondoPantallaDibujo(Color colorFondoPantallaDibujo) {
        this.colorFondoPantallaDibujo = colorFondoPantallaDibujo;
    }
    public Color getColorRelleno() {
        return colorRelleno;
    }
    public void setColorRelleno(Color colorRelleno) {
        this.colorRelleno = colorRelleno;
    }
    public float getTamañoBorde() {
        return tamañoBorde;
    }

    public void setTamañoBorde(float tamañoBorde) {
        if(tamañoBorde >= Constantes.MINIMO_GROSOR_BORDE
                &&  tamañoBorde <= Constantes.MAXIMO_GROSOR_BORDE){
           this.tamañoBorde = tamañoBorde;
        }
    }

    public double getEscala(){
        return escala;
    }
    public void setEscala(double escala){
        if(imagen != null){
            double anteriorEscala = this.escala;

            this.escala = escala;
            this.firePropertyChange("escala", anteriorEscala, escala);
            repaint();
        }
    }
    public Cursor getCursorActual() {
        return cursorActual;
    }
    public void setCursorActual(Cursor cursorActual) {
        this.cursorActual = cursorActual;
    }
    public Point2D getUbicacionDeImagen(){
        return ubicacionDeImagen;
    }
    public void setUbicacionDeImagen(Point2D imageLocation){
        this.ubicacionDeImagen = imageLocation;
        repaint();              // Vuelve a dibujar la imagen en la ubicacion especificada
    }
    public int getLineaX1() {
        return lineaX1;
    }
    public void setLineaX1(int lineaX1) {
        this.lineaX1 = lineaX1;
    }
    public int getLineaX2() {
        return lineaX2;
    }
    public void setLineaX2(int lineaX2) {
        this.lineaX2 = lineaX2;
    }
    public int getLineaY1() {
        return lineaY1;
    }
    public void setLineaY1(int lineaY1) {
        this.lineaY1 = lineaY1;
    }
    public int getLineaY2() {
        return lineaY2;
    }
    public void setLineaY2(int lineaY2) {
        this.lineaY2 = lineaY2;
    }
    public boolean isConRelleno() {
        return conRelleno;
    }
    public void setConRelleno(boolean conRelleno) {
        this.conRelleno = conRelleno;
    }
    public int getModoDibujar() {
        return modoDibujar;
    }
    public void setModoDibujar(int modoDibujar) {
        this.modoDibujar = modoDibujar;
    }
    public BufferedImage getImagen(){
        return imagen;
    }
    public void setImagen(Image imagen){
        this.imagen = (BufferedImage) imagen;                   
        setUbicacionDeImagen(null);                            
        repaint();                                             
    }
public void setImagen(File file) throws IOException{
        setImagen(ImageIO.read(file));
        repaint();                                              // Se dibuja la nueva imagen
    }
    public static int getARRASTRAR() {
        return ARRASTRAR;
    }
    public static int getCIRCULO() {
        return CIRCULO;
    }
    public static int getLAPIZ() {
        return LAPIZ;
    }
    public static int getLINEA() {
        return LINEA;
    }
    public static int getOVALO() {
        return OVALO;
    }
    public static int getPINCEL() {
        return PINCEL;
    }
    public static int getRECTANGULO() {
        return RECTANGULO;
    }
    public static int getRECTANGULO_CON_CURVAS_REDONDAS() {
        return RECTANGULO_CON_CURVAS_REDONDAS;
    }
    public static int getTEXTO() {
        return TEXTO;
    }
    public static int getBORRADOR() {
        return BORRADOR;
    }
    public int getLongitudBorrador() {
        return longitudBorrador;
    }
    public void setLongitudBorrador(int longitudBorrador) {
        this.longitudBorrador = longitudBorrador;
    }
    public boolean isHabilitarDibujarTexto() {
        return habilitarDibujarTexto;
    }
    public void setHabilitarDibujarTexto(boolean habilitarDibujarTexto) {
        this.habilitarDibujarTexto = habilitarDibujarTexto;
    }
    public void setNombreArchivo(File nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    public boolean isArchivoGuardadoUltimaVersion() {
        return archivoGuardadoUltimaVersion;
    }
    public void setArchivoGuardadoUltimaVersion(boolean archivoGuardadoUltimaVersion) {
        this.archivoGuardadoUltimaVersion = archivoGuardadoUltimaVersion;
    }
    public void agregarFigura(Figura figura){
        listaFiguras.add(figura);
    }
    public void eliminarFigura(Figura figura){
        listaFiguras.remove(figura);
    }

    public void agregarTexto(Texto texto){
        listaTexto.add(texto);
    }
    public void eliminarTexto(Texto texto){
        listaTexto.remove(texto);
    }
    private Figura dameFigura(MouseEvent e){
        for (Figura figura : listaFiguras){
            if (figura.estaDentro(e.getX(), e.getY())){
                return figura;
            }
        }
        return null;
    }

    @Override
    public void paint(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        setBackground(getColorFondoPantallaDibujo());

        if (getImagen() != null){
            Point2D center = new Point2D.Double(getWidth() / 2, getHeight() / 2);
            if (getUbicacionDeImagen() != null){
                center = getUbicacionDeImagen();
            }
            Point2D loc = new Point2D.Double();
            double width = imagen.getWidth() * getEscala();
            double height = imagen.getHeight() * getEscala();
            loc.setLocation(center.getX() - width / 2, center.getY() - height / 2);
            setColorFondoPantallaDibujo(getColorFondoPantallaDibujo());
            g.drawImage(getImagen(), (int) loc.getX(), (int) loc.getY(),(int) width, (int) height, null); 
        }
        dibujarFiguras(g);
        dibujarTexto(g);
  
        this.setBackground(getColorFondoPantallaDibujo());
        g.setColor(getColorBorde());      

        if(modoDibujar != getARRASTRAR()){
            if (modoDibujar == getLINEA()) {
                Line2D line2D = new Line2D.Float(getCoordenadasInicioX(),
                    getCoordenadasInicioY(), getCoordenadasFinX(), getCoordenadasFinY());
                g2 = (Graphics2D)g;
                Stroke bordeFigura = new BasicStroke(getTamañoBorde(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                g2.setColor(getColorBorde());
                g2.setStroke(bordeFigura);
                g2.draw(line2D);
            }

            if (modoDibujar == getOVALO()){
                if(conRelleno){
                    g.setColor(getColorRelleno());
                    g.fillOval(Math.min(getCoordenadasInicioX(), getCoordenadasFinX()),
                            Math.min(getCoordenadasInicioY(), getCoordenadasFinY()),
                            Math.abs(getCoordenadasInicioX() - getCoordenadasFinX()),
                            Math.abs(getCoordenadasInicioY() - getCoordenadasFinY()));
                }
                Ellipse2D e2;
                Stroke bordeFigura;

                e2 = new Ellipse2D.Float(Math.min(getCoordenadasInicioX(), getCoordenadasFinX()),
                        Math.min(getCoordenadasInicioY(), getCoordenadasFinY()),
                        Math.abs(getCoordenadasInicioX() - getCoordenadasFinX()),
                        Math.abs(getCoordenadasInicioY() - getCoordenadasFinY()));
                g2 = (Graphics2D)g;
                bordeFigura = new BasicStroke(getTamañoBorde(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                g2.setColor(getColorBorde());
                g2.setStroke(bordeFigura);
                g2.draw(e2);
            }

            if (modoDibujar == getCIRCULO()){
                double radio = Math.sqrt(Math.pow(getCoordenadasFinX() - getCoordenadasInicioX(),2) +
                        Math.pow(getCoordenadasFinY() - getCoordenadasInicioY(),2));
                if(conRelleno){
                    g.setColor(getColorRelleno());
                    g.fillOval(getCoordenadasInicioX() - (int)radio, getCoordenadasInicioY() - (int)radio,
                        (int)radio * 2 , (int)radio * 2);
                }
                Ellipse2D e2;
                Stroke bordeFigura;

                e2 = new Ellipse2D.Float(getCoordenadasInicioX() - (int)radio, getCoordenadasInicioY() - (int)radio,
                    (int)radio * 2 , (int)radio * 2);
                g2 = (Graphics2D)g;
                bordeFigura = new BasicStroke(getTamañoBorde(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                g2.setColor(getColorBorde());
                g2.setStroke(bordeFigura);
                g2.draw(e2);
            }

            if (modoDibujar == getRECTANGULO_CON_CURVAS_REDONDAS()){
                if(conRelleno){
                    g.setColor(getColorRelleno());
                    g.fillRoundRect(Math.min(getCoordenadasInicioX(), getCoordenadasFinX()),
                            Math.min(getCoordenadasInicioY(), getCoordenadasFinY()),
                            Math.abs(getCoordenadasInicioX() - getCoordenadasFinX()),
                            Math.abs(getCoordenadasInicioY() - getCoordenadasFinY()), 25, 25);
                }
                RoundRectangle2D rr2;
                Stroke bordeFigura;
                rr2 = new RoundRectangle2D.Float(Math.min(getCoordenadasInicioX(), getCoordenadasFinX()),
                        Math.min(getCoordenadasInicioY(), getCoordenadasFinY()),
                        Math.abs(getCoordenadasInicioX() - getCoordenadasFinX()),
                        Math.abs(getCoordenadasInicioY() - getCoordenadasFinY()), 25, 25);
                g2 = (Graphics2D)g;
                bordeFigura = new BasicStroke(getTamañoBorde(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                g2.setColor(getColorBorde());
                g2.setStroke(bordeFigura);
                g2.draw(rr2);
            }


            if (modoDibujar == getRECTANGULO()){
                if(conRelleno){
                    g.setColor(getColorRelleno());
                    g.fillRect(Math.min(getCoordenadasInicioX(), getCoordenadasFinX()),
                            Math.min(getCoordenadasInicioY(), getCoordenadasFinY()),
                            Math.abs(getCoordenadasInicioX() - getCoordenadasFinX()),
                            Math.abs(getCoordenadasInicioY() - getCoordenadasFinY()));
                }
                Rectangle2D r2;
                Stroke bordeFigura;
                r2 = new Rectangle2D.Float(Math.min(getCoordenadasInicioX(), getCoordenadasFinX()),
                        Math.min(getCoordenadasInicioY(), getCoordenadasFinY()),
                        Math.abs(getCoordenadasInicioX() - getCoordenadasFinX()),
                        Math.abs(getCoordenadasInicioY() - getCoordenadasFinY()));
                g2 = (Graphics2D)g;
                bordeFigura = new BasicStroke(getTamañoBorde(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                g2.setColor(getColorBorde());
                g2.setStroke(bordeFigura);
                g2.draw(r2);
            }

            if (modoDibujar == getTEXTO() && isHabilitarDibujarTexto()
                    && ventanaTexto.isDibujaTexto()){
                texto.setPosicionInicialX(coordenadasInicioX);
                texto.setPosicionInicialY(coordenadasInicioY);
                g2 = (Graphics2D)g;

                FontRenderContext contextoFuente = g2.getFontRenderContext();
                Font fuente = new Font(texto.getTipo(), texto.getEstilo(), texto.getTamanio());
                TextLayout layout = new TextLayout( texto.getContenidoTexto(), fuente, contextoFuente );
                g2.setColor( texto.getColor());
                layout.draw( g2,getCoordenadasInicioX(), getCoordenadasInicioY());
                setHabilitarDibujarTexto(false);
            }
        }
    }

    public void deshacer(){
        modoDibujar = 0;
        if(desHacerPila.isEmpty()){
            JOptionPane.showMessageDialog(null, "Ya no se puede deshacer",
                    Constantes.TITULO_PROGRAMA,
                    JOptionPane.INFORMATION_MESSAGE);
        }else{
            if(desHacerPila.lastElement() instanceof Figura){
                Figura objeto = (Figura) desHacerPila.pop();
                eliminarFigura(objeto);
            }else{
                Texto objeto = (Texto) desHacerPila.pop();
                eliminarTexto(objeto);
            }
        }
        repaint();
    }
    public void borrarTodo(){
        listaFiguras.clear();
        listaTexto.clear();
        desHacerPila.clear();
        imagen = null;
        setModoDibujar(0);
        repaint();
    }
    public boolean guardarImagen(){
        if(nombreArchivo == null){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileSelectionMode(0);
            fileChooser.setFileFilter(new FiltroArchivo());
            int resultado = fileChooser.showSaveDialog(null);

            if(resultado == JFileChooser.CANCEL_OPTION)
                return false;
            nombreArchivo = fileChooser.getSelectedFile();

            if(nombreArchivo == null || nombreArchivo.getName().equals("")){
                JOptionPane.showMessageDialog(null,"Nombre de archivo inválido",
                        "" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO +
                        Constantes.TITULO_PROGRAMA, JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if(getImagen() == null){
                crearImagen();
            }
	}
        actualizarImagen();
        
        try{
            JOptionPane.showMessageDialog(null, "Archivo Guardado",
                    "" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO
                    + Constantes.TITULO_PROGRAMA,
                    JOptionPane.INFORMATION_MESSAGE);   
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        try {
            File file;
            if(nombreArchivo.toString().endsWith(".png")){
                 file = new File(nombreArchivo.toString());
            }else{
                 file = new File(nombreArchivo.toString() + ".png");
            }
          if(imagen == null){
                crearImagen();
            }
            ImageIO.write(getImagen(), "png", file);
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        listaFiguras.clear();
        listaTexto.clear();
        desHacerPila.clear();

        archivoGuardadoUltimaVersion = true;
        setModoDibujar(NULO);
        
	repaint();
        return true;
    }
    public void abrirImagen(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileFilter(new FiltroArchivo());

        int result = fileChooser.showOpenDialog(null);
        if(result == JFileChooser.CANCEL_OPTION)
            return;

        nombreArchivo = fileChooser.getSelectedFile();

        if(nombreArchivo != null){
            try{

                borrarTodo();
                BufferedImage image = ImageIO.read(nombreArchivo);
                imagen = ImageIO.read(nombreArchivo);
                setImagen(nombreArchivo);

                Graphics g = image.getGraphics();
                setColorFondoPantallaDibujo(Color.WHITE);
                g.drawImage(image, 0, 0, this);
            }catch(Exception exp){
                JOptionPane.showMessageDialog(null,"No se puede abrir el archivo",
                        "" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO +
                        Constantes.TITULO_PROGRAMA, JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            nombreArchivo = null;
	}
        archivoGuardadoUltimaVersion = true;
        repaint();
    }
    public void crearImagen() {
        imagen = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = imagen.createGraphics();
        g2.setColor(getColorFondoPantallaDibujo());
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        dibujarFiguras(g2);
        dibujarTexto(g2);
        setImagen(imagen);
        g2.dispose();
        setImagen(imagen);
    }
    public void acercar(){
        setEscala(getEscala() * 1.09);
    }
    public void alejar(){
        setEscala(getEscala() * 0.9174311926605505);
    }

    public void dibujarFiguras(Graphics g){
        for (Figura figura : listaFiguras){
            figura.dibujar(g);
        }
    }
    public void dibujarTexto(Graphics g){
        for (Texto texto : listaTexto){
                texto.dibujar(g);
        }
    }

    public void actualizarImagen(){
        if(getImagen() != null){
            Graphics g = getImagen().getGraphics();
            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(getColorFondoPantallaDibujo());
            g2.drawImage(getImagen(), 0, 0, null);
            dibujarFiguras(g2);
            dibujarTexto(g2);
            setImagen(getImagen());
            g2.dispose();
        }
    }

    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if( pageIndex >= 1 ) {
            return( Printable.NO_SUCH_PAGE );
        }
        Graphics2D g2 = (Graphics2D)g;
        g2.translate( pageFormat.getImageableX(), pageFormat.getImageableY() );
        paint( g2 );
        return( Printable.PAGE_EXISTS );
    }

    public void mostrarVentanaTexto() {
        if (ventanaTexto == null) {
            ventanaTexto = new VentanaTexto(null, true);
            ventanaTexto.setLocationRelativeTo(this);
        }
        ventanaTexto.setVisible(true);
    }
    
}