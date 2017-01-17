package com.bajiuk.weather.base;

public interface MvpPresenter<T extends MvpView> {

  void attach(T view);

  void detach();
}
