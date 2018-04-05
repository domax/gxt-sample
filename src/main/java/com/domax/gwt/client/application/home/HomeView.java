package com.domax.gwt.client.application.home;

import com.domax.gwt.shared.HomeInfo;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.sencha.gxt.core.client.resources.ThemeStyles;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

public class HomeView extends ViewImpl implements HomePresenter.MyView {

    interface Binder extends UiBinder<Widget, HomeView> {}

    @UiField(provided = true) ThemeStyles.Styles themeStyle = ThemeStyles.get().style();
    @UiField HTML lines;
    @UiField Label timestamp;

    @Inject
    public HomeView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setHomeInfo(HomeInfo homeInfo) {
        final SafeHtmlBuilder builder = new SafeHtmlBuilder();
        Optional.ofNullable(homeInfo.getLines())
                .orElseGet(Collections::emptyList)
                .forEach(line -> builder
                        .appendHtmlConstant("<p>")
                        .appendEscaped(line)
                        .appendHtmlConstant("</p>"));
        lines.setHTML(builder.toSafeHtml());

        timestamp.setText(Optional
                .ofNullable(homeInfo.getTimestamp())
                .map(Date::toString)
                .orElse("No timestamp"));
    }
}
