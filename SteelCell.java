package sample;

import javafx.scene.image.Image;

public class SteelCell extends Cell {
    private Image image = new Image("img/SteelWall.png");
    public SteelCell(){
        super();
        setImage(image);
        setCanBroken(false);
        setBulletCanPass(false);
        setTankCanPass(false);
        setCanHide(false);
    }
}
