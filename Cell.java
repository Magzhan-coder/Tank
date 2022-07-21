package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Cell {
    private boolean canBroken, bulletCanPass, tankCanPass, canHide;
    private int cellHealth = 1;
    private Image image;

    public void destroyCell(){
        if(canBroken && cellHealth > 0){
            cellHealth--;
        }
    }

    public void drawCell(GraphicsContext gc, int x, int y, int size){
        gc.drawImage(image, x*size, y*size);
    }

    public boolean isCanBroken() {
        return canBroken;
    }

    public void setCanBroken(boolean canBroken) {
        this.canBroken = canBroken;
        if(canBroken){
            cellHealth = 4;
        }
    }

    public boolean isBulletCanPass() {
        return bulletCanPass;
    }

    public void setBulletCanPass(boolean bulletCanPass) {
        this.bulletCanPass = bulletCanPass;
    }

    public boolean isTankCanPass() {
        return tankCanPass;
    }

    public void setTankCanPass(boolean tankCanPass) {
        this.tankCanPass = tankCanPass;
    }

    public boolean isCanHide() {
        return canHide;
    }

    public void setCanHide(boolean canHide) {
        this.canHide = canHide;
    }

    public int getCellHealth() {
        return cellHealth;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}

