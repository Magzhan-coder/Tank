package sample;

import javafx.scene.image.Image;

public class NormalCell extends Cell {
    private Image image = new Image("img/Normal.png");
    public NormalCell(){
        super();
        setImage(image);
        setCanBroken(false);
        setBulletCanPass(true);
        setTankCanPass(true);
        setCanHide(false);
    }

}
