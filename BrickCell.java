package sample;

import javafx.scene.image.Image;

public class BrickCell extends Cell {
    private Image image = new Image("img/Brick.png");
    public BrickCell(){
        super();
        setImage(image);
        setCanBroken(true);
        setBulletCanPass(false);
        setTankCanPass(false);
        setCanHide(false);
    }
}
