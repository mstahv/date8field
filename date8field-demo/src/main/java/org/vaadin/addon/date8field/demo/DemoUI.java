package org.vaadin.addon.date8field.demo;

import org.vaadin.addon.date8field.LocalDateTimeField;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.time.LocalDateTime;

@Title("Date8Field Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.addon.date8field.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        // Initialize our new UI component
        final LocalDateTimeField component = new LocalDateTimeField();
        
        component.setValue(LocalDateTime.now());
        
        component.addValueChangeListener(e->{
            LocalDateTime value = component.getValue();
            Notification.show(value.toString());
        });

        setContent(new CssLayout(component));

    }

}
