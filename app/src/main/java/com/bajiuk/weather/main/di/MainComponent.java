package com.bajiuk.weather.main.di;

import com.bajiuk.weather.main.MainView;
import dagger.Component;
import javax.inject.Singleton;

@Component @Singleton public interface MainComponent {
  void inject(MainView view);
}