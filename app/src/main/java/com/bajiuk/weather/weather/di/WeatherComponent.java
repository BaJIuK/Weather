package com.bajiuk.weather.weather.di;

import com.bajiuk.weather.di.ApplicationComponent;
import com.bajiuk.weather.di.RetrofitModule;
import com.bajiuk.weather.weather.WeatherView;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
    RetrofitModule.class, WeatherModule.class
}, dependencies = {
    ApplicationComponent.class
}) public interface WeatherComponent {
  void inject(WeatherView view);
}
