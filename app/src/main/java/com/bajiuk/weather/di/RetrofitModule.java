package com.bajiuk.weather.di;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bajiuk.weather.api.di.WeatherApiModule.BASE_URL;

@Module public class RetrofitModule {

  @Singleton @Provides Retrofit provideRetrofit(GsonConverterFactory converterFactory,
      RxJavaCallAdapterFactory adapterFactory, OkHttpClient client) {
    return new Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(adapterFactory)
        .client(client)
        .build();
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
