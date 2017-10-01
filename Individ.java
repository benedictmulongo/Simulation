package com.company;

/**
 * Created by ben on 2017-09-26.
 */
public class Individ
{
    String name;
    Boolean infect;
    Boolean islive;
    Boolean immun;
    int min;
    int max;
    pos position;
    int nb_days_sickness;
    int newly;

    Individ(String n, int x,int y)
    {
        this.name = n;
        this.infect = false;
        this.islive = true;
        this.immun = false;
        //this.lifetime = new int [2];
        //lifetime[0] = 5;
        this.min = 3;
        //lifetime[1] = 10;
        this.max = 5;
        this.position = new pos(x,y);
        //numbers of days elapsed since the individ were infected
        this.nb_days_sickness = 0;
        newly = -1;
    }

    Individ(String n, int x,int y, boolean inf)
    {
        this.name = n;
        this.infect = inf;
        this.islive = true;
        this.immun = false;
        //this.lifetime = new int [2];
        //lifetime[0] = 5;
        //lifetime[1] = 10;
        this.min = 3;
        //lifetime[1] = 10;
        this.max = 5;
        this.position = new pos(x,y);
        //numbers of days elapsed since the individ were infected
        //make sure to make to 2 so that people newly infected with
        // value 1 cannot infect theirs neighbours until their values
        // increment to 2 or more in the next days of infection
        this.nb_days_sickness = 1;
        this.newly = 0;
    }
    public void setNewly(int k)
    {
        this.newly = k;
    }
    public int getNewly()
    {
        return this.newly;
    }


    Individ()
    {
        this.infect = false;
        this.islive = true;
        this.immun = false;
        this.min = 5;
        //lifetime[1] = 10;
        this.max = 10;
    }

    public void setInfect(Boolean f)
    {
        this.infect = f;
        this.nb_days_sickness++;
        this.newly = 1;
    }
    public void setInfectImmun(Boolean f)
    {
        this.infect = f;
    }
    public void setIslive(Boolean l)
    {
        this.islive = l;
    }
    public void setLifetime(int m, int mx)
    {
        this.min = m;
        //lifetime[1] = 10;
        this.max = mx;
    }
    public int getMax()
    {
        return this.max;
    }
    public int getMin()
    {
        return this.min;
    }

    public void setImmun(Boolean i)
    {
        this.immun = i;
    }

    public boolean isLive()
    {
        return islive;
    }
    public boolean isInfect()
    {
        return infect;
    }
    public boolean isImmun()
    {
        return immun;
    }
    public void days_infection()
    {
        this.nb_days_sickness++;
    }
    public void setInfect_days(int f)
    {
        this.nb_days_sickness = f;
    }

    public String toString()
    {
        //return "[ Id = " + name + " , infect = " + infect + " , days_of_sick = " + nb_days_sickness + " ] ";
        return "[" + " inf = " + infect + " , sick_d = " + nb_days_sickness + ", im = " + this.immun + ", isliv " + isLive() +  " ] ";
    }
}
