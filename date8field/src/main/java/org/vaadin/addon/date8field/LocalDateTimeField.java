package org.vaadin.addon.date8field;

import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.DateField;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * A Vaadin field implementation for LocalDateTime type (introduced in Java 8).
 */
public class LocalDateTimeField extends CustomField<LocalDateTime> {

    private final DateField legacyDateField = new DateField();

    @Override
    protected void setInternalValue(LocalDateTime newValue) {
        super.setInternalValue(newValue);
        if (newValue == null) {
            legacyDateField.setValue(null);
        } else {
            Instant instant = newValue.atZone(ZoneId.systemDefault()).toInstant();
            legacyDateField.setValue(Date.from(instant));
        }
    }
    
     /**
     * Sets the resolution for UI.
     * 
     * The default resolution is {@link Resolution#SECOND}.
     * 
     * @param resolution
     *            the resolution to set.
     */
    public void setResolution(Resolution resolution) {
        legacyDateField.setResolution(resolution);
    }

    @Override
    protected Component initContent() {
        legacyDateField.addValueChangeListener(e -> {
            Date value = legacyDateField.getValue();
            if (value == null) {
                setValue(null);
            } else {
                setValue(LocalDateTime.ofInstant(value.toInstant(), ZoneId.systemDefault()));
            }
        });
        legacyDateField.setImmediate(true);
        legacyDateField.setResolution(Resolution.SECOND);
        return legacyDateField;
    }

    @Override
    public Class<? extends LocalDateTime> getType() {
        return LocalDateTime.class;
    }

}
