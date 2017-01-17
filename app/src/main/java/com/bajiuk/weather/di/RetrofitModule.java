package com.bajiuk.weather.di;

import com.bajiuk.weather.api.WeatherApi;
import com.bajiuk.weather.api.WeatherApiWrapper;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bajiuk.weather.api.di.WeatherApiModule.BASE_URL;

/**
 * @author KoiDev
 * @email DevSteelKoi@gmail.com
 */

@Module public class RetrofitModule {

  @Singleton @Provides WeatherApi provideRetrofit(GsonConverterFactory converterFactory,
      RxJavaCallAdapterFactory adapterFactory, OkHttpClient client) {
    return new Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(adapterFactory)
        .client(client)
        .build()
        .create(WeatherApi.class);
  }

  @Singleton @Provides WeatherApiWrapper provideApiWrapper(WeatherApi api) {
    // TODO: Move app key to BUILDSCRIPT
    return new WeatherApiWrapper("cdc58a359f54c1eab3b14ad0e46e836d", api);
  }

  @Singleton @Provides GsonConverterFactory provideGsonConverterFactory() {
    return GsonConverterFactory.create();
  }

  @Singleton @Provides RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
    return RxJavaCallAdapterFactory.create();
  }

  @Singleton @Provides OkHttpClient provideOkHttpClient() {
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
  }
}