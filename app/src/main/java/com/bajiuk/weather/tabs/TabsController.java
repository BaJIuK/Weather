package com.bajiuk.weather.tabs;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.bajiuk.weather.R;
import com.bajiuk.weather.WeatherApplication;
import com.bajiuk.weather.base.ViewModelController;
import com.bajiuk.weather.dialog.EditDialogWrapper;
import com.bajiuk.weather.tabs.di.DaggerTabsComponent;
import com.bajiuk.weather.tabs.di.TabsComponent;
import com.bajiuk.weather.weather.WeatherController;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import javax.inject.Inject;

public class TabsController extends ViewModelController<TabsComponent, TabsViewModel>
    implements TabsView<TabsViewModel> {

  @Inject TabsPresenter presenter;
  @BindView(R.id.tabs) TabLayout tabLayout;
  @BindView(R.id.weather) FrameLayout weatherLayout;

  private Router weatherRouter;
  private WeatherController weatherController;
  private EditDialogWrapper editDialogWrapper;
  private TabLayout.OnTabSelectedListener onTabSelectedListener =
      new TabLayout.OnTabSelectedListener() {
        @Override public void onTabSelected(TabLayout.Tab tab) {
          showWeather(tab.getText().toString());
        }

        @Override public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override public void onTabReselected(TabLayout.Tab tab) {

        }
      };

  public TabsController() {
    initDialog();
    getComponent().inject(this);
    presenter.attach(this);
  }

  @Override
  protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    return inflater.inflate(R.layout.view_tabs, container, false);
  }

  @Override protected void onViewBound(@NonNull View view) {
    initWeatherContainer();
  }

  @Override protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    editDialogWrapper.onRestoreInstanceState(savedInstanceState);
  }

  @Override protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    editDialogWrapper.onSaveInstanceState(outState);
  }

  @Override protected void onActivityResumed(@NonNull Activity activity) {
    super.onActivityResumed(activity);
    editDialogWrapper.onResume(activity);
  }

  @Override protected TabsComponent buildComponent() {
    return DaggerTabsComponent.builder()
        .applicationComponent(WeatherApplication.getAppComponent())
        .build();
  }

  @OnClick(R.id.fab) protected void onFabClick() {
    editDialogWrapper.show(getActivity());
  }

  private void initWeatherContainer() {
    weatherRouter = getChildRouter(weatherLayout).setPopsLastView(true);
  }

  private void initDialog() {
    editDialogWrapper =
        new EditDialogWrapper(R.string.city_dialog_title, city -> presenter.addCity(city));
  }

  public void showTabs() {
    tabLayout.removeOnTabSelectedListener(onTabSelectedListener);
    tabLayout.removeAllTabs();
    TabLayout.Tab selected = null;
    for (String tab : data.getTabs()) {
      TabLayout.Tab tabItem = tabLayout.newTab().setText(tab);
      tabLayout.addTab(tabItem);
      if (tab.equals(data.getSelected())) {
        selected = tabItem;
      }
    }
    tabLayout.addOnTabSelectedListener(onTabSelectedListener);
    if (selected != null) {
      selected.select();
      showWeather(selected.getText().toString());
    }
  }

  @Override public void addTab(String name) {
    TabLayout.Tab tab = tabLayout.newTab().setText(name);
    tabLayout.addTab(tab);
    tab.select();
    data.setSelected(name);
  }

  @Override public void showWeather(String location) {
    weatherController = WeatherController.getControllerForCity(location, weatherController);
    weatherRouter.popToRoot();
    weatherRouter.setRoot(RouterTransaction.with(weatherController)
        .pushChangeHandler(new FadeChangeHandler())
        .popChangeHandler(new FadeChangeHandler()));
  }

  @Override public void showContent() {
    showTabs();
  }

  @Override protected void loadData() {
    presenter.loadData();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.detach();
  }
}
