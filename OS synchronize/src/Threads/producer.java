package Threads;



public class producer extends Thread {
    buffer buf;
    int N,largest=0,numberPrimes=0;


    public producer(buffer buf,int N) {
        this.buf = buf;
        this.N=N;
    }

    static boolean isPrime(int n){

        //since 0 and 1 is not prime return false.
        if(n==1||n==0)return false;

        //Run a loop from 2 to n-1
        for(int i=2; i<n; i++){
            // if the number is divisible by i, then n is not a prime number.
            if(n%i==0)return false;
        }
        //otherwise, n is prime number.
        return true;
    }

    public void run() {

        for (int i = 1; i <= N; i++) {
            if (isPrime(i)) {
                buf.produce(i);
                numberPrimes += 1;
                if (i > largest)
                    largest = i;
            }
        }

        buf.produce(0);
        buf.produce(numberPrimes);
        buf.produce(largest);

    }

    public int getNumberPrimes(){
        return numberPrimes;
    }

    public int getLargest(){
        return largest;
    }
}


