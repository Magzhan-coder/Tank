package sample;

import javafx.scene.image.Image;

public class TreeCell extends Cell {
    private Image image = new Image("img/Tree.png");

    public TreeCell(){
        super();
        setImage(image);
        setCanBroken(false);
        setBulletCanPass(true);
        setTankCanPass(true);
        setCanHide(true);
    }
}
