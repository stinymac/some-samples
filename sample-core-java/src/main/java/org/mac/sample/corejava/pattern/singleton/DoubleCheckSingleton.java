 /*
  *      (             |"|           !!!       #   ___                             o
  *      _)_          _|_|_       `  _ _  '    #  <_*_>             ,,,         ` /_\ '       __MMM__
  *     (o o)         (o o)      -  (OXO)  -   #  (o o)            (o o)       - (o o) -       (o o)
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo--8---(_)--Ooo----ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  *
  * 虽不能至,心向往之。(Although I can't, my heart is longing for it.)
  *
  *
  *       ___        |
  *      /_\ `*      |.===.         ,,,,,
  *     (o o)        {}o o{}       /(o o)\
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  */
package org.mac.sample.corejava.pattern.singleton;

/**
 *
 * @author Mac
 * @create 2018-05-21 15:11
 **/

public class DoubleCheckSingleton {
    // 使用volatile 阻止重排序优化而造成的返回的实例对象使用时的NullPointException
    private static  volatile DoubleCheckSingleton INSTANCE ;

    private DoubleCheckSingleton(){

    }

    public  static DoubleCheckSingleton getInstance(){
        if (null == INSTANCE) {

            synchronized (DoubleCheckSingleton.class) {

                if (null == INSTANCE) {
                    INSTANCE = new DoubleCheckSingleton();
                }
            }
        }
        return INSTANCE;
    }
}