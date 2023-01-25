import java.util.*;

public class SJF {
    int AverageWaitingTime = 0;
    int AverageTurnaroundTime = 0;

    public void SJFScheduler(){
        //Asking For Input
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter The Number Of Processes:");
        int n = myObj.nextInt();
        System.out.println("Enter The Context switching:");
        int CS = myObj.nextInt();
        ArrayList<Process> qArray = new ArrayList<>();

        for (int i=0 ; i<n ; i++){

            System.out.println("Enter The Process Arrival Time:");
            int a = myObj.nextInt();

            System.out.println("Enter The Process Burst Time:");
            int b = myObj.nextInt();

            Process pro = new Process(a, b, 0);
            pro.name = "P" + i;
            qArray.add(pro);
        }

        int time = - CS;
        StringBuilder order = new StringBuilder();
        int[] Original = new int[n];

        for(int i=0 ; i<n ; i++)
        {
            Original[i] = qArray.get(i).burstTime;
        }

        int finishedPro = 0;

        while(true){
            int min=99999999, c=n;
            if (finishedPro == n)
                break;

            for (int i=0 ; i<n ; i++)
            {
                if ((qArray.get(i).arrivalTime <= time) && (qArray.get(i).finished == 0) && (qArray.get(i).burstTime < min))
                {
                    if (qArray.get(i).burstTime != min){
                        String name = qArray.get(i).name + ", ";
                        order.append(name);
                        time += CS;
                        min = qArray.get(i).burstTime;
                        c = i;
                    }
                }
            }

            if (c==n)
                time++;
            else
            {
                qArray.get(c).burstTime--;
                time++;
                if (qArray.get(c).burstTime == 0)
                {
                    qArray.get(c).finishTime = time;
                    qArray.get(c).finished = 1;
                    finishedPro++;
                }
            }
        }

        for(int i=0 ; i<n ; i++)
        {
            qArray.get(i).turnaroundTime = qArray.get(i).finishTime - qArray.get(i).arrivalTime;
            qArray.get(i).waitingTime = qArray.get(i).turnaroundTime - Original[i];
            AverageWaitingTime += qArray.get(i).waitingTime;
            AverageTurnaroundTime += qArray.get(i).turnaroundTime;
        }

        AverageWaitingTime = AverageWaitingTime / n;
        AverageTurnaroundTime = AverageTurnaroundTime / n;

        //Printing The Results
        System.out.println("Processes execution order:");
        System.out.println(order);

        System.out.println("Process id  WT  TAT  ");
        for(int i=0 ; i<n ; i++)
        {
            System.out.println(qArray.get(i).name + "\t" + qArray.get(i).waitingTime + "\t" + qArray.get(i).turnaroundTime + "\t");
        }

        System.out.println("Average Waiting Time "+ AverageWaitingTime);
        System.out.println("Average Turnaround Time "+ AverageTurnaroundTime);

    }

}

