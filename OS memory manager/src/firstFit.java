import java.util.ArrayList;
import java.util.Scanner;

public class firstFit {
    public firstFit(ArrayList<Partition> partitions, ArrayList<Process> processes) {
        System.out.println("/------------------------------First Fit--------------------------------------------/");
        ArrayList<Partition> pars = new ArrayList<>();
        ArrayList<Process> pros = new ArrayList<>();

        for (Partition partition : partitions) {
            Partition temp = new Partition(0, 0);
            temp.index = partition.index;
            temp.totalSize = partition.totalSize;
            temp.fragmented = partition.fragmented;
            pars.add(temp);
        }

        for (Process process : processes) {
            Process temp = new Process(0, 0);
            temp.siz = process.siz;
            temp.index = process.index;
            pros.add(temp);
        }

        int ha = pars.size();
        for (Process pro : pros) {
            for (int j = 0 ; j < pars.size() ; j++) {
                if (pars.get(j).totalSize > pro.siz && !pars.get(j).fragmented) {
                    int rest = pars.get(j).totalSize - pro.siz;
                    pars.get(j).totalSize = pro.siz;
                    pars.get(j).fragmented = true;
                    pars.get(j).ps = pro;
                    Partition p = new Partition(rest , ha);
                    ha++;
                    pars.add(j+1, p);
                    pro.exu = true;
                    break;
                }
                else if (pars.get(j).totalSize == pro.siz && !pars.get(j).fragmented){
                    pars.get(j).totalSize = pro.siz;
                    pars.get(j).fragmented = true;
                    pars.get(j).ps = pro;
                    pro.exu = true;
                    break;
                }
            }
        }

        for (Partition par : pars){
            if (par.fragmented){
                System.out.println("Partition " + par.index + "(" + par.totalSize + "KB)" + " => " + "Process " + (par.ps.index + 1));
            }
            else if (!par.compacted){
                System.out.println("Partition " + par.index + "(" + par.totalSize + "KB)" + " => " + "External fragment ");
            }
        }
        for (Process pro : pros){
            if (!pro.exu) {
                int i = pro.index;
                System.out.println("Process " + (i+1) + " Can't be Allocated");
            }
        }

        System.out.println("////////////////////////////////////////////////////////////////////////////////////////");

        Scanner myObj = new Scanner(System.in);
        System.out.println("Do you want to compact? 1.yes 2.no");
        int f = myObj.nextInt();
        if (f == 1){
            int compactIndex = pars.size();
            int compactSize = 0;

            for (Partition par : pars){
                if (!par.fragmented){
                    compactSize+= par.totalSize;
                    par.compacted = true;
                }
            }

            Partition comp = new Partition(compactSize, compactIndex);
            pars.add(comp);
            ha++;

            for (Process pro : pros) {
                if (!pro.exu){
                    for (int j = 0 ; j < pars.size() ; j++) {
                        if (pars.get(j).totalSize > pro.siz && !pars.get(j).fragmented) {
                            int rest = pars.get(j).totalSize - pro.siz;
                            pars.get(j).totalSize = pro.siz;
                            pars.get(j).fragmented = true;
                            pars.get(j).ps = pro;
                            Partition p = new Partition(rest , ha);
                            ha++;
                            pars.add(j+1, p);
                            pro.exu = true;
                            break;
                        }
                        else if (pars.get(j).totalSize == pro.siz && !pars.get(j).fragmented){
                            pars.get(j).totalSize = pro.siz;
                            pars.get(j).fragmented = true;
                            pars.get(j).ps = pro;
                            pro.exu = true;
                            break;
                        }
                    }
                }
            }

            for (Partition par : pars){
                if (par.fragmented) {
                    System.out.println("Partition " + par.index + "(" + par.totalSize + "KB)" + " => " + "Process " + (par.ps.index + 1));
                }
                else if (!par.compacted){
                    System.out.println("Partition " + par.index + "(" + par.totalSize + "KB)" + " => " + "External fragment ");
                }
            }
            for (Process pro : pros){
                if (!pro.exu) {
                    int i = pro.index;
                    System.out.println("Process " + (i+1) + " Can't be Allocated");
                }
            }
        }

        System.out.println("////////////////////////////////////////////////////////////////////////////////////////");

    }
}
