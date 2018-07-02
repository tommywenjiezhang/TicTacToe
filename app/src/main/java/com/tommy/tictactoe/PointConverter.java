package com.tommy.tictactoe;

public class PointConverter {
    private  Point point;
    private int tag;
    private int x;
    private int y;

    public PointConverter(Point point) {
        this.tag = convertIttoTag(point);
        this.x = point.getX();
        this.y = point.getY();
    }
    public PointConverter(int tagid){
        this.x = rowConverter(tagid);
        this.y = columConverter(tagid);
    }
    public PointConverter(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int convertIttoTag(Point point){
        if(point.x == 0 && point.y == 0){
            return 0;
        }
        else if(point.x == 1 && point.y == 0){
            return 3;
        }
        else if(point.x == 2 && point.y == 0){
            return 6;
        }
        else if(point.x == 0 && point.y == 1){
            return 1;
        }
        else if(point.x == 1 && point.y == 1){
            return 4;
        }
        else if(point.x == 2 && point.y == 1){
            return 7;
        }
        else if(point.x == 0 && point.y == 2){
            return 2;
        }
        else if(point.x == 1 && point.y == 2){
            return 5;
        }
        else if(point.x == 2 && point.y == 2){
            return 8;
        }
        else{
            return -1;
        }
    }

    public int getTag() {
        return tag;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int rowConverter(int value){
        int num =0;
        switch(value){
            case 0: case 1: case 2:
                num = 0;
                break;
            case 3: case 4: case 5:
                num = 1;
                break;
            case 6: case 7: case 8:
                num = 2;
                break;
        }
        return num;
    }

    private int columConverter(int value){
        int num = 0;
        switch(value){
            case 0: case 3: case 6:
                num = 0;
                break;
            case 1: case 4: case 7:
                num = 1;
                break;
            case 2: case 5: case 8:
                num = 2;
                break;
        }
        return num;
    }
}
