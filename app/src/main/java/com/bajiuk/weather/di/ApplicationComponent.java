package com.bajiuk.weather.di;

import android.content.Context;
import com.bajiuk.weather.db.StorageApi;
import com.bajiuk.weather.db.di.StorageModule;
import dagger.Component;
import javax.inject.Singleton;
import retrofit2.Retrofit;

@Singleton @Component(modules = {
    ApplicationModule.class, RetrofitModule.class, StorageModule.class
}) public interface ApplicationComponent {
  Retrofit retrofit();

  Context context();

  StorageApi storage();
}
