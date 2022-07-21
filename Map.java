package sample;

import javafx.scene.canvas.GraphicsContext;

import java.util.Scanner;

public class Map {
    private Scanner scanner;
    private Cell[][] map;
    private int mapSize;
    private int cellSize;
    private Position playerPosition;//initial Player position

    Map(Scanner scan) throws Exception{
        this.scanner = scan;
        initialize();
    }

    private void initialize() throws Exception{
        mapSize = scanner.nextInt();
        if(mapSize == 0){
            throw new InvalidMapException("Map size can not be zero");
        }

        map = new Cell[mapSize][mapSize];

        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                char a = scanner.next().charAt(0);

                if(a == 'P'){
                    playerPosition = new Position(j, i);
                }

                initCell(a, i, j);

            }
        }
    }

    private void initCell(char a, int i, int j) throws Exception{
        if(!isValidInput(a)){
            throw new InvalidMapException("Not enough map elements or invalid input");
        }
        if(a == 'S'){
            map[i][j] = new SteelCell();
        }else if(a == 'W'){
            map[i][j] = new WaterCell();
        }else if(a == 'T'){
            map[i][j] = new TreeCell();
        }else if(a == 'B'){
            map[i][j] = new BrickCell();
        }else{
            map[i][j] = new NormalCell();
        }
    }

    //    0 -> Empty Cell, T -> Tree, B -> Block, S -> Steel, W -> Water, P -> Player
    private boolean isValidInput(Character c) {
        return (c == '0' || c == 'T' || c == 'B' || c == 'S' || c == 'W' || c == 'P');
    }

    public void drawCells(GraphicsContext gc){
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                map[i][j].drawCell(gc, j, i, cellSize);
            }
        }
    }

    public void setCellSize(int cellSize){
        this.cellSize = cellSize;
    }

    public void destroyCell(int x, int y){ //destroy cell at (x,y) if cell can destroyed
        if(isValidCoordinate(x, y)) {
            map[y][x].destroyCell();
            if(getCellAt(x, y).getCellHealth() == 0){ // if cell destroyed, then replace it to Normal Cell
                map[y][x] = new NormalCell();
            }
        }
    }

    // check if it is valid coordinate
    public boolean isValidCoordinate(int x, int y){
        boolean isValidY = (y >= 0 && y < mapSize);
        boolean isValidX = (x >= 0 && x < mapSize);
        return isValidX && isValidY;
    }

    // return Player's initial Position
    public Position getPlayerPosition(){ return playerPosition; }

    public int getMapSize(){
        return mapSize;
    }

    public Cell getCellAt(int x, int y){
        return map[y][x];
    }
}
