package pl.touk.excel.export.samples

import pl.touk.excel.export.getters.PropertyGetter

class CurrencyGetter extends PropertyGetter<Currency, String> {
    CurrencyGetter(String propertyName) {
        super(propertyName)
    }

    @Override
    protected String format(Currency value) {
        return value.displayName
    }
}
