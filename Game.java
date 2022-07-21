package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.*;

public class Game extends Application {
    private Scene scene;
    private Stage stage;
    private Map map;
    private Tank tankPlayer;
    private List<Bullet> bulletList = new LinkedList<>();
    private int CELL_SIZE = 64;


    private GraphicsContext gc;

    private void initialize() throws Exception{

        Pane root = new Pane();
        scene = new Scene(root);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.W){
                    tankPlayer.moveUp();
                }else if(keyEvent.getCode() == KeyCode.S){
                    tankPlayer.moveDown();
                }else if(keyEvent.getCode() == KeyCode.D){
                    tankPlayer.moveRight();
                }else if(keyEvent.getCode() == KeyCode.A){
                    tankPlayer.moveLeft();
                }else if(keyEvent.getCode() == KeyCode.SPACE){
                    Bullet bullet = new Bullet(tankPlayer.getPosition(), tankPlayer.getDirection(), CELL_SIZE);
                    bullet.setMap(map);
                    bulletList.add(bullet);
                }
            }
        });

        stage.setTitle( "Tanker 2D" );
        stage.setScene(scene);

        Scanner scan = new Scanner(System.in);
        map = new Map(scan);
        map.setCellSize(CELL_SIZE);
        int mapSize = map.getMapSize();
        tankPlayer = new Tank(map.getPlayerPosition(), CELL_SIZE);
        tankPlayer.setMap(map);
        tankPlayer.setCellSize(CELL_SIZE);

        Canvas canvas = new Canvas(mapSize*CELL_SIZE, mapSize*CELL_SIZE);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add( canvas );
    }

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        initialize();
        updateCadre();

        new AnimationTimer() {
            private long last = 0;
            public void handle(long now)
            {
                if(now - last >= 50_000_000){
                    updateCadre();
                    last = now;
                }
            }
        }.start();

        stage.show();
    }

    private void updateCadre(){
        map.drawCells(gc);
        tankPlayer.updateTank(gc);
        updateBullets(gc);
    }


    public void updateBullets(GraphicsContext gc){
        for(Bullet bullet : bulletList){
            bullet.updateBullet(gc);
//            System.out.println("Bullet" + bullet.getPosition().toString());
            if(!bullet.isAlive()){
                bulletList.remove(bullet);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
