package com.bajiuk.weather.db.di;

import com.bajiuk.weather.db.Notificator;
import com.bajiuk.weather.db.StorageApi;
import com.bajiuk.weather.db.StorageApiImpl;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class StorageModule {
  @Singleton @Provides StorageApi provideStorageApi() {
    return new StorageApiImpl();
  }

  @Singleton @Provides Notificator provideNotificator() {
    return new Notificator();
  }
}
