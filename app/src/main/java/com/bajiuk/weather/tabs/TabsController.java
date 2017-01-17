package com.bajiuk.weather.tabs;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.bajiuk.weather.R;
import com.bajiuk.weather.WeatherApplication;
import com.bajiuk.weather.base.ButterKnifeController;
import com.bajiuk.weather.tabs.di.DaggerTabsComponent;
import com.bajiuk.weather.tabs.di.TabsComponent;
import java.util.List;
import javax.inject.Inject;

public class TabsController extends ButterKnifeController<TabsComponent> implements TabsView {

  @Inject TabsPresenter presenter;
  @BindView(R.id.tabs) TabLayout tabLayout;

  @Override
  protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    return inflater.inflate(R.layout.view_tabs, container, false);
  }

  @Override protected void onViewBound(@NonNull View view) {
    getComponent().inject(this);
    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override public void onTabSelected(TabLayout.Tab tab) {
        presenter.onTabSelected(tab.getText().toString());
      }

      @Override public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override public void onTabReselected(TabLayout.Tab tab) {

      }
    });
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

  @Override public void setData(List<String> tabs) {
    tabLayout.removeAllTabs();
    for (String tab : tabs) {
      tabLayout.addTab(tabLayout.newTab().setText(tab));
    }
  }
}
