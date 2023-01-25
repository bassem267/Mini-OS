public class Process {
    public int arrivalTime;
    public int burstTime;

    public int burst2;
    public String name;
    public int waitingTime;

    public int turnaroundTime;

    public boolean flag=true;

    public int priority;
    public int finishTime;
    public int finished;
    public int quantum;
    private int id;
    public int turnAroundTime;
    private int constBurstTime;
    private int AG_Factor;


    public Process() {

    }


    public int getId() {
        return id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public String getName() {
        return name;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public int getConstBurstTime() {
        return constBurstTime;
    }

    public int getQuantum() {
        return quantum;
    }

    public int getAG_Factor() {
        return AG_Factor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = Math.max(burstTime, 0);
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = Math.max(waitingTime, 0);
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public void setConstBurstTime(int constBurstTime) {
        this.constBurstTime = constBurstTime;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public void setAG_Factor(int AG_Factor) {
        this.AG_Factor = AG_Factor;
    }

    public void increaseWaitingTimeBy(int val) {
        this.setWaitingTime(val + this.getWaitingTime());
    }

    public void increaseTurnAroundTimeBy(int val) {
        this.setTurnAroundTime(val + this.getTurnAroundTime());
    }

    @Override
    public String toString() {
        return "Process{" +
                "name='" + name + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", burstTime=" + burstTime +
                ", waitingTime=" + waitingTime +
                ", TurnaroundTime=" + turnAroundTime +
                ", finishTime=" + finishTime +
                '}';
    }
    public Process(int a, int b, int p){
        this.arrivalTime = a;
        this.priority = p;
        this.burstTime = b;
    }


    public Process(int a, int b , String n){
        arrivalTime=a;
        burstTime=b;
        burst2=b;
        name=n;
    }


    public Process (int a, int b, int p, int t, String n) {
        arrivalTime=a;
        burstTime=b;
        priority=p;
        quantum=t;
        name=n;
    }
}
