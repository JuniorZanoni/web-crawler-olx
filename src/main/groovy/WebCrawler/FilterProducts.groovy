package WebCrawler

class FilterProducts {
    private List<Product> products

    FilterProducts(List<Product> products) {
        this.products = products
    }

    List<Product> removeValuesGreaterAverage() {
        Integer average = calculateAverageValue()
        List<Product> productsAverage = this.products.findAll({ product ->
            return product.getPrice() <= average
        })

        return productsAverage
    }

    private Integer calculateAverageValue() {
        Integer averageValue = 0

        this.products.forEach { product ->
            averageValue += product.getPrice()
        }

        return averageValue / products.size()
    }

}
