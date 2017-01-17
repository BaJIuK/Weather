package com.bajiuk.weather.weather.di;

import com.bajiuk.weather.api.di.WeatherApiModule;
import com.bajiuk.weather.base.PerController;
import com.bajiuk.weather.di.ApplicationComponent;
import com.bajiuk.weather.weather.WeatherController;
import dagger.Component;

@PerController @Component(modules = {
    WeatherApiModule.class, WeatherModule.class
}, dependencies = {
    ApplicationComponent.class
}) public interface WeatherComponent {
  void inject(WeatherController view);
}
