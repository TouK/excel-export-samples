import pl.touk.excel.export.samples.samples.ProductFactory

// Place your Spring DSL code here
beans = {

    productFactory(ProductFactory) {
        fakerService = ref('fakerService')
    }
}
