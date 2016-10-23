package com.example.coelh.myapplication;

/**
 * Created by coelh on 19/10/2016.
 */

public class Snake {

    private int direction;
    private int dimX = 20;
    private int dimY = 20;
    private SnakeNode head;
    private SnakeNode tail;
    private SnakeNode food;

    public Snake(){

        head = new SnakeNode(1000,1000);
        SnakeNode prev = head;
        SnakeNode aux;

        for(int j = 1001; j < 1004; j++){
            aux = new SnakeNode(10, j);
            aux.setPrevious(prev);
            prev = aux;
        }
        tail = prev;

        food = new SnakeNode(1500,1500);

        direction = 0;
        //0 de baixo pra cima
        //1 da esquerda pra direita
        //2 de cima a baixo
        //3 da direita pra esquerda

    }
    //x e y sao posições do tabuleiro
    public void changeDirection(int x, int y){
        if(direction == 0 || direction== 2){
            if(y<head.y % dimY)
                direction=3;
            else
                direction=1;
        }
        else{
            if(x<head.x % dimX)
                direction=2;
            else
                direction=0;
        }
    }
    public boolean move(){
        int topX = head.x;
        int topY = head.y;

        switch (direction){
            case 0:
                topX = topX+1;
                break;
            case 1:
                topY = topY+1;
                break;
            case 2:
                topX = topX-1;
                break;
            case 3:
                topY = topY-1;
        }

        if((head.x%dimX == food.x%dimX)&&(head.x%dimX == food.x%dimX)){
            SnakeNode newHead = new SnakeNode(topX, topY);
            head.setPrevious(newHead);
            head = newHead;
            randomFood();
        }
        else{
            SnakeNode aux = tail;
            tail = tail.getPrevious();
            aux.setPrevious(null);
            head.setPrevious(aux);
            aux.changePosition(topX,topY);
            head = aux;
        }
        //TODO: vrificar se a snake se toca
        return true;
    }
    public void randomFood(){
        int x = (int)(Math.random() *dimX);
        int y = (int)(Math.random() *dimY);

        food.changePosition(x,y);
    }
    class SnakeNode{
        public int x;
        public int y;
        private SnakeNode previousNode;

        public SnakeNode(int x, int y){
            this.x = x;
            this.y = y;
        }

        public void changePosition(int x, int y){
            this.x = x;
            this.y = y;
        }
        public void setPrevious(SnakeNode snakeNode){
            this.previousNode = snakeNode;
        }
        public SnakeNode getPrevious(){
            return previousNode;
        }

    }
    public void setDimesion(int x, int y){
        dimX = x;
        dimY = y;
    }
    public SnakeNode getTail(){
        return tail;
    }
    public SnakeNode getFood(){
        return food;
    }
}
