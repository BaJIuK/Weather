package com.bajiuk.weather.db;

import rx.Observable;
import rx.subjects.BehaviorSubject;

public class Notificator {
  public static final String CURRENT = "CURRENT";

  private BehaviorSubject<String> addSubject = BehaviorSubject.create(CURRENT);
  private BehaviorSubject<String> visibleSubject = BehaviorSubject.create(CURRENT);

  public void addLocation(String location) {
    addSubject.onNext(location);
    visibleSubject.onNext(location);
  }

  public void setVisible(String location) {
    visibleSubject.onNext(location);
  }

  public Observable<String> getAddNotifier() {
    return addSubject;
  }

  public Observable<String> getVisibleNotifier() {
    return visibleSubject;
  }
}
