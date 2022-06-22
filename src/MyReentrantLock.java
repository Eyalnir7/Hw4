import java.util.concurrent.atomic.AtomicBoolean;

public class MyReentrantLock implements Lock, AutoCloseable{

    private AtomicBoolean locked = new AtomicBoolean();
    private Thread currentThread = null;

    @Override
    public void acquire() {
        while(!locked.compareAndSet(false, true)){
            try{
                this.wait();
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        currentThread = Thread.currentThread();
    }

    @Override
    public boolean tryAcquire() {
        return locked.compareAndSet(false, true);
    }

    @Override
    public void release() {
        if(!Thread.currentThread().equals(currentThread))
            throw new IllegalReleaseAttempt();
        boolean status = locked.compareAndSet(true, false);
        if(!status)
            throw new IllegalReleaseAttempt();
        this.notifyAll();
    }

    @Override
    public void close() {
        release();
    }
}
