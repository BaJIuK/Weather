package com.bajiuk.weather.tabs.di;

import com.bajiuk.weather.base.PerController;
import com.bajiuk.weather.di.ApplicationComponent;
import com.bajiuk.weather.tabs.TabsController;
import dagger.Component;

@PerController @Component(modules = {
    TabsModule.class
}, dependencies = {
    ApplicationComponent.class
})
public interface TabsComponent {
  void inject(TabsController controller);
}
