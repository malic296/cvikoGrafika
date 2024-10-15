package controller;

import model.Point;
import model.Polygon;
import rasterizer.*;
import view.Panel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller2D {

    private final Panel panel;
    private boolean isShiftPressed = false;
    private LineRasterizer lineRasterizer;
    private LineRasterizerTrivial lineRasterizerTrivial;

    private Polygon polygon;
    public PolygonRasterizer polygonRasterizer;

    public Controller2D(Panel panel) {
        this.panel = panel;

        initObjects(panel.getRasterImage());
        initListeners(panel);
        // init Listeners
    }

    public void initObjects(Raster raster){
        lineRasterizer = new LineRasterizerGraphics(raster);
        lineRasterizer.setColor(Color.YELLOW);

        lineRasterizerTrivial = new LineRasterizerTrivial(raster);
        lineRasterizerTrivial.setColor(Color.BLUE);

        polygon = new Polygon();
        polygonRasterizer = new PolygonRasterizer(lineRasterizerTrivial);
        // ...
    }

    public void initListeners(Panel panel){
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(isShiftPressed){
                    panel.clear(0x000000);
                    polygon.addPoint(new Point(e.getX(), e.getY()));
                    polygonRasterizer.rasterize(polygon);

                    panel.repaint();
                }
                else{
                    panel.clear(0x000000);
                    polygonRasterizer.rasterize(polygon);

                    panel.repaint();
                }

            }

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_C){
                    panel.clear(0x000000);
                    polygon.deletePolygon();
                    panel.repaint();
                }
                else if(e.getKeyCode() == KeyEvent.VK_SHIFT){
                    isShiftPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                isShiftPressed = false;
            }
        });


    }
}
