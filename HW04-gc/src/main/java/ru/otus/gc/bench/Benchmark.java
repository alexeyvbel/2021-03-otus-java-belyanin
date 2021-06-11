package ru.otus.gc.bench;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Benchmark implements BenchmarkMBean {
    private final int loopCounter;
    private volatile int size = 0;

    public Benchmark(int loopCounter) {
        this.loopCounter = loopCounter;
    }

    void run() throws InterruptedException {

        for (int idx = 0; idx < loopCounter; idx++) {
            int local = size;
            int index = 0;
            List<Double> list = new ArrayList<>();

            for (int i = 0; i < local; i++, index++) {
                list.add(Math.random());

                if (index == loopCounter){
                    for (int j = 0; j < list.size()/2; j++) {
                        list.remove(j);
                    }
                    index=0;
                }
            }
            Thread.sleep(1); //Label_1
        }

    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        System.out.println("new size:" + size);
        this.size = size;
    }
}
