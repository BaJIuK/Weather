package com.bajiuk.weather.base;

public interface MvpView<M extends MvpViewModel> {
  void setData(M data);
  void showContent();
}
