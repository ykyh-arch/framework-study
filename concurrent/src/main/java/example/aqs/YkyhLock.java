package example.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @ClassName: YkyhLock
 * @Description: 自定义锁，基于AQS框架实现
 * @Author: Uetec
 * @Date: 2020-11-13-11:48
 * @Version: 1.0
 **/
public class YkyhLock implements Lock {

    private Helper helper=new Helper();

    //非公共内部帮助类
    private class Helper extends AbstractQueuedSynchronizer {
        //获取锁
        @Override
        protected boolean tryAcquire(int arg) {//信号量
            int state=getState();
            if(state==0){//没有其他线程占有
                //利用CAS原理修改state
                if(compareAndSetState(0,arg)){
                    //设置当前线程占有资源
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            //
            }else if(getExclusiveOwnerThread()==Thread.currentThread()){
                setState(getState()+arg);
                return true;
            }
            return false;
        }
        //释放锁
        @Override
        protected boolean tryRelease(int arg) {
            int state=getState()-arg;
            boolean flag=false;
            //判断释放后是否为0
            if(state==0){
                setExclusiveOwnerThread(null);
                setState(state);
                return true;
            }
            setState(state);//存在线程安全吗？重入性的问题，当前已经独占了资源state
            return false;
        }
        public Condition newConditionObjecct(){
            return new ConditionObject();
        }
    }
    @Override
    public void lock() {
        //独占锁
        helper.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        //以独占方式获得，如果中断，中止。
        helper.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        helper.release(1);
    }

    @Override
    public Condition newCondition() {
        return helper.newConditionObjecct();
    }
}
