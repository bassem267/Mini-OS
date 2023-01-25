import java.util.*;

public class Priority {
    int AverageWaitingTime = 0;
    int AverageTurnaroundTime = 0;

    public void PriorityScheduler() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter The Number Of Processes:");
        int n = myObj.nextInt();
        ArrayList<Process> qArray = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter The Process Arrival Time:");
            int a = myObj.nextInt();

            System.out.println("Enter The Process Burst Time:");
            int b = myObj.nextInt();

            System.out.println("Enter The Process Priority:");
            int p = myObj.nextInt();

            Process pro = new Process(a, b, p);
            pro.name = "P" + i;
            qArray.add(pro);
        }


        int time = 0;
        StringBuilder order = new StringBuilder();
        int[] Original = new int[n];

        for (int i = 0; i < n; i++) {
            Original[i] = qArray.get(i).burstTime;
        }

        int finishedPro = 0;
        // solve starvation problem
        while (true) {
            int min = 999, c = n;
            if (finishedPro == n)
                break;

            for (int i = 0; i < n; i++) {
                if ((qArray.get(i).arrivalTime <= time) && (qArray.get(i).finished == 0) && (qArray.get(i).priority < min)) {
                    if (qArray.get(i).priority != min) {
                        String name = qArray.get(i).name + ", ";
                        order.append(name);
                        min = qArray.get(i).priority;
                        c = i;
                    }
                }
            }

            if (time % 5 == 0){
                for (int i = 0; i < n; i++) {
                    if ((qArray.get(i).finished == 0) && (qArray.get(i).priority > 0)){
                        qArray.get(i).priority -= 1;
                    }
                }
            }

            if (c == n)
                time++;
            else {
                qArray.get(c).burstTime--;
                time++;
                if (qArray.get(c).burstTime == 0 && qArray.get(c).finished ==0) {
                    qArray.get(c).finishTime = time;
                    qArray.get(c).finished = 1;
                    finishedPro++;
                }
            }

        }

        for (int i = 0; i < n; i++) {

            qArray.get(i).turnaroundTime = qArray.get(i).finishTime - qArray.get(i).arrivalTime;
            qArray.get(i).waitingTime = qArray.get(i).turnaroundTime - Original[i];
            AverageWaitingTime += qArray.get(i).waitingTime;
            AverageTurnaroundTime += qArray.get(i).turnaroundTime;
        }

        AverageWaitingTime = AverageWaitingTime /n;
        AverageTurnaroundTime = AverageTurnaroundTime / n;

        //Printing The Results
        System.out.println("Processes execution order:");
        System.out.println(order);

        System.out.println("Process id  WT  TAT  ");
        for (int i = 0; i < n; i++) {
            System.out.println(qArray.get(i).name + "\t" + qArray.get(i).waitingTime + "\t" + qArray.get(i).turnaroundTime + "\t");
        }

        System.out.println("Average Waiting Time " + AverageWaitingTime);
        System.out.println("Average Turnaround Time " + AverageTurnaroundTime);


    }

}