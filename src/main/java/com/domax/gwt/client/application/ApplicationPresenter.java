package com.domax.gwt.client.application;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.Proxy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationPresenter
    extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy> {

  public static final NestedSlot SLOT_MAIN = new NestedSlot();

  interface MyView extends View {}

  @ProxyStandard
  interface MyProxy extends Proxy<ApplicationPresenter> {}

  @Inject
  public ApplicationPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
    super(eventBus, view, proxy, RevealType.Root);
    log.debug("ApplicationPresenter instantiated");
  }
}
