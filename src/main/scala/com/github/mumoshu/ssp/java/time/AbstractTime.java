package com.github.mumoshu.ssp.java.time;

public interface AbstractTime {
    //var hour: Int
    void setHour(Integer hour); // Scala の Int は Java の int にマッピングされるが、プリミティブではなくクラス扱いなので、Integerとintどっちに例えるか悩む。
    Integer getHour();

    // var minute: Int
    void setMinute(Integer minute);
    Integer getMinute();
}
