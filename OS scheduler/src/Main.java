/*
* Marwan Mohamed     20200512
* Fares Saad         20200372
* Bassem Mohamed     20200112
* Yahia Salah        20200638
* Ganna Ibrahim      20200124
* */


public class Main {
    public static void main(String[] args) {

        System.out.println("SJF SCHEDULER +++++++++++++++\n");
        SJF sjf = new SJF();
        sjf.SJFScheduler();

        System.out.println("\n");

        System.out.println("ROUND ROBIN SCHEDULER +++++++++++++++ \n");
        RoundRobin rr = new RoundRobin();
        rr.RoundRobinScheduler();

        System.out.println("\n");

        System.out.println("PRIORITY SCHEDULER +++++++++++++++ \n");
        Priority pp= new Priority();
        pp.PriorityScheduler();
    }
}