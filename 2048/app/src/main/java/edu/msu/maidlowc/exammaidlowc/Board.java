package edu.msu.maidlowc.exammaidlowc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Board {

    /**
            * Paint for filling the area the board is in
     */
    private Paint fillPaint;

    private Paint textPaint;


    public int getScore() {
        return score;
    }

    private int score;

    private int tiles[][] = {{0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};


    private float cell_left;
    private float cell_top;
    private float cell_right;
    private float cell_bottom;


    /**
     * Paint for outlining the area the board is in
     */
    private Paint outlinePaint;

    private int canvasWidth;

    private int canvasHeight;


    /**
     * Percentage of the display width or height that
     * is occupied by the board.
     */
    final static float SCALE_IN_VIEW = 0.9f;

    /**
     * The size of the board in pixels
     */
    private int boardSize;

    /**
     * How much we scale
     */
    private float scaleFactor;

    /**
     * Left margin in pixels
     */
    private int marginX;


    /**
     * Top margin in pixels
     */
    private int marginY;

    private Paint tilePaint = new Paint();

    /**
     * Random number generator
     */
    private Random random = new Random();

    //completion bool
    private boolean complete = false;

    Context con = null;

    public Board(Context context) {

        con = context;
        randomize();
        randomize();

        fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setColor(Color.LTGRAY);

        outlinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outlinePaint.setColor(Color.BLACK);
        outlinePaint.setStyle(Paint.Style.STROKE);

    }


    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    public void draw(Canvas canvas) {

        int wid = canvas.getWidth();
        int hit = canvas.getHeight();

        // Determine the minimum of the two dimensions
        int minDim = wid < hit ? wid : hit;

        boardSize = (int)(minDim * SCALE_IN_VIEW);

        // Compute the margins so we center the board
        marginX = (int)((wid - boardSize) / 2f);
        marginY = (int)((hit - boardSize) / 2f);

        //
        // Draw the outline of the board
        //

        canvas.drawRect(marginX, marginY, marginX + boardSize, marginY + boardSize, fillPaint);
        canvas.drawRect(marginX, marginY, marginX + boardSize, marginY + boardSize, outlinePaint);


        canvasWidth = marginX + boardSize;
        canvasHeight = marginY + boardSize;


        //DRAW THE TILES
        for(int i=0; i<tiles.length; i++) {
            for(int j=0; j<tiles[i].length; j++) {
                int value = tiles[i][j];
                if(value > 0){
                    drawTile(canvas, marginX, marginY, boardSize, scaleFactor, value, i, j);
                }
            }
        }

    }


    public void drawTile(Canvas canvas, int marginX, int marginY, int boardSize, float scaleFactor, int value, int i, int j){

        float cell = boardSize /4;
        float yad = ((cell / 2) + (2*cell/3))/2;

        getColor(value);

        getCoordFromIndex(i,j,boardSize);
        canvas.drawRect(marginX + cell_left, marginY + cell_top, marginX + cell_right, marginY + cell_bottom, tilePaint);
        canvas.drawRect(marginX + cell_left, marginY + cell_top, marginX + cell_right, marginY + cell_bottom, outlinePaint);


        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(cell/4);

        textPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(String.valueOf(value), marginX + cell_left + cell/2, marginY + cell_top + yad ,textPaint); //x=300,y=300

    }

    public void getCoordFromIndex(int row, int col, int boardSize){


        float cell = boardSize / 4;

        if(row == 0){
            cell_top = 0;
            cell_bottom = cell;
        }
        else if(row == 1){

            cell_top = cell;
            cell_bottom = cell * 2;

        }
        else if(row == 2){

            cell_top = cell * 2;
            cell_bottom = cell * 3;

        }
        else if(row == 3){
            cell_top = cell * 3;
            cell_bottom = boardSize;
        }

        if(col == 0){
            cell_left = 0;
            cell_right = cell;

        }
        else if(col == 1){

            cell_left = cell;
            cell_right = cell * 2;

        }
        else if(col == 2){

            cell_left = cell * 2;
            cell_right = cell * 3;

        }
        else if(col == 3){
            cell_left = cell * 3;
            cell_right = boardSize;
        }

    }

    public void getColor(int value){

        if(value == 2) {
            tilePaint.setColor(Color.rgb(238, 228, 218));
            tilePaint.setStyle(Paint.Style.FILL);
        }
        if(value == 4){
            tilePaint.setColor(Color.rgb(237, 224, 199));
            tilePaint.setStyle(Paint.Style.FILL);

        }
        if(value == 8){
            tilePaint.setColor(Color.rgb(245, 149, 99));
            tilePaint.setStyle(Paint.Style.FILL);

        }
        if(value == 16){
            tilePaint.setColor(Color.rgb(246, 124, 95));
            tilePaint.setStyle(Paint.Style.FILL);

        }
        if(value == 32){
            tilePaint.setColor(Color.rgb(246, 94, 59));
            tilePaint.setStyle(Paint.Style.FILL);
        }
        if(value == 64){
            tilePaint.setColor(Color.rgb(237, 204, 97));
            tilePaint.setStyle(Paint.Style.FILL);
        }

        ///fill in rest of colors
        if(value == 128){
            tilePaint.setColor(Color.rgb(237, 194, 46));
            tilePaint.setStyle(Paint.Style.FILL);

        }
        if(value == 256){
            tilePaint.setColor(Color.rgb(183, 132, 170));
            tilePaint.setStyle(Paint.Style.FILL);

        }
        if(value == 512){
            tilePaint.setColor(Color.rgb(170, 95, 166));
            tilePaint.setStyle(Paint.Style.FILL);
        }
        if(value == 1024){
            tilePaint.setColor(Color.CYAN);
            tilePaint.setStyle(Paint.Style.FILL);

        }
        if(value == 2048){
            tilePaint.setColor(Color.RED);
            tilePaint.setStyle(Paint.Style.FILL);

        }


    }

    public void randomize(){

        int r1 = ThreadLocalRandom.current().nextInt(0, 3 + 1);
        int r2 = ThreadLocalRandom.current().nextInt(0, 3 + 1);


        while(tiles[r1][r2] != 0){

            r1 = ThreadLocalRandom.current().nextInt(0, 3 + 1);
            r2 = ThreadLocalRandom.current().nextInt(0, 3 + 1);

        }
        tiles[r1][r2] = 2;
    }

    public boolean space(){
        for(int i = 0; i<tiles.length; i++){
            for(int j = 0; j < tiles[i].length; j++){
                int value = tiles[i][j];
                if (value == 0){
                    return true;
                }
            }
        }
        return false;
    }

    public void failState(){
        Toast.makeText(con, "There are no more moves, you've lost the game!", Toast.LENGTH_LONG).show();
    }

    private void winState(){
        Toast.makeText(con, "CONGRATULATION! 2048! YOU WON!", Toast.LENGTH_LONG).show();
    }

    private Context getApplicationContext() {
        return this.getApplicationContext();
    }

    public boolean moveCheck(){
        for(int i=0; i < tiles.length; i++) {
            for(int j=0; j < tiles[i].length; j++) {
                int value = tiles[i][j];
                //plan for boundaries
                //test top tile
                if(i-1 != -1){
                    int temp = tiles[i-1][j];
                    if (value == temp){
                        return true;
                    }
                }
                //test bottom tile
                if(i+1 != 4){
                    int temp = tiles[i+1][j];
                    if (value == temp){
                        return true;
                    }

                }
                //test left tile
                if(j-1 != -1){
                    int temp = tiles[i][j-1];
                    if (value == temp){
                        return true;
                    }

                }
                //test right tile
                if(j+1 != 4){
                    int temp = tiles[i][j+1];
                    if (value == temp){
                        return true;
                    }

                }

            }
        }

        return false;
    }

    public boolean onMove(String direction){
        int move_count = 0;
        if(direction == "RIGHT"){
            //ITERATE THROUGH BACKWARDS
            for(int i=3; i>=0; i--) {
                for(int j=3; j>=0; j--) {
                    //if most right continue
                    if(j+1==4){
                        continue;
                    }
                    int temp = tiles[i][j];
                    //test if on tile
                    if(temp > 0){
                        int n = j;
                        while(n + 1 != 4) {

                            //test if next door is tile
                            if (tiles[i][n + 1] == 0) {
                                tiles[i][n + 1] = temp;
                                tiles[i][n] = 0;
                                move_count += 1;
                            }
                            else{
                                break;
                            }

                            n += 1;

                        }
                        if(n + 1 != 4) {
                            //test if next door is negative
                            if(tiles[i][n+1] > 0) {

                                //test if next door is same value
                                if (tiles[i][n + 1] == tiles[i][n]) {
                                    //set as negative marking as merged this turn
                                    tiles[i][n + 1] = temp * -2;
                                    score += temp * 2;
                                    tiles[i][n] = 0;
                                    move_count += 1;

                                    //check if 2048
                                    if (temp * 2 == 2048) {
                                        winState();
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        if(direction == "LEFT"){
            //ITERATE THROUGH BACKWARDS
            for(int i=0; i < tiles.length; i++) {
                for(int j=0; j < tiles[i].length; j++) {
                    //if most left continue
                    if(j-1==-1){
                        continue;
                    }
                    int temp = tiles[i][j];
                    //test if on tile
                    if(temp > 0){
                        int n = j;
                        while(n - 1 != -1) {
                            //test if next door is tile
                            if (tiles[i][n - 1] == 0) {
                                tiles[i][n - 1] = temp;
                                tiles[i][n] = 0;
                                move_count += 1;
                            }
                            else{
                                break;
                            }
                            n -= 1;

                        }
                        if(n - 1 != -1) {
                            //test if next door is negative
                            if(tiles[i][n - 1] > 0) {

                                //test if next door is same value
                                if (tiles[i][n - 1] == tiles[i][n]) {
                                    tiles[i][n - 1] = temp * -2;
                                    score += temp * 2;
                                    tiles[i][n] = 0;
                                    move_count += 1;
                                    //check if 2048
                                    if (temp * 2 == 2048) {
                                        winState();
                                    }

                                }
                            }
                        }
                    }
                }
            }

        }
        if(direction == "UP"){
            //ITERATE THROUGH BACKWARDS
            for(int i=0; i < tiles.length; i++) {
                for(int j=0; j < tiles[i].length; j++) {
                    //if most top continue
                    if(i-1==-1){
                        continue;
                    }
                    int temp = tiles[i][j];
                    //test if on tile
                    if(temp > 0){
                        int n = i;
                        while(n - 1 != -1) {
                            //test if next door is tile
                            if (tiles[n - 1][j] == 0) {
                                tiles[n - 1][j] = temp;
                                tiles[n][j] = 0;
                                move_count += 1;
                            }
                            else{
                                break;
                            }
                            n -= 1;

                        }
                        if(n - 1 != -1) {

                            //test if next door is negative
                            if(tiles[n-1][j] > 0) {

                                //test if next door is same value
                                if (tiles[n - 1][j] == tiles[n][j]) {
                                    tiles[n - 1][j] = temp * -2;
                                    score += temp * 2;
                                    tiles[n][j] = 0;
                                    move_count += 1;
                                    //check if 2048
                                    if (temp * 2 == 2048) {
                                        winState();
                                    }

                                }
                            }
                        }
                    }
                }
            }

        }
        if(direction == "DOWN"){
            //ITERATE THROUGH BACKWARDS
            for(int i=3; i>=0; i--) {
                for(int j=3; j>=0; j--) {
                    //if most bottom continue
                    if(i+1==4){
                        continue;
                    }
                    int temp = tiles[i][j];
                    //test if on tile
                    if(temp > 0){
                        int n = i;
                        while(n + 1 != 4) {

                            //test if next door is tile
                            if (tiles[n+1][j] == 0) {
                                tiles[n+1][j] = temp;
                                tiles[n][j] = 0;
                                move_count += 1;
                            }
                            else{
                                break;
                            }

                            n += 1;

                        }
                        if(n + 1 != 4) {

                            //test if next door is negative
                            if(tiles[n+1][j] > 0) {

                                //test if next door is same value
                                if (tiles[n + 1][j] == tiles[n][j]) {
                                    tiles[n + 1][j] = temp * -2;
                                    score += temp * 2;
                                    tiles[n][j] = 0;
                                    move_count += 1;

                                    //check if 2048
                                    if (temp * 2 == 2048) {
                                        winState();
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        normalize();
        if(move_count == 0){
            return false;
        }
        else{
            return true;
        }

    }

    private void normalize(){
        for(int i=0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if(tiles[i][j] < 0){
                    int value = tiles[i][j];
                    tiles[i][j] = value * -1;
                }
            }
        }
    }


}
