val reader = FileReader("new.csv")
val json = Json { prettyPrint = true }
val records:Iterable<CSVRecord> = CSVFormat.DEFAULT.withHeader().parse(reader)
for(record in records) {
    println("${record["BookID"]} ${record["Chapter"]}")
    //for(i in metadata) { File("karoli/${i.first}/").mkdirs() }
    val obj = Json.decodeFromString<NewV>(
        URL("https://getbible.net/v2/karoli/${record["BookID"]}/${record["Chapter"]}.json")
            .readText()
    )
    Writer.bufferedWriter(json.encodeToString(obj), "karoli/${obj.book_name}/${obj.chapter}.json")
}