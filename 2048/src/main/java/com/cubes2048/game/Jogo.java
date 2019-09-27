package com.cubes2048.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/** 
    @version 1.1
    @see Classe Principal do Jogo
 */

public class Jogo extends JPanel implements KeyListener,Runnable{

    private static final long serialVersionUID = 1L;
    
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;
    public static final Font main= new Font("Bebas Neue Regular",Font.PLAIN,28);
    private Thread jogo;
    private boolean running;
    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private GameBoard board;
   
    //private long TempoInicial;
    //private long elapsed;
    //private boolean set;

    public Jogo(){

        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        addKeyListener(this);
        
        board = new GameBoard(WIDTH / 2 - GameBoard.BOARD_WIDTH/2,HEIGHT - GameBoard.BOARD_HEIGHT - 10);
    }
    
    
    private void update(){

        board.update();
        teclado.update();

    }
    
    private void render(){

        //Desenha um retangulo branco na tela
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0,0,WIDTH,HEIGHT);

        //render board
        board.render(g);
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

        while(running){ //contagem de qnts upgrades tem que fazer

         boolean shouldRender = false;  
            double now =System.nanoTime();
            unprocessed += (now - then)/nsPerUpdate;
            then = now;//resseta o 
 
            while(unprocessed>=1){  //updadte queue

            updates++;
            update();
            unprocessed --;
            shouldRender =true;
            }

            if(shouldRender){   //render
            fps++;
            render();
            shouldRender = false;
            }

            else {
                try {
                    Thread.sleep(1);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if(System.currentTimeMillis()- fpsTimer >1000){ //FPS Timer
            System.out.printf("%d fps %d updates",fps,updates);
            System.out.println();
            fps = 0;
            updates = 0;
            fpsTimer += 1000;
        }
    }
    
    
    public synchronized void start(){

        if(running)return;
        running = true;
        jogo = new Thread(this,"jogo");
        jogo.start();    
    }

    public synchronized void stop(){

        if(!running)return; 
        running = false;
        System.exit(0);
    }
    
    
     @Override
    public void keyTyped(KeyEvent e){
       
    }

    @Override
    public void keyPressed(KeyEvent e){
        teclado.KeyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        teclado.KeyReleased(e);
    }
    
}
