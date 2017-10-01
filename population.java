package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ben on 2017-09-26.
 */
public class population
{
/*    //case 0
    final int [] X = {0,-1,-1,-1,0,1,1,1} ;
    final int [] Y = {1,1,0,-1,-1,-1,0,1};*/
//Delimiter used in CSV file
private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    //****************** data input ************************
    //size of the population
    int N;
    //probability that a sick individ infects its neighbours
    double ps;
    //the lifetime of the disease for each individ
    int min;
    int max;
    //probability that an individ X die iff X is sick
    double ps_x;
    //number of sick individs and their position
    int Ns;
    int posX;
    int posY;
    Individ [][] pop;
    //*******************************************************
    int SM,X,F,S,AS,AX,TF,Frisk;
    int epidemi;
    boolean isEpidemi;

    ArrayList<Object> coord;
    FileWriter fileWriter = null;
    population()
    {
        //Scanner in
        //****************** data input ************************
        //size of the population
        this.N = 3;
        //probability that a sick individ infects its neighbours
        this.ps = 0.2;
        //the lifetime of the disease for each individ
        this.min = 3;
        this.max = 5;
        //probability that an individ X die iff X is sick
        this.ps_x = 0.2;
        //number of sick individs and their position
        this.Ns = 2;
        this.posX = (int)(this.N/2);
        this.posY = (int)(this.N/2);
        //*******************************************************
/*        System.out.println("X = "  + this.posX);
        System.out.println("X = "  + this.posY);*/

        this.coord = new ArrayList<Object>();
        this.pop = new Individ[N][N];
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                pop[i][j] = new Individ( " " + i + "" + j, i,j);
                if( i == this.posX && j == this.posY)
                    pop[i][j] = new Individ( " " + i + "" + j, i,j,true);
            }
        }
        //******* initialize the data output parameter *************
        SM = 0;
        X = 0;
        F=0;
        S=1;
        AS=0;
        AX=0;
        //************** end of initialization **********************
        epidemi = (int)((this.N*this.N)/2) + 1;
        this.isEpidemi = false;
    }

    //****custom population constructor ***********
    population(int maxx,int minn, int p_s,int NN,int pxx, int antal_sjuka, int [] dessplacering)
    {
        //Scanner in
        //****************** data input ************************
        //size of the population
        this.N = NN;
        //probability that a sick individ infects its neighbours
        this.ps = p_s;
        //the lifetime of the disease for each individ
        this.min = maxx;
        this.max = minn;
        //probability that an individ X die iff X is sick
        this.ps_x = pxx;
        //number of sick individs and their position
        this.Ns = antal_sjuka;
        this.posX = (int)(this.N/2);
        this.posY = (int)(this.N/2);
        //*******************************************************

        this.coord = new ArrayList<Object>();
        this.pop = new Individ[NN][NN];
        int x = 0, y = 1;
        for(int i = 0, j = 1; j < dessplacering.length; i++,j++) {
            int q = dessplacering[i];
            int  k = dessplacering[j];
            pop[q][k] = new Individ(" " + q + "" + k, q, k, true);
        }
        for(int i = 0; i < NN; i++)
        {
            for(int j = 0; j < NN; j++)
            {
                if(pop[i][j] == null) {
                    pop[i][j] = new Individ(" " + i + "" + j, i, j);
                }
            }
        }
        //******* initialize the data output parameter *************
        SM = 0;
        X = 0;
        F=0;
        S=antal_sjuka;
        AS=0;
        AX=0;
        //************** end of initialization **********************
        epidemi = (int)((this.N*this.N)/2);
        this.isEpidemi = false;
    }
    //*********************************************



    public int [] getDataOutput()
    {
        int [] bark = {this.SM,this.X,this.F,this.S,this.AS,this.AX};
        return bark;
    }
    public String getDataOutputString()
    {
        StringBuilder br = new StringBuilder();
        br.append(" Smittade per day " + this.SM + " ," + NEW_LINE_SEPARATOR);
        br.append(" Döda per day " + this.X + " ,"+ NEW_LINE_SEPARATOR);
        br.append(" Tillfriskna per day " + this.F + " ,"+ NEW_LINE_SEPARATOR);
        br.append(" Sjuka per day " + this.S + " ,"+ NEW_LINE_SEPARATOR);
/*        br.append(" Accumelerad Sjuka  " + this.AS + " ,"+ NEW_LINE_SEPARATOR);
        br.append(" Accumelerad döda  " + this.AX + " ,"+ NEW_LINE_SEPARATOR);*/
        br.append(" Totalt tillfriskna  " + this.TF + " ,"+ NEW_LINE_SEPARATOR);
        br.append(" Friska  " + this.Frisk + " ,"+ NEW_LINE_SEPARATOR);


        /*return br.toString();*/

        return br.toString();
    }

    public void getDataOutputString(int d)
    {
        try
        {
            String tr = "Dataoutput" + d + ".csv";
            this.fileWriter = new FileWriter(tr);
            //Write the CSV file header
            this.fileWriter.append("**************** DATA OUTPUT ******************");

            //Add a new line separator after the header
            this.fileWriter.append(NEW_LINE_SEPARATOR);

            //Write a new student object list to the CSV file
            this.fileWriter.append(" Smittade per day " + this.SM);
            this.fileWriter.append(NEW_LINE_SEPARATOR);
            this.fileWriter.append(" Döda per day " + this.X );
            this.fileWriter.append(NEW_LINE_SEPARATOR);
            this.fileWriter.append(" Tillfriskna per day " + this.F );
            this.fileWriter.append(NEW_LINE_SEPARATOR);
            this.fileWriter.append(" Sjuka per day " + this.S );
            this.fileWriter.append(NEW_LINE_SEPARATOR);
/*            this.fileWriter.append(" Accumelerad Sjuka  " + this.AS);
            this.fileWriter.append(NEW_LINE_SEPARATOR);
            this.fileWriter.append(" Accumelerad döda  " + this.AX);
            this.fileWriter.append(NEW_LINE_SEPARATOR);*/
            this.fileWriter.append(" Totalt tillfriskna  " + this.TF);
            this.fileWriter.append(NEW_LINE_SEPARATOR);
            this.fileWriter.append(" Friska  " + this.Frisk);
            this.fileWriter.append(NEW_LINE_SEPARATOR);

            System.out.println("CSV file was created successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
    }
    public ArrayList<int[]> getNear(int x1, int y1)
    {
        return neighbours(x1,y1,this.pop);
    }

    public ArrayList<int[]> neighbours(int posX, int posY, Individ [][] P)
    {
        int dim = P[0].length;
        pos point = new pos(posX,posY);
        ArrayList<Object> cd = new ArrayList<Object>();
        ArrayList<int[]> par = new ArrayList<int[]>();
        pos [] back;
        //get the situation and postion of object individ
        // around the individ in index posX and posY
        int sit = situation(posX,posY,dim);
//        System.out.println("case is = " + sit);
        //get the corresponding
//        int [] cord = (int[]) coord.get(sit);
        if((sit >= 0 ) && (sit <= 3)) {
            //case 1
            //back = new int[6];
            switch (sit)
            {
                case 0 :
                    par.add(point.getRight());
                    par.add(point.getQ4());
                    par.add(point.getDown());
                    break;
                case 1 :
                    par.add(point.getLeft());
                    par.add(point.getQ3());
                    par.add(point.getDown());
                    break;
                case 2 :
                    par.add(point.getUp());
                    par.add(point.getQ1());
                    par.add(point.getRight());
                    break;
                case 3 :
                    par.add(point.getUp());
                    par.add(point.getQ2());
                    par.add(point.getLeft());
                    break;
            }
        }
        else if((sit >= 4 ) && (sit <= 7)) {
            //case 2 or 1
            //back = new int[12];
            switch (sit)
            {
                case 4 :
                    par.add(point.getQ3());
                    par.add(point.getLeft());
                    par.add(point.getRight());
                    par.add(point.getQ4());
                    par.add(point.getDown());
                    break;
                case 5 :
                    par.add(point.getLeft());
                    par.add(point.getQ1());
                    par.add(point.getUp());
                    par.add(point.getRight());
                    par.add(point.getQ2());
                    break;
                case 6 :
                    par.add(point.getUp());
                    par.add(point.getQ1());
                    par.add(point.getRight());
                    par.add(point.getQ4());
                    par.add(point.getDown());
                    break;
                case 7 :
                    par.add(point.getUp());
                    par.add(point.getQ2());
                    par.add(point.getLeft());
                    par.add(point.getQ3());
                    par.add(point.getDown());
                    break;
            }
        }
        else {
            //back = new int[16];
            par.add(point.getUp());
            par.add(point.getRight());
            par.add(point.getDown());
            par.add(point.getLeft());
            par.add(point.getQ1());
            par.add(point.getQ2());
            par.add(point.getQ3());
            par.add(point.getQ4());
        }

        return par;
    }

    public int situation(int x, int y, int n)
    {
        int c = 0;
        //case 1 4 cases
        if( x == 0 && y == 0)
            return 0;
        else if(x == 0 && y == n -1)
            return 1;
        else if(x == n - 1 && y == 0)
            return 2;
        else if (x ==  n - 1 && y == n - 1)
            return 3;

        //case 2 2 cases
        if( x == 0)
            return 4;
        else if(x == n - 1)
            return 5;

        if( y == 0)
            return 6;
        else if(y == n - 1)
            return 7;
        return -1;
    }

    public void display()
    {
        for(int i = 0; i < this.N; i++)
        {
            for(int j = 0; j < this.N; j++)
            {

                System.out.print(" " + pop[i][j]);
            }
            System.out.println(" ");
        }

    }
    //select a.size() * random
    public ArrayList<int[]> random_choose(ArrayList<int[]> univ, double prob)
    {
        ArrayList<int[]> par = new ArrayList<int[]>();
        //choose no more than this number of element
        int limit = (int)(prob*univ.size());
//        System.out.println("univ size = " + univ.size());
//        System.out.println("limit = " + limit);
        boolean [] check = new boolean[univ.size()];
        int t,k;
        int count = 0;
        for(int i = 0; (par.size() <= limit); i++ )
        {
            t = (int)(univ.size() * Math.random());
            if(count < limit)
            {
                if(check[t] == false) {
                    check[t] = true;
                    par.add(univ.get(t));
                    count++;
                }
            }
            else if(count == limit) {
                break;
            }
            else
                break;
        }
        return par;
    }

    //infect will be called from another function
    // every call will constituetes an iteration and
    // represent one day in our model
    public void infect()
    {
        for(int i = 0; i < this.N; i++)
        {
            for(int j = 0; j < this.N; j++)
            {
                if((this.pop[i][j].isInfect() && this.pop[i][j].isLive()) && (this.pop[i][j].isImmun() == false && (this.pop[i][j].getNewly() == 0)) )
                {
                    //********************* handle neighbours around this individ *********************************
                    ArrayList<int[]> ind_toInfect = neighbours(i,j,this.pop);
                    ArrayList<int[]> ind_Infect = random_choose(ind_toInfect,this.ps);
                    //to add check if p is already infected
                    for(int [] p : ind_Infect) {
                        if(this.pop[p[0]][p[1]].isInfect() == false)
                        {
                            this.pop[p[0]][p[1]].setInfect(true);
                        }

                    }

                    //other cases probability that X dies iff X is sick
                    // in the same loop get neigbours P
                    // in P random choose K individs with prob p_X
                    // and for each individ  p in K if p is infected
                    // set the p individ has dead setIslive = false;
                    ArrayList<int[]> ind_die = random_choose(ind_toInfect,this.ps_x);
                    for(int [] p : ind_die) {
                        if(this.pop[p[0]][p[1]].isInfect()) {
                            this.pop[p[0]][p[1]].setIslive(false);
                            //augment the number of dead individ
                            //this.X++;
                        }
                    }

                    //**************************************** end  **********************************************
                    this.pop[i][j].days_infection();
                    //********************* handle this individ in index *****************************************
                    //handle different cases min och max set immun
                    if(this.pop[i][j].nb_days_sickness > this.pop[i][j].getMax())
                    {
                        this.pop[i][j].setImmun(true);
                        this.pop[i][j].setInfectImmun(false);
                        //Increment the number of people immune or who have been curred
                        //this.F++;
                    }

                }

                // *********** update the number of accumulated dead and ************
                // ******** sick individ since the beginning of the infection *******
                //ändra den här och uppdatera värdet efter varje iteration
                // ta dag 1 får in värden och updatera sedan
                //*******************************************************************
                if(this.S >= epidemi)
                    this.isEpidemi = true;
            }
        }

        //update for the next day
        this.S = 0;
        this.SM = 0;
        this.F = 0;
        this.X = 0;
        this.TF = 0;
        this.Frisk = 0;
        for(int i = 0; i < this.N; i++)
        {
            for(int j = 0; j < this.N; j++)
            {
                if(this.pop[i][j].isInfect() && this.pop[i][j].isLive())
                    this.S = this.S + 1;
                if((!this.pop[i][j].isInfect()) && this.pop[i][j].isLive())
                    this.Frisk = this.Frisk + 1;

                if(this.pop[i][j].isInfect()) {
                    //peuples[i][j].days_infection();
                    this.pop[i][j].setNewly(0);

                    if(this.pop[i][j].nb_days_sickness == 1 && this.pop[i][j].isLive())
                    {
                        this.SM = this.SM + 1;
                    }
                    if(this.pop[i][j].isLive() == false)
                    {
                        this.X = this.X + 1;
                    }
                    if(this.pop[i][j].isImmun())
                    {
                        this.F = this.F + 1;
                    }
                }
                if(this.pop[i][j].isImmun())
                    this.TF = this.TF + 1;
            }
        }
    }

    public int stop()
    {

        int bk = (this.N) * (this.N);
        int count = 0;
        for(int i = 0; i < this.N; i++)
        {
            for(int j = 0; j < this.N; j++)
            {
                if(!this.pop[i][j].isInfect()) {
                    count = count + 1;
                }
            }
        }
        return count;
    }


    public void simul()
    {
        //call infect N times N = days
        int i;
        int count = 0;
        int bk = (this.N) * (this.N) - this.X;
        for(i = 1; count != bk ; i++)
        {
            System.out.println("************************** day " + i + " ******************************** ");
            infect();
            display();
            System.out.println();
            this.AS = this.AS + this.S;
            this.AX = this.AX + this.X;
            count = stop();
            bk = (this.N) * (this.N) - this.X;
            //System.out.println( "Count ==== " + count);
            System.out.println( "Data : \n" + getDataOutputString());
            getDataOutputString(i);
            System.out.println(" ************************  day " + i + " finished ************************");
        }


        System.out.println("It is an epidemi ? " + this.isEpidemi);
    }

    public void simulate(int N)
    {
        //call infect N times N = days
        int i;
        for(i = 1; i  < N + 1; i++)
        {
            System.out.println("************************** day " + i + " ******************************** ");
            infect();
            display();
            System.out.println();
            this.AS = this.AS + this.S;
            this.AX = this.AX + this.X;

            System.out.println( "Data : \n" + getDataOutputString());
            System.out.println(" ************************  day " + i + " finished ************************");
        }
        getDataOutputString(i);

        System.out.println("It is an epidemi ? " + this.isEpidemi);
    }


    public void simulate()
    {
        System.out.println("************************** day " + 0 + " ******************************** ");
        display();
        System.out.println( "Data : \n" + getDataOutputString());
        System.out.println(" ************************  day " + 0 + " finished ************************");
        simulation();

    }
    public void simulation()
    {
        //call infect N times N = days
        int i;
        for(i = 1; i  < this.N + 1; i++)
        {
            System.out.println("************************** day " + i + " ******************************** ");
            infect();
            display();
            System.out.println();
            this.AS = this.AS + this.S;
            this.AX = this.AX + this.X;

            System.out.println( "Data : \n" + getDataOutputString());
            System.out.println(" ************************  day " + i + " finished ************************");
        }
        getDataOutputString(i);

        System.out.println("It is an epidemi ? " + this.isEpidemi);
    }
}
