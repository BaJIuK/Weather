package com.bajiuk.weather.weather.di;

import com.bajiuk.weather.api.WeatherApiWrapper;
import com.bajiuk.weather.base.PerController;
import com.bajiuk.weather.weather.WeatherPresenter;
import dagger.Module;
import dagger.Provides;

@PerController
@Module
public class WeatherModule {
  @PerController @Provides WeatherPresenter providePresenter(WeatherApiWrapper api) {
    return new WeatherPresenter(api);
  }
}
