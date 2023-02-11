package WebCrawler

import com.opencsv.CSVWriter

class GenerateCSV {

    void createCSCInFolderDownloads(List<Product> products)
    {
        File file = new File("./Reports/products.csv")
        try {
            new File("./Reports").mkdirs()

            FileWriter outputfile = new FileWriter(file)
            CSVWriter writer = new CSVWriter(outputfile)

            writer.writeNext((String [])["titulo", "valor", "endereço", "URL"])
            products.forEach {product -> writer.writeNext((String[])[product.title, product.price, product.local, product.url])}

            writer.close()
        }
        catch (IOException e) {
            println e.message
        }
    }

}
