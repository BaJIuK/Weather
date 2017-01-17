package com.bajiuk.weather.di;

import android.content.Context;
import dagger.Component;
import javax.inject.Singleton;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {
    ApplicationModule.class, RetrofitModule.class
}) public interface ApplicationComponent {
  Retrofit retrofit();
  Context context();
}
