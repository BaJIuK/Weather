package com.bajiuk.weather.api.di;

import com.bajiuk.weather.api.WeatherApi;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @author KoiDev
 * @email DevSteelKoi@gmail.com
 */
@Module public class WeatherApiModule {

  public static final String BASE_URL = "api.openweathermap.org/data/2.5/";

   @Provides WeatherApi provideApi(Retrofit retrofit) {
     return retrofit.create(WeatherApi.class);
  }


}
