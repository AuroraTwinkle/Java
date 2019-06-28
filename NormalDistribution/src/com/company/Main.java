package com.company;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        NormalDistribution normalDistribution = new NormalDistribution(0,1);
        HashSet h=new HashSet();
        double hh=0.00;
        for(int i=0;i<300;i++){
            h.add(hh);
            DecimalFormat df = new DecimalFormat("#.00");
            System.out.println("normalDistributionMap.insert(std::pair<std::string,double>("+"\"" + df.format(hh) + "\","
                    + (1 - normalDistribution.cumulativeProbability(3.00))+"));") ;
            hh+=0.01;
        }
        Iterator it=h.iterator();
        int i=0;
        while(it.hasNext())
        {
            i++;
            Object o=it.next();

        }
        
	// write your code here
    }
}
