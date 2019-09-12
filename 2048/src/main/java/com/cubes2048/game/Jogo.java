/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubes2048.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author 18706986
 */
public class Jogo extends JPanel implements KeyListener,Runnable{

    private static final long serialVersionUID = 1L;
    
    public static final int WIDTH = 400;
    public static final int HEIGHT = 630;
    public static final Font main= new Font("Bebas Neue Regular",Font.PLAIN,28);
    private Thread jogo;
    private boolean running;
    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
   
    private long TempoInicial;
    private long elapsed;
    private boolean set;
    public Jogo()
    {
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        addKeyListener(this);
    }
    private void update()
    {
        
    }
    
    private void render()
    {
        //Desenha um retangulo branco na tela
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0,0,WIDTH,HEIGHT);
        //render board
        g.dispose();
        
        Graphics2D g2d = (Graphics2D)getGraphics();
        g2d.drawImage(image, 0, 0,null);
        g2d.dispose();
    }
    
   

    @Override
    public void run() {
        
        int fps=0,updates =0;
        long fpsTimer =System.currentTimeMillis();
        double nsPerUpdate =1000000000.0/60;
        //last updade time in nanoseconds
        double then =System.nanoTime();
        double unprocessed = 0;
        while(running)
        {
         boolean shouldRender = false;  
         //contagem de qnts upgrades tem que fazer
            double now =System.nanoTime();
            unprocessed += (now - then)/nsPerUpdate;
            then = now;//resseta o 
        //updadte queue
 
            while(unprocessed>=1)
            {
            updates++;
            update();
            unprocessed --;
            shouldRender =true;
            }
        //render
            if(shouldRender)
            {
            fps++;
            render();
            shouldRender = false;
            }
            else 
            {
                try
                    {
                        Thread.sleep(1);
                    }
                 catch(Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        }
        //FPS Timer
        if(System.currentTimeMillis()- fpsTimer >1000){
            System.out.printf("%d fps %d updates",fps,updates);
            System.out.println();
            fps = 0;
            updates = 0;
            fpsTimer += 1000;
        }
    }
    
    
    public synchronized void start()
    {
        if(running)return;
        running = true;
        jogo = new Thread(this,"jogo");
        jogo.start();    
    }
    public synchronized void stop()
    {
        if(!running)return; 
        running = false;
        System.exit(0);
    }
    
    
     @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    
}
