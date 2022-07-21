package sample;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyPlayer implements Player{
    private Position position;
    private Map map;
    protected boolean isHidden = false;
    protected int cellSize;
    protected char direction = 'l'; //default direction is left


    public MyPlayer(Position position, int cellSize){
        this.position = position;
        this.cellSize = cellSize;
    }

    @Override
    public void setMap(Map map) {
        this.map = map;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void moveLeft() {
        int x = position.getX();
        int y = position.getY();
        direction = 'l';
        if(canMove(x-1, y)){
            position.setX(x-1);
        }
    }

    @Override
    public void moveUp() {
        int y = position.getY();
        int x = position.getX();
        direction = 'u';
        if(canMove(x, y-1)){
            position.setY(y-1);
        }
    }

    @Override
    public void moveRight() {
        int x = position.getX();
        int y = position.getY();
        direction = 'r';
        if(canMove(x+1, y)){
            position.setX(x+1);
        }
    }

    @Override
    public void moveDown() {
        int y = position.getY();
        int x = position.getX();
        direction = 'd';
        if(canMove(x, y+1)){
            position.setY(y+1);
        }
    }




    private boolean canMove(int x, int y) {
        if(!map.isValidCoordinate(x, y)){
            return false;
        }
        if(map.getCellAt(x, y).isTankCanPass()){
            isHidden = map.getCellAt(x, y).isCanHide();
            return true;
        }
        return false;
    }

    public void setCellSize(int cellSize){
        this.cellSize = cellSize;
    }

    public char getDirection(){
        return this.direction;
    }
}

