package com.domax.gwt.client.application;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.sencha.gxt.widget.core.client.container.Viewport;
import javax.inject.Inject;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {

  interface Binder extends UiBinder<Widget, ApplicationView> {}

  @UiField Viewport main;

  @Inject
  public ApplicationView(Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public void setInSlot(Object slot, IsWidget content) {
    if (slot == ApplicationPresenter.SLOT_MAIN) {
      main.setWidget(content);
    } else {
      super.setInSlot(slot, content);
    }
  }
}
