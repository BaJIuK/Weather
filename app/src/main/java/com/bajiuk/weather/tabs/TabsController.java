package com.bajiuk.weather.tabs;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.bajiuk.weather.R;
import com.bajiuk.weather.WeatherApplication;
import com.bajiuk.weather.base.ButterKnifeController;
import com.bajiuk.weather.dialog.EditDialogWrapper;
import com.bajiuk.weather.tabs.di.DaggerTabsComponent;
import com.bajiuk.weather.tabs.di.TabsComponent;
import com.bajiuk.weather.weather.WeatherController;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import java.util.List;
import javax.inject.Inject;

public class TabsController extends ButterKnifeController<TabsComponent> implements TabsView {

  @Inject TabsPresenter presenter;
  @BindView(R.id.tabs) TabLayout tabLayout;
  @BindView(R.id.weather) FrameLayout weatherLayout;

  private Router weatherRouter;
  private EditDialogWrapper editDialogWrapper;

  public TabsController() {
    initDialog();
    getComponent().inject(this);
  }

  @Override
  protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    return inflater.inflate(R.layout.view_tabs, container, false);
  }

  @Override protected void onViewBound(@NonNull View view) {
    initWeatherContainer();
    initTabs();
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

  @Override protected void onAttach(@NonNull View view) {
    super.onAttach(view);
    presenter.attach(this);
  }

  @Override protected void onDetach(@NonNull View view) {
    presenter.detach();
    super.onDetach(view);
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
    showWeather(null);
  }

  private void initTabs() {
    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override public void onTabSelected(TabLayout.Tab tab) {
        showWeather(tab.getText().toString());
      }

      @Override public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override public void onTabReselected(TabLayout.Tab tab) {

      }
    });
  }

  private void initDialog() {
    editDialogWrapper =
        new EditDialogWrapper(R.string.city_dialog_title, city -> presenter.addCity(city));
  }

  @Override public void setTabs(List<String> tabs) {
    tabLayout.removeAllTabs();
    for (String tab : tabs) {
      tabLayout.addTab(tabLayout.newTab().setText(tab));
    }
  }

  @Override public void addTab(String name) {
    TabLayout.Tab tab = tabLayout.newTab().setText(name);
    tabLayout.addTab(tab);
    tab.select();
  }

  @Override public void showWeather(String location) {
    WeatherController weatherController = new WeatherController(location);
    weatherRouter.setRoot(RouterTransaction.with(weatherController)
        .pushChangeHandler(new FadeChangeHandler())
        .popChangeHandler(new FadeChangeHandler()));
  }
}
