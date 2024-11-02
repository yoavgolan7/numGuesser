public class Timer extends Thread {
    private boolean running = true;
    private double secondsWaited = 0;

    public Timer() {
        super();
    }

    @Override
    public void run() {
        super.run();
        while (running) {
            try {
                Thread.sleep(100);
                secondsWaited+=0.1;
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }
    public void countDown(double seconds, boolean[] done) {
        this.secondsWaited = 0;
        this.start();
        while (this.secondsWaited < seconds) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
        done[0] = true;
        this.interrupt();
    }


    public double getSecondsWaited() {
        return this.secondsWaited;
    }

}