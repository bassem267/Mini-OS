import java.util.*;


public class AG {
    ArrayList<Process> process = new ArrayList<>();
    ArrayList<Process> arrivalQueue = new ArrayList<>();
    ArrayList<String> quantumInfo = new ArrayList<>();
    ArrayList<String> excutionOrder = new ArrayList<>();
    ArrayList<String> order = new ArrayList<>();
    private int currentTime = 0;

    AG(ArrayList<Process> process){
        this.process = process;
    }

    AG(){

    }

    public void start(ArrayList<Process> arrivalQueue) throws InterruptedException {
        this.arrivalQueue = arrivalQueue;
        int i = 0, processRunningTime = 0;
        Process currentProcess = new Process();

        while (true) {
            if (!arrivalQueue.isEmpty() && currentTime < arrivalQueue.get(0).getArrivalTime()) {
                order.add(null);
                currentTime++;
                continue;
            }
            while (i != arrivalQueue.size() && currentTime >= arrivalQueue.get(i).getArrivalTime()) {
                currentProcess = arrivalQueue.get(i);
                calculateFactor(currentProcess);
                process.add(currentProcess);
                ++i;
            }
            break;
        }
        int ans = getMinimumFactorProcess(currentProcess);
        if (ans != -1) {
            currentProcess = process.get(ans);
            process.remove(ans);
        } else {
            process.remove(process.size() - 1);
        }

        while (true) {

            if (process.isEmpty() && isDead(currentProcess) && i == arrivalQueue.size()) break;

//			System.out.println(process.size() + " " + processRunningTime + " " +currentProcess.getBurstTime()+ " "+currentTime);

            while (i < arrivalQueue.size() && currentTime >= arrivalQueue.get(i).getArrivalTime()) {
//				System.out.println("time added is  "+currentTime);
                calculateFactor(arrivalQueue.get(i));
                process.add(arrivalQueue.get(i));
                ++i;
            }

            if (isDead(currentProcess)) {
                if (processRunningTime != currentProcess.getQuantum()) {
                    int result = getMinimumFactorProcess(currentProcess);
                    if (result != -1) {
                        currentProcess = changeProcess(result, currentProcess);
                    } else
                        currentProcess = changeProcess(0, currentProcess);
                } else
                    currentProcess = changeProcess(0, currentProcess);
                processRunningTime = 0;
            } else if (processRunningTime >= Math.ceil(currentProcess.getQuantum() / 2.0)) {
                if (processRunningTime < currentProcess.getQuantum()) {
                    int result = getMinimumFactorProcess(currentProcess);
//					System.out.println("result is "+ result);
                    if (result != -1) {
                        changeProcessQuantum(currentProcess, processRunningTime);
                        currentProcess = changeProcess(result, currentProcess);
                        processRunningTime = 0;
                    }
                } else {
                    changeProcessQuantum(currentProcess, processRunningTime);
                    currentProcess = changeProcess(0, currentProcess);
                    processRunningTime = 0;

                }
            }

            secondPassed(currentProcess, ++processRunningTime);
        }
    }

    public void addChangedQuantum(int unChangedQuantum, Process currentProcess) {
        StringBuilder change = new StringBuilder("( ");
        for (Process i : arrivalQueue) {
            if (i.getName().equals(currentProcess.getName())) {
                if (unChangedQuantum != 0)
                    change.append(unChangedQuantum).append("+").append(currentProcess.getQuantum() - unChangedQuantum);
                else change.append("0");
            } else change.append(i.getQuantum());
            change.append(" , ");
        }
        change = new StringBuilder(change.substring(0, change.length() - 2));
        change.append(" )");
        if (quantumInfo.size() > 0 && change.toString().equals(quantumInfo.get(quantumInfo.size() - 1)))
            return;
        quantumInfo.add(change.toString());

    }

    private void changeProcessQuantum(Process currentProcess, int processRunningTime) {
        int unChangedQuantum = currentProcess.getQuantum();
        if (currentProcess.getQuantum() == processRunningTime) {
            currentProcess.setQuantum((int) getMeanQuantum(currentProcess));
//			System.out.println("upper quantum is " +currentProcess.getQuantum());
        } else {
            int quantum = 2 * currentProcess.getQuantum();
            currentProcess.setQuantum(quantum - processRunningTime);
//			System.out.println("lower quantum is " +currentProcess.getQuantum());
        }
        addChangedQuantum(unChangedQuantum, currentProcess);

    }

    private Process changeProcess(int result, Process toAdd) {
        Process currentProcess = new Process();
        if (!isDead(toAdd)) process.add(toAdd);
        if (process.size() > 0) {
            currentProcess = process.get(result);
            process.remove(result);
        } else currentProcess = toAdd;

//		System.out.println("current process "+currentProcess.getName());
//		System.out.println("current process "+currentProcess.getName());
        return currentProcess;
    }

    public void secondPassed(Process currentProcess, int processRunningTime) throws InterruptedException {
        if (!isDead(currentProcess)) {
            int burst = currentProcess.getBurstTime();
            currentProcess.setBurstTime(burst - 1);
        }
        ++currentTime;
        for (Process i : process) {
            i.increaseWaitingTimeBy(1);
            i.increaseTurnAroundTimeBy(1);
        }

    }

    private double getMeanQuantum(Process currentProcess) {
        double result = 0;
        for (Process i : process) {
            result += i.getQuantum();
        }
        result += currentProcess.getQuantum();
        result /= process.size() + 1;
        result = Math.ceil(result * 0.1) + currentProcess.getQuantum();
        return result;
    }


    private boolean isDead(Process currentProcess) {
        if (currentProcess.getBurstTime() <= 0) {
            currentProcess.setQuantum(0);
            addChangedQuantum(0, currentProcess);
            if (excutionOrder.size() > 0 && excutionOrder.get(excutionOrder.size() - 1).equals(currentProcess.getName()))
                return true;
            excutionOrder.add(currentProcess.getName());
            return true;
        }
        return false;
    }

    public int getMinimumFactorProcess(Process currentProcess) {
        int res = -1;
        for (int i = 0; i < process.size(); ++i) {
            if (process.get(i).getAG_Factor() < currentProcess.getAG_Factor()) {
                currentProcess = process.get(i);
                res = i;
            }
        }
        return res;
    }

    public void calculateFactor(Process process) {
        int arrival = process.getArrivalTime();
        int burst = process.getBurstTime();
        int priority = process.getPriority();

        process.setAG_Factor(arrival + burst + priority);
        process.setTurnAroundTime(burst);
    }

    public static void main(String[] args) throws InterruptedException {
        int wt = 0;
        int tat = 0;

        System.out.println("AG SCHEDULER +++++++++++++++ \n");

        System.out.println("Enter The Number Of Processes:");
        Scanner myObj = new Scanner(System.in);
        int n = myObj.nextInt();

        AG tst = new AG();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter The Process " + (i + 1) + " Burst Time: ");
            int b = myObj.nextInt();
            System.out.println("Enter The Process " + (i + 1) +" Arrival Time: ");
            int a = myObj.nextInt();
            System.out.println("Enter The Process " + (i + 1) +" priority: ");
            int p = myObj.nextInt();
            System.out.println("Enter the Process " + (i + 1) +" quantum time: ");
            int t = myObj.nextInt();

            Process pro = new Process(a, b, p, t, "P" + (i+1));
            tst.arrivalQueue.add(pro);
        }

        System.out.println("\n");
        System.out.println("Quantum updates: ");
        tst.start(tst.arrivalQueue);

        for (String change : tst.quantumInfo) {
            System.out.println(change);
        }

        System.out.println("\n");
        System.out.println("Processes execution order:");
        for (String order : tst.excutionOrder) {
            System.out.println(order);
        }
        for (String order : tst.order) {
            System.out.print(order + " ");
        }

        System.out.println("\n");

        System.out.println("Process id  WT  TAT  ");
        for (int i = 0; i < n; i++) {
            System.out.println(tst.arrivalQueue.get(i).name + "\t" + tst.arrivalQueue.get(i).waitingTime + "\t" + tst.arrivalQueue.get(i).turnAroundTime + "\t");

            wt += tst.arrivalQueue.get(i).waitingTime;
            tat += tst.arrivalQueue.get(i).turnAroundTime;
        }

        System.out.println("Average Waiting Time = " + wt/(double)n);
        System.out.println("Average Turnaround Time = " + tat/(double) n);
    }

}