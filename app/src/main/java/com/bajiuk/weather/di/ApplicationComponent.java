package com.bajiuk.weather.di;

import android.content.Context;
import com.bajiuk.weather.db.StorageApi;
import com.bajiuk.weather.db.di.StorageModule;
import com.bajiuk.weather.location.LocationProvider;
import com.bajiuk.weather.location.di.LocationModule;
import dagger.Component;
import javax.inject.Singleton;
import retrofit2.Retrofit;

@Singleton @Component(modules = {
    ApplicationModule.class, RetrofitModule.class, StorageModule.class, LocationModule.class
}) public interface ApplicationComponent {
  Retrofit retrofit();

  Context context();

  StorageApi storage();

  LocationProvider location();
}
