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
 * @create 2018-05-25 20:44
 **/

public class FutureExecutor {
    public<T> Future<T> execute (FutureRunnable<T> task) {
        AsyncFuture<T> aysncFuture = new AsyncFuture<T>();
        new Thread(() -> {

                T result = task.call();
                aysncFuture.set(result);

        }).start();
        return aysncFuture;
    }

    public<T> void execute (FutureRunnable<T> task,Callback<T> call) {

        new Thread(() -> {
                T result = task.call();
                call.accept(result);
            }
        ).start();

    }
}
