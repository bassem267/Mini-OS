import java.util.ArrayList;
import java.util.Scanner;

public class bestFit {

    public bestFit(ArrayList<Partition> partitions, ArrayList<Process> processes) {
        System.out.println("/--------------------------------Best  Fit--------------------------------------------/");
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

        int done = pars.size();
        for (Process pro : pros) {
            Partition best = new Partition(99999999,-1);
            for (Partition par : pars) {

                if (par.totalSize >= pro.siz && !par.fragmented && par.totalSize < best.totalSize) {
                    best = par;
                }

            }

            if (best.index != -1){
                if (best.totalSize > pro.siz){
                    int rest = best.totalSize - pro.siz;
                    best.totalSize = pro.siz;
                    best.fragmented = true;
                    best.ps = pro;
                    Partition p = new Partition(rest , done);
                    pars.add(best.index +1 , p);
                    done++;
                    pro.exu = true;
                }
                else if (best.totalSize == pro.siz){
                    best.fragmented = true;
                    best.ps = pro;
                    pro.exu = true;
                }
            }
        }

        for (Partition par : pars){
            if (par.fragmented){
                System.out.println("Partition " + par.index + "(" + par.totalSize + "KB)" + " => " + "Process " + (par.ps.index + 1));
            }
            else{
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
            done++;

            for (Process pro : pros) {
                if (!pro.exu){
                    Partition best = new Partition(99999999,-1);
                    for (Partition par : pars) {

                        if (par.totalSize >= pro.siz && !par.fragmented && par.totalSize < best.totalSize) {
                            best = par;
                        }

                    }

                    if (best.index != -1){
                        if (best.totalSize > pro.siz){
                            int rest = best.totalSize - pro.siz;
                            best.totalSize = pro.siz;
                            best.fragmented = true;
                            best.ps = pro;
                            Partition p = new Partition(rest , done);
                            pars.add(best.index +1 , p);
                            done++;
                            pro.exu = true;
                        }
                        else if (best.totalSize == pro.siz){
                            best.fragmented = true;
                            best.ps = pro;
                            pro.exu = true;
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
