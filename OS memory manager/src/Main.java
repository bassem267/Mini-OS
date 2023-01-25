/*
 * Marwan Mohamed     20200512
 * Fares Saad         20200372
 * Bassem Mohamed     20200112
 * Yahia Salah        20200638
 * Ganna Ibrahim      20200124
 * */

import java.util.*;


public class Main {
    public static void main(String[] args) {
        
        int be;
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter Number of partition: ");
        int n = myObj.nextInt();

        ArrayList<Partition> partitions = new ArrayList<>();

        System.out.println("Enter Size of partition: ");

        for (int i=0 ; i<n ; i++){
            System.out.println("Partition" + i + ": ");
            int s = myObj.nextInt();
            Partition p = new Partition(s, i);
            partitions.add(p);
        }


        System.out.println("Enter Number of processes: ");
        int np = myObj.nextInt();
        ArrayList<Process> processes = new ArrayList<>();

        System.out.println("Enter Size of processes: ");

        for (int i=0 ; i < np ; i++){
            System.out.println("Process" + (i+1) + ": ");
            int s = myObj.nextInt();
            Process ps = new Process(s, i);
            processes.add(ps);
        }
        do {

            System.out.println("Select the policy you want to apply: ");
            System.out.println("1. First fit ");
            System.out.println("2. Best fit ");
            System.out.println("3. Worst fit ");
            int c = myObj.nextInt();

            if ( c == 1){
                firstFit f = new firstFit(partitions, processes);
            }
            else if( c == 2){
                bestFit b = new bestFit(partitions, processes);
            }
            else if( c == 3){
                worstFit w = new worstFit(partitions, processes);
            }
            else {
                System.out.println("Please Select A Number from 1 to 3 According to the policy you want: ");
            }

            System.out.println("If you want to exit Enter 0 else enter any number");
            be = myObj.nextInt();
        }
        while (be != 0);

         


        // testing part easily

        /*ArrayList<Partition> partitions = new ArrayList<>();
        ArrayList<Process> processes = new ArrayList<>();
        partitions.add(new Partition(90,0));
        partitions.add(new Partition(20,1));
        partitions.add(new Partition(5,2));
        partitions.add(new Partition(30,3));
        partitions.add(new Partition(120,4));
        partitions.add(new Partition(80,5));

        processes.add(new Process(15,0));
        processes.add(new Process(90,1));
        processes.add(new Process(30,2));
        processes.add(new Process(100,3));

        firstFit f = new firstFit(partitions, processes);
        bestFit b = new bestFit(partitions, processes);
        worstFit w = new worstFit(partitions, processes);
*/



    }
}