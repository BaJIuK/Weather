package com.bajiuk.weather.tabs.di;

import com.bajiuk.weather.base.PerController;
import com.bajiuk.weather.db.StorageApi;
import com.bajiuk.weather.tabs.TabsPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Valentin on 18.01.2017.
 */

@Module public class TabsModule {
  @PerController @Provides TabsPresenter provideTabsPresenter(StorageApi storageApi) {
    return new TabsPresenter(storageApi);
  }
}
