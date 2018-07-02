package com.tommy.tictactoe;

public class Position {
    private int value;
    private int row;
    private int column;

    public Position(int value) {
        this.value = value;
        this.row = rowConverter(this.value);
        this.column = columConverter(this.value);
    }
    public Position(int row, int column){
        this.column = column;
        this.row = row;
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

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "Position{" +
                "value=" + value +
                ", row=" + row +
                ", column=" + column +
                '}';
    }


}
