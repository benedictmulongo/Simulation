package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args)
    {
	// write your code here
        population pop = new population();
        pop.simul();
/*        ArrayList<int[]> p = pop.getNear(2,2);
        ArrayList<int[]> q = pop.random_choose(p,0.5);
        for(int [] c : q)
            System.out.println(Arrays.toString(c));*/

    }
}
