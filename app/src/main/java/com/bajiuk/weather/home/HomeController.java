package com.bajiuk.weather.home;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.BindView;
import com.bajiuk.weather.R;
import com.bajiuk.weather.base.ButterKnifeController;
import com.bajiuk.weather.fab.FabController;
import com.bajiuk.weather.tabs.TabsController;
import com.bajiuk.weather.weather.WeatherController;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;

public class HomeController extends ButterKnifeController {
  @BindView(R.id.fab) FrameLayout fabLayout;
  @BindView(R.id.weather) FrameLayout weatherLayout;
  @BindView(R.id.tab_bar) FrameLayout tabLayout;

  Router fabRouter;
  Router tabRouter;
  Router weatherRouter;

  @Override
  protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    return inflater.inflate(R.layout.view_root, container, false);
  }

  @Override protected void onViewBound(@NonNull View view) {
    fabRouter = getChildRouter(fabLayout).setPopsLastView(true);
    weatherRouter = getChildRouter(weatherLayout).setPopsLastView(true);
    tabRouter = getChildRouter(tabLayout).setPopsLastView(true);

    WeatherController weatherController = new WeatherController();
    weatherRouter.setRoot(RouterTransaction.with(weatherController)
        .pushChangeHandler(new FadeChangeHandler())
        .popChangeHandler(new FadeChangeHandler()));

    FabController fabController = new FabController();
    fabRouter.setRoot(RouterTransaction.with(fabController)
        .pushChangeHandler(new FadeChangeHandler())
        .popChangeHandler(new FadeChangeHandler()));

    TabsController tabsController = new TabsController();
    tabRouter.setRoot(RouterTransaction.with(tabsController)
        .pushChangeHandler(new FadeChangeHandler())
        .popChangeHandler(new FadeChangeHandler()));
  }

  @Override protected Object buildComponent() {
    return null;
  }
}
