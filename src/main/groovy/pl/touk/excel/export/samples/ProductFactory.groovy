package pl.touk.excel.export.samples

import java.math.RoundingMode

class ProductFactory {
    private static List<String> types = ["hard", "soft", "undefined"]

    List<Product> createProducts() {
        Random random = Random.newInstance()
        List<Product> products = []
        for(int i in 1..10) {
            products.add(createProduct(i, random))
        }
        return products
    }

    private Product createProduct(int i, Random random) {
        new Product(name: "some name $i", description: "Lorem ipsum $i",
                productNumber: new Long(i), validTill: new Date(i * 100000000000),
                price: createPrice(random),
                type: types.getAt(i % 3))
    }

    private Price createPrice(Random random) {
        new Price(
                currency: Currency.getInstance("PLN"),
                value: new BigDecimal(random.nextDouble() * 100).setScale(2, RoundingMode.HALF_EVEN))
    }
}
