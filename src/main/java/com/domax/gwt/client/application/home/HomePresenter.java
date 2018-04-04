package com.domax.gwt.client.application.home;

import com.domax.gwt.client.api.HomeService;
import com.domax.gwt.client.application.ApplicationPresenter;
import com.domax.gwt.client.place.NameTokens;
import com.domax.gwt.shared.HomeInfo;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import lombok.extern.slf4j.Slf4j;

import static com.domax.gwt.client.api.CallbackConsumer.consume;

@Slf4j
public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> {

    interface MyView extends View {
        void setHomeInfo(HomeInfo homeInfo);
    }

    @ProxyStandard
    @NameToken(NameTokens.home)
    interface MyProxy extends ProxyPlace<HomePresenter> {}

    private final HomeService homeService;

    @Inject
    public HomePresenter(EventBus eventBus, MyView view, MyProxy proxy, HomeService homeService) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
        this.homeService = homeService;
        log.debug("HomePresenter instantiated");
    }

    @Override
    protected void onBind() {
        super.onBind();
        homeService.getLines(consume(v -> v.ifPresent(i -> getView().setHomeInfo(i))));
    }
}
