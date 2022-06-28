import java.util.concurrent.atomic.AtomicBoolean;

public class MyReentrantLock implements Lock, AutoCloseable{

    private AtomicBoolean locked = new AtomicBoolean();
    private Thread currentThread = null;

    private int enterCounter = 0;

    @Override
    public void acquire() {
        while(!tryAcquire()){
            Thread.yield();
        }
        this.currentThread = Thread.currentThread();
        enterCounter++;
    }

    @Override
    public boolean tryAcquire() {
        if(currentThread == Thread.currentThread()){
            this.enterCounter++;
            return true;
        }
        return locked.compareAndSet(false, true);
    }

    @Override
    public void release() {
        if(Thread.currentThread() != currentThread) {
            System.out.println("lock is not yours");
            throw new IllegalReleaseAttempt();
        }
        enterCounter--;
        if(enterCounter == 0){
            boolean status = locked.compareAndSet(true, false);
            if(!status) {
                System.out.println("locked was already released");
                throw new IllegalReleaseAttempt();
            }
        }
    }

    @Override
    public void close() {
        release();
    }


//    @Override
//    public void acquire() {
//        while(!locked.compareAndSet(false, true)){
//            try{
//                this.wait();
//            }
//            catch (InterruptedException e){
//                Thread.currentThread().interrupt();
//            }
//        }
//        currentThread = Thread.currentThread();
//    }
//
//    @Override
//    public boolean tryAcquire() {
//        return locked.compareAndSet(false, true);
//    }
//
//    @Override
//    public void release() {
//        if(!Thread.currentThread().equals(currentThread))
//            throw new IllegalReleaseAttempt();
//        boolean status = locked.compareAndSet(true, false);
//        if(!status)
//            throw new IllegalReleaseAttempt();
//        this.notifyAll();
//    }
//
//    @Override
//    public void close() {
//        release();
//    }
}
