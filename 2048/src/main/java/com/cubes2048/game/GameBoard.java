/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubes2048.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author 18706986
 */
public class GameBoard {
    
    public static final int ROWS = 4;
    public static final int COLS = 4;
    
    private final int startingTiles = 2;
    private tile[][] board;
    private boolean perdeu;
    private boolean ganhou;
    private BufferedImage gameBoard;
    private BufferedImage finalBoard;
    private int x;
    private int y;
    
    private static int SPACING = 10;
    public static int BOARD_WIDTH = (COLS +1)* SPACING +COLS*tile.WIDTH;
    public static int BOARD_HEIGHT =(ROWS+1)*SPACING+ROWS*tile.HEIGHT;
    
    private boolean hasStarted;
 
    public GameBoard(int x, int y)
    {
        this.x = x;
        this.y = y;
        board = new tile[ROWS][COLS];
        gameBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        
        createBoardImage(); 
        start();
        
    }
    
    private void createBoardImage(){
        Graphics2D g = (Graphics2D)gameBoard.getGraphics();
        g.setColor(Color.darkGray);
        g.fillRect(0,0,BOARD_WIDTH,BOARD_HEIGHT);
        g.setColor(Color.lightGray);
        
        for(int row=0;row<ROWS;row++)
        {
            
            for(int col = 0; col<COLS;col++)
            {
                int x= SPACING + SPACING * col +tile.WIDTH * col;
                int y= SPACING + SPACING * row +tile.HEIGHT * row;
                g.fillRoundRect(x,y,tile.WIDTH,tile.HEIGHT,tile.ARC_WIDTH,tile.ARC_HEIGHT);
                
                
            }
        }
    }
    private void start()
    {

        for(int i=0;i< startingTiles; i++)
        {
            spawnRandom();
        }
    }    

    
    private void spawnRandom()
    {
     Random random = new Random();
     boolean notValid = true;
        
     while(notValid)
     {
         int location = random.nextInt(ROWS * COLS);
         int row = location/ROWS;
         int col= location % COLS;
         tile current = board[row][col];
         if(current == null)
         {
             int value = random.nextInt(10)< 9 ? 2:4;
             tile tile = new tile(value, getTileX(col),getTileY(row));
             board[row][col] = tile;
             notValid = false;
         }
     }
        
    }
    
        public int getTileX(int col)
        {
        return SPACING + col* tile.WIDTH + col*SPACING;
        }
        
        public int getTileY(int row)
        {
        return SPACING + row* tile.WIDTH + row*SPACING;
        }
        
            

    public void render (Graphics2D g) 
    {
        Graphics2D g2d = (Graphics2D) finalBoard.getGraphics();
        g2d.drawImage(gameBoard,0,0,null);
        
        //draw tiles
        for(int row = 0; row<ROWS;row++)
        {
            for(int col=0;col < COLS;col++)
            {
                tile current = board[row][col];
                if(current == null)continue;
                current.render(g2d);
            }
            
        }
        
        g.drawImage(finalBoard,x,250,null);
        g2d.dispose();
        
    }
    public void update()
    {
        checkKeys();
        
        for(int row = 0; row<ROWS;row++)
        {
            for(int col=0;col<COLS;col++)
            {
                tile current= board[row][col];
                if(current == null)continue;
                current.update();
                resetPosition(current,row,col);
                if(current.getValue() == 2048)
                {
                ganhou = true;
                }
            }
        }
    }
    
    private void resetPosition(tile current, int row, int col)
    {
        if(current == null)return;
        
        int x = getTileX(col);
        int y = getTileY(row);
        
        int distX = current.getX() - x;
        int distY = current.getY() - y;
        
        if(Math.abs(distX)<tile.SLIDE_SPEED)
        {
            current.setX(current.getX()- distX);
        }
        
        if(Math.abs(distY)<tile.SLIDE_SPEED)
        {
            current.setY(current.getY()-distY);
        }
        
        if(distX < 0)
        {
        current.setX(current.getX() + tile.SLIDE_SPEED);
        }
        
        if(distY < 0)
        {
            current.setY(current.getY() + tile.SLIDE_SPEED);
        }
        
        if(distX > 0)
        {
            current.setX(current.getX() - tile.SLIDE_SPEED);
        }
        
        if(distY > 0)
        {
            current.setY(current.getY() - tile.SLIDE_SPEED);
        }
        
        
    }
    
    
    private boolean move(int row,int col,int direcaoHorizontal,int direcaoVertical,Direction dir)
    {
        boolean podeMover = false;
        
        tile current = board[row][col];
        if(current == null)return false;
        boolean move = true;
        int newCol = col;
        int newRow = row;
        while(move)
        {
        newCol += direcaoHorizontal;
        newRow += direcaoVertical;
        if(checkOutBounds(dir,newRow,newCol))break;
        if(board[newRow][newCol] == null)
        {
            board[newRow][newCol] = current;
            board[newRow - direcaoVertical][newCol - direcaoHorizontal] = null;
            board[newRow][newCol].setIrPara(new Point(newRow,newCol));
            podeMover=true;
        }
        else if(board[newRow][newCol].getValue()==current.getValue()&& board[newRow][newCol].PodeCombinar())
        {
            board[newRow][newCol].setPodeCombinar(false);
            board[newRow][newCol].setValue(board[newRow][newCol].getValue()*2);
            podeMover = true;
            board[newRow - direcaoVertical][newCol - direcaoHorizontal] = null;
            board[newRow][newCol].setIrPara(new Point(newRow,newCol));
           // board[newRow][newCol].setCombineAnimation(true);
           //colocar score
        }    
        else
        {
            move =false;
        }

        }
        
        
        
    return podeMover;
    }
    
    private boolean checkOutBounds(Direction dir, int row, int col) 
    {
        if(dir == Direction.LEFT)
        {
            return col < 0;
        }
        else if(dir == Direction.RIGHT)
        {
            return col > COLS -1;
        }
        else if(dir==Direction.UP)
        {
            return row < 0;
        }
        else if(dir == Direction.DOWN)
        {
            return row> ROWS-1;
        }
        return false;
    }
    
    
    private void moveTiles(Direction dir)
    {
        boolean podeMover = false;
        int direcaoHorizontal = 0;
        int direcaoVertical = 0;
        
        if(dir == Direction.LEFT)
        {
            direcaoHorizontal = -1;
            for(int row= 0; row <ROWS;row++)
            {
                for(int col = 0;col<COLS;col++)
                {
                    if(!podeMover)
                    {
                        podeMover= move(row,col,direcaoHorizontal,direcaoVertical,dir);
                
                    }
                    else move(row, col, direcaoHorizontal, direcaoVertical,dir);
                }
            }
        }
        
        
        else if(dir == Direction.RIGHT)
        {
            direcaoHorizontal = 1;
             for(int row= 0; row <ROWS;row++)
            {
                for(int col = COLS - 1;col >= 0;col--)
                {
                     if(!podeMover)
                    {
                        podeMover= move(row,col,direcaoHorizontal,direcaoVertical,dir);
                
                    }
                    else move(row,col,direcaoHorizontal,direcaoVertical,dir);
                }
            
            }
        }
        
        
        else if(dir == Direction.UP)
        {
            direcaoVertical = -1;
            for(int row= 0; row <ROWS;row++)
            {
                for(int col = 0;col<COLS;col++)
                {
                    if(!podeMover)
                    {
                        podeMover= move(row,col,direcaoHorizontal,direcaoVertical,dir);
                
                    }
                    else move(row,col,direcaoHorizontal,direcaoVertical,dir);
                }
        
            }
        }
        
        else if(dir == Direction.DOWN)
        {
            direcaoVertical = 1;
            for(int row= ROWS - 1; row >=0;row--)
            {
                for(int col = 0;col<COLS;col++)
                {
                    if(!podeMover)
                    {
                        podeMover= move(row,col,direcaoHorizontal,direcaoVertical,dir);
                
                    }
                    else move(row,col,direcaoHorizontal,direcaoVertical,dir);
                }
            }
        }
        else
        {
            System.out.println(dir +" nao e uma direcao valida.");
        }
        
        for(int row=0;row<ROWS;row++)
        {
            for(int col=0;col<COLS;col++)
            {
                tile current = board[row][col];
                if(current == null)continue;
                current.setPodeCombinar(true);
                
            }
        }
            if(podeMover)
            {
               spawnRandom();
              //checar se perdeu
              checarPerdeu();
        
            }
            
        }
        
        private void checarPerdeu()
        {
            for (int row = 0 ;row < ROWS;row++)
            {
                for (int col = 0; col<COLS;col++)
                {
                    if(board[row][col] == null)return;
                    if(checarTilesEmVolta(row,col,board[row][col]))
                    {
                        return;
                    }  
                }
            }
            
            perdeu = true;
            //fzr o score
        }
        
    private boolean checarTilesEmVolta(int row, int col, tile current)
    {
        if(row>0)
        {
             tile check = board[row-1][col];
             if(check == null)return true;
            if(current.getValue() == check.getValue())return true;
        }
        if(row<ROWS -1)
        {
            tile check =board[row+1][col];
            if(check == null)return true;
            if(current.getValue() == check.getValue())return true;
        }
        
         if(col>0)
        {
            tile check =board[row][col - 1];
            if(check == null)return true;
            if(current.getValue() == check.getValue())return true;
        }
         
          if(col<COLS -1)
        {
            tile check =board[row][col + 1];
            if(check == null)return true;
            if(current.getValue() == check.getValue())return true;
        }
        return false;
    }
    
    private void checkKeys()
    {
        if(teclado.typed(KeyEvent.VK_LEFT)  || teclado.typed(KeyEvent.VK_A))
        {
        //manda os quadrados para a esquerda
            moveTiles(Direction.LEFT);
            if(!hasStarted)hasStarted = true;
        }
        if(teclado.typed(KeyEvent.VK_RIGHT) || teclado.typed(KeyEvent.VK_D))
        {
        //manda os quadrados para a direita
            moveTiles(Direction.RIGHT);
            if(!hasStarted)hasStarted = true;
        }
        if(teclado.typed(KeyEvent.VK_UP) || teclado.typed(KeyEvent.VK_W))
        {
        //manda os quadrados para cima
            moveTiles(Direction.UP);
            if(!hasStarted)hasStarted = true;
        }
        if(teclado.typed(KeyEvent.VK_DOWN) || teclado.typed(KeyEvent.VK_S))
        {
        //manda os quadrados para baixo
            moveTiles(Direction.DOWN);
            if(!hasStarted)hasStarted = true;
        }
        
    }

}

