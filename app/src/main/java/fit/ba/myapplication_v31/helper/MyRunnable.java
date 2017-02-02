package fit.ba.myapplication_v31.helper;

import java.io.Serializable;

/**
 * Created by Developer on 31.01.2017..
 */

public interface MyRunnable<T> extends Serializable {


    void run(T t);
}
