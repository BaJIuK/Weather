package com.bajiuk.weather.weather.di;

import com.bajiuk.weather.api.di.WeatherApiModule;
import com.bajiuk.weather.di.ApplicationComponent;
import com.bajiuk.weather.di.RetrofitModule;
import com.bajiuk.weather.weather.WeatherView;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = {
    WeatherApiModule.class, WeatherModule.class, RetrofitModule.class
}, dependencies = {
    ApplicationComponent.class
}) public interface WeatherComponent {
  void inject(WeatherView view);
}
