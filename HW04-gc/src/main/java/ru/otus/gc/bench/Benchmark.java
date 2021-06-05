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
        List<Double> list = new ArrayList<>();

        for (int idx = 0; ; idx++) {

                list.add(Math.random());

            if (idx == loopCounter){
                for (int i = 0; i < list.size()/2; i++) {
                    list.remove(i);
                }
                idx=0;
            }
            Thread.sleep(1000); //Label_1
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
