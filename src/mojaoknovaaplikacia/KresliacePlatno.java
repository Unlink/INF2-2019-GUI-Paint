/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mojaoknovaaplikacia;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 *
 * @author duracik2
 */
public class KresliacePlatno extends JComponent implements ActionListener, MouseMotionListener, MouseListener {

    private String text = "Ahoj";
    private ArrayList<ArrayList<Point>> ciary;
    private BufferedImage obrazok = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
    

    public KresliacePlatno() {
        new Timer(1000, this).start();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        ciary = new ArrayList<>();
        ciary.add(new ArrayList<>());
        
        
        Graphics graphics = obrazok.getGraphics();
        for (int i = 0; i < 10; i++) {
            graphics.drawLine(10*i+10, 0, 10*i+10, obrazok.getHeight());
        }
        for (int i = 0; i < 10; i++) {
            graphics.drawLine(0, 10*i+10, obrazok.getWidth(), 10*i+10);
        }
    }
    
    

    public void setText(String text) {
        this.text = text;
        this.repaint();
    }
    
    
    
    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("Teraz prekreslujem");
        
        BufferedImage obrazok;
        try {
            obrazok = ImageIO.read(getClass().getResource("/obrazky/palma.jpg"));
            g.drawImage(obrazok, 20, 20, 500, 500, null);
        } catch (IOException ex) {
            System.out.println("Nepodarilo sa");
        }
        
        g.drawImage(this.obrazok, 0, 0, null);
        
        Graphics2D g2 = (Graphics2D) g;
        
        g.drawLine(10, 10, 45, 80);
        g.drawString(text, 50, 50);
        g.fillPolygon(new int[]{100, 200, 350}, new int[]{88, 400, 1}, 3);
        
        g.setColor(Color.red);
        g2.setStroke(new BasicStroke(2));
        g.drawPolygon(new int[]{100, 200, 350}, new int[]{88, 400, 1}, 3);
        
        for (ArrayList<Point> ciara: ciary) {
            int[] xPoints = new int[ciara.size()];
            int[] yPoints = new int[ciara.size()];
            int i = 0;
            for (Point point : ciara) {
                xPoints[i] = ciara.get(i).x;
                yPoints[i] = ciara.get(i).y;
                i++;
            }
            g2.drawPolyline(xPoints, yPoints, ciara.size());
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //this.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ciary.get(ciary.size()-1).add(new Point(e.getX(), e.getY()));
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println(e.getX() + " " + e.getY());
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.ciary.add(new ArrayList<>());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
