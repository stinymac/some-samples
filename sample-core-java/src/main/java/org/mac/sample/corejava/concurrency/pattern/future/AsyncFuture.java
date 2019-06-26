/*
 *          (          (
 *          )\ )  (    )\   )  )     (
 *  (  (   (()/( ))\( ((_| /( /((   ))\
 *  )\ )\   ((_))((_)\ _ )(_)|_))\ /((_)
 * ((_|(_)  _| (_))((_) ((_)__)((_|_))
 * / _/ _ \/ _` / -_|_-< / _` \ V // -_)
 * \__\___/\__,_\___/__/_\__,_|\_/ \___|
 *
 * 东隅已逝，桑榆非晚。(The time has passed,it is not too late.)
 * 虽不能至，心向往之。(Although I can't, my heart is longing for it.)
 *
 */

package org.mac.sample.corejava.concurrency.pattern.future;

/**
 *
 * @author Mac
 * @create 2018-05-25 20:48
 **/

public class AsyncFuture<T> implements Future<T>{

    private volatile T result;
    @Override
    public T get() throws InterruptedException {
        synchronized (this) {
            while (result == null) {
                this.wait();
            }
        }
        return result;
    }

    public void set (T result) {
        synchronized (this) {
           this.result = result;
           this.notifyAll();
        }
    }
}
