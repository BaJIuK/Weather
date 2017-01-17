package com.bajiuk.weather.fab.di;

import com.bajiuk.weather.base.PerController;
import com.bajiuk.weather.db.Notificator;
import com.bajiuk.weather.db.StorageApi;
import com.bajiuk.weather.fab.FabPresenter;
import dagger.Module;
import dagger.Provides;

@PerController
@Module
public class FabModule {
  @PerController @Provides FabPresenter provideFabPresenter(StorageApi api, Notificator notificator) {
    return new FabPresenter(api, notificator);
  }
}
