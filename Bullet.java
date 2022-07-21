package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet {
    private Position position;
    private Map map;
    private int cellSize;
    private int speed;
    private char direction;
    private boolean isAlive = true;
    private boolean isHidden = false; // is true, when under tree


    public Bullet(Position position, char direction, int cellSize){
        this.position = new Position(position.getX(), position.getY());
        this.direction = direction;
        this.cellSize = cellSize;
    }

    public void updateBullet(GraphicsContext gc){
        updateBulletPosition();
        drawBullet(gc);
    }

    private void updateBulletPosition(){
        int x = position.getX(), y = position.getY();
        switch (direction){
            case 'l':
                goToCellAt(x-1, y);
                break;
            case 'u':
                goToCellAt(x, y-1);
                break;
            case 'd':
                goToCellAt(x, y+1);
                break;
            case 'r':
                goToCellAt(x+1, y);
                break;
        }
    }

    private void goToCellAt(int x, int y){
        if(!canMoveTo(x, y)){
            map.destroyCell(x, y);
            isAlive = false;
            return;
        }
        position.setX(x);
        position.setY(y);
    }

    private boolean canMoveTo(int x, int y){
        if(!map.isValidCoordinate(x, y)){
            return false;
        }
        Cell targetCell = map.getCellAt(x, y);
        if(targetCell.isBulletCanPass()){
            isHidden = targetCell.isCanHide();
            return true;
        }
        return false;
    }

    private void drawBullet(GraphicsContext gc){
        if(!isHidden){
            gc.setFill(Color.BLACK);
            double radius = cellSize / 4;
            int x = position.getX() * cellSize + 3 * cellSize / 8;
            int y = position.getY() * cellSize + 3 * cellSize / 8;
            gc.fillOval(x , y, radius, radius);
        }
    }

    public void setMap(Map map){
        this.map = map;
    }

    public boolean isAlive(){ return isAlive; }
    public Position getPosition(){
        return position;
    }
}
