package sample;

import javafx.scene.image.Image;

public class WaterCell extends Cell {
    private Image image = new Image("img/Water.png");
    public WaterCell(){
        super();
        setImage(image);
        setCanBroken(false);
        setBulletCanPass(true);
        setTankCanPass(false);
        setCanHide(false);
    }
}
