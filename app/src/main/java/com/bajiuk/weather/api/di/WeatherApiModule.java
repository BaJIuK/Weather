package com.bajiuk.weather.api.di;

import com.bajiuk.weather.api.WeatherApi;
import com.bajiuk.weather.api.WeatherApiWrapper;
import com.bajiuk.weather.base.PerController;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@PerController
@Module public class WeatherApiModule {

  public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

  @Provides @PerController WeatherApi provideApi(Retrofit retrofit) {
    return retrofit.create(WeatherApi.class);
  }

  @Provides @PerController WeatherApiWrapper provideApiWrapper(WeatherApi api) {
    // TODO: Move app key to BUILDSCRIPT
    return new WeatherApiWrapper("cdc58a359f54c1eab3b14ad0e46e836d", api);
  }
}
