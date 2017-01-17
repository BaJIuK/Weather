package com.bajiuk.weather.api.di;

import com.bajiuk.weather.api.WeatherApi;
import com.bajiuk.weather.api.WeatherApiWrapper;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;

@Module public class WeatherApiModule {

  public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

  @Provides WeatherApi provideApi(Retrofit retrofit) {
    return retrofit.create(WeatherApi.class);
  }

  @Singleton @Provides WeatherApiWrapper provideApiWrapper(WeatherApi api) {
    // TODO: Move app key to BUILDSCRIPT
    return new WeatherApiWrapper("cdc58a359f54c1eab3b14ad0e46e836d", api);
  }
}
