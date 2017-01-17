package com.bajiuk.weather.weather.di;

import com.bajiuk.weather.api.WeatherApi;
import com.bajiuk.weather.api.WeatherApiWrapper;
import com.bajiuk.weather.weather.WeatherPresenter;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class WeatherModule {
  @Singleton @Provides WeatherPresenter providePresenter(WeatherApiWrapper api) {
    return new WeatherPresenter(api);
  }
}
