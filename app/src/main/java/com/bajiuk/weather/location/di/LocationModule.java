package com.bajiuk.weather.location.di;

import android.content.Context;
import com.bajiuk.weather.location.LocationProvider;
import com.bajiuk.weather.location.LocationProviderImpl;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class LocationModule {
  @Provides @Singleton LocationProvider provideLocationProvider(Context context) {
    return new LocationProviderImpl(context);
  }
}
