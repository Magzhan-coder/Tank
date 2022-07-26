package sample;

public class Position {
    private int x, y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x){ this.x = x; }
    public int getX(){ return x; }

    public void setY(int y){ this.y = y; }
    public int getY(){ return y; }

    public boolean equals(Position p){
        return ( x == p.getX() && p.getY() == y);
    }

    public String toString(){
        return "Position (" + x + ", " + y + ")";
    }

}
