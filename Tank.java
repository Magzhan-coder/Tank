package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tank extends MyPlayer {
    private Position position;
    private int cellSize;
    private int tankLife = 5; //for part 3

    public Tank(Position position, int cellSize) {
        super(position, cellSize);
        this.position = position;
        this.cellSize = cellSize;
    }

    public void updateTank(GraphicsContext gc){
        if(!super.isHidden){ //does not draw
            drawTankBody(gc);
            drawTankGun(gc);
        }
    }

    private void drawTankBody(GraphicsContext gc){
        int x = position.getX() * cellSize + cellSize / 8;
        int y = position.getY() * cellSize + cellSize / 8;
        gc.setFill(Color.BLUE);
        gc.fillRect(x , y, 3 * cellSize / 4 ,3 * cellSize / 4);
    }

    private void drawTankGun(GraphicsContext gc){
        gc.setFill(Color.RED);
        int gunWidth = cellSize / 4, gunHeight = 5 * cellSize / 8;
        int x = position.getX() * cellSize, y = position.getY() * cellSize;
        int gunX = x + cellSize / 2, gunY = y + cellSize / 2;
        int temp = -1;
        switch (super.direction) {
            case 'l':
                gunY -= cellSize / 8;
                gunX = x;
                temp = gunHeight;
                gunHeight = gunWidth;
                gunWidth = temp;
                break;
            case 'd':
                gunX -= cellSize / 8;
                gunY -= cellSize / 8;
                break;
            case 'r':
                gunY -= cellSize / 8;
                gunX -= cellSize / 8;
                temp = gunWidth;
                gunWidth = gunHeight;
                gunHeight = temp;
                break;
            case 'u':
                gunX -= cellSize / 8;
                gunY = y;
                break;

        }
        gc.fillRect(gunX, gunY, gunWidth, gunHeight);
    }


}
