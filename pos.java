package com.company;

/**
 * Created by ben on 2017-09-27.
 */
public class pos
{
    int posX;
    int posY;
    pos(int x,int y)
    {
        this.posX = x;
        this.posY = y;
    }

    public int [] getUp()
    {
        int [] f = {posX - 1, posY};
        return f;
    }
    public  int [] getDown()
    {
        int [] f = {posX + 1, posY};
        return f;
    }

    public int [] getRight()
    {
        int [] f = {posX, posY + 1};
        return f;
    }
    public  int [] getLeft()
    {
        int [] f = {posX, posY - 1};
        return f;
    }
    public  int [] getQ1()
    {
        int [] f = {posX - 1, posY + 1};
        return f;
    }
    public  int [] getQ2()
    {
        int [] f = {posX - 1, posY - 1};
        return f;
    }
    public  int [] getQ3()
    {
        int [] f = {posX + 1, posY - 1};
        return f;
    }
    public  int [] getQ4()
    {
        int [] f = {posX + 1, posY + 1};
        return f;
    }
    public String toString()
    {
        return "[ X = " + posX + " , Y = " + posY + " ] ";
    }
}
