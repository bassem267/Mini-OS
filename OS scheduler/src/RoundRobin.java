import java.util.*;

import static java.lang.Integer.min;

public class RoundRobin {

    public ArrayList<Process> arr = new ArrayList<>();

    public void RoundRobinScheduler() {
        System.out.println("Enter The Number Of Processes:");
        Scanner myObj = new Scanner(System.in);
        int n = myObj.nextInt();
        System.out.println("Enter The Round Robin Time Quantum:");
        int RR = myObj.nextInt();
        System.out.println("Enter The Context switching:");
        int CS = myObj.nextInt();


        for (int i = 0; i < n; i++) {
            System.out.println("Enter The Process Arrival Time:");
            int a = myObj.nextInt();
            System.out.println("Enter The Process Burst Time:");
            int b = myObj.nextInt();
            Process pro = new Process(a, b, "P" + (i+1));
            arr.add(pro);
        }
        int time = 0;
        int index = 0;
        String lastP = "";
        int k = 0;
        double tWaitingtime = 0, tTurnover = 0;
        boolean d= true;
        StringBuilder order = new StringBuilder();
        while (d) {
            if (time >= arr.get(index).arrivalTime) {
                if (arr.get(index).burstTime > 0) {
                    int unit = min(RR, arr.get(index).burstTime);
                    time += unit;
                    arr.get(index).burstTime -= unit;
                    order.append(arr.get(index).name + ", ");


                    if (!lastP.equals(arr.get(index).name))
                        time+=CS;

                    //calculate the waiting tie of the process
                    if (arr.get(index).burstTime == 0) {
                        arr.get(index).waitingTime = time - arr.get(index).arrivalTime - arr.get(index).burst2;
                        arr.get(index).turnaroundTime = time - arr.get(index).arrivalTime;

                        tWaitingtime += arr.get(index).waitingTime;
                        tTurnover += arr.get(index).turnaroundTime;
                        arr.get(index).flag=false;


                    }



                    lastP = arr.get(index).name;
                    k++;
                }

            }
            index++;
            if (index == n) {
                index = 0;
                if (k == 0)
                    time++;
                k = 0;

                int count = 0;

                for (Process obj : arr) {
                    if (!obj.flag)
                        count++;

                }

                if (count == n)
                    d=false;


            }

        }


        System.out.println("Processes execution order:");
        System.out.println(order);

        System.out.println("Process id  WT  TAT  ");
        for (int i = 0; i < n; i++) {
            System.out.println(arr.get(i).name + "\t" + arr.get(i).waitingTime + "\t" + arr.get(i).turnaroundTime + "\t");
        }

        System.out.println("Average Waiting Time " + tWaitingtime/(double)n);
        System.out.println("Average Turnaround Time " + tTurnover/(double) n);


    }



}