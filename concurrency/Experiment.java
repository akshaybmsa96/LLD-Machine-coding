package concurrency;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Experiment {

    public static void main(String[] args) throws InterruptedException {

//        Thread t1 =  new Thread();
//
//        for(int i = 0; i< 50; i++){
//            t1 = new Thread(new Task());
//            t1.start();
//        }
//
//        t1.join();
//
//        System.out.println("Always at last");


//        int cores = Runtime.getRuntime().availableProcessors();
//        System.out.println("Cores : "+cores);
//        ExecutorService service = Executors.newFixedThreadPool(cores);
//
//        System.out.println("start Code");
//
//        for(int i = 0; i< 50; i++){
//            service.execute(new Task());
//        }


        System.out.println("Running Thread" +  Thread.currentThread().getName());

        Queue<Emp> employees = new PriorityQueue<>(Comparator.comparingDouble(e -> e.salary));

        employees.add(new Emp(1, 200.0));
        employees.add(new Emp(10, 100.0));
        employees.add(new Emp(5, 1000.0));
        employees.add(new Emp(2, 20.0));


        System.out.println(employees);

        employees.poll();

        System.out.println(employees);

        employees.poll();

        System.out.println(employees);

        employees.poll();

        System.out.println(employees);

        employees.poll();

        System.out.println(employees);

    }
}


class Emp {
    int empId;
    Double salary;

    @Override
    public String toString() {
        return "Emp{" +
                "empId=" + empId +
                ", salary=" + salary +
                '}';
    }

    public Emp(int empId, Double salary) {
        this.empId = empId;
        this.salary = salary;
    }
}

class Task implements  Runnable{

    @Override
    public void run() {
        System.out.println("Running Thread" +  Thread.currentThread().getName());
    }
}
