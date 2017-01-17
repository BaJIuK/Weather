package com.bajiuk.weather.mvp;

import android.view.View;

public interface Presenter<T extends View> {

  void takeView(T view);

  void dropView(T view);
}
