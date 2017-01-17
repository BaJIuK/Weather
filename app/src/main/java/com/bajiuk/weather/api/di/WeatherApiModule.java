package com.bajiuk.weather.api.di;

import com.bajiuk.weather.api.WeatherApi;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @author KoiDev
 * @email DevSteelKoi@gmail.com
 */
@Module
public class WeatherApiModule {
  @Provides WeatherApi provideApi() {
    // TODO
    return null;
  }
}
