package pl.touk.excel.export.samples.samples
import org.springframework.context.MessageSource
import pl.touk.excel.export.WebXlsxExporter
import pl.touk.excel.export.getters.LongToDatePropertyGetter
import pl.touk.excel.export.getters.MessageFromPropertyGetter

class SampleExportController {
    ProductFactory productFactory
    MessageSource messageSource

    def simplestExport() {
        List<Product> products = productFactory.createProducts()

        def headers = ['name', 'description', 'validTill', 'productNumber', 'price.value']
        def withProperties = headers

        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

    def i18nExport() {
        List<Product> products = productFactory.createProducts()

        def headers = [message(code: 'product.name.header'),
                       message(code: 'product.description.header'),
                       message(code: 'product.validTill.header'),
                       message(code: 'product.productNumber.header'),
                       message(code: 'price.value.header')]

        def withProperties = ['name', 'description', 'validTill', 'productNumber', 'price.value']

        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

    def exportWithBuildInGetters() {
        List<Product> products = productFactory.createProducts()

        def headers = ['name', 'type', 'created at']
        def withProperties = ['name', new MessageFromPropertyGetter(messageSource, 'type'), new LongToDatePropertyGetter('creationTimestamp')]

        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

    def exportWithCustomGetter() {
        List<Product> products = productFactory.createProducts()

        def headers = ['name', 'currency', 'value']
        def withProperties = ['name', new CurrencyGetter('price.currency'), 'price.value']

        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

    def exportWithDirectManipulation() {
        List<Product> products = productFactory.createProducts()

        def withProperties = ['name', 'description', 'validTill', 'productNumber', 'price.value']

        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillRow(["aaa", "bbb", 13, new Date()], 1)
            fillRow(["ccc", "ddd", 87, new Date()], 2)
            putCellValue(3, 3, "Now I'm here")
            add(products, withProperties, 4)
            save(response.outputStream)
        }
    }
}