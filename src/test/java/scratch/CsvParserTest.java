package scratch;

import csv_parser.CsvParserUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CsvParserTest {
    public Map<String, String> AUTHOR_BOOK_MAP = Map.of(
            "Dan Simmons", "Hyperion",
            "Douglas Adams", "The Hitchhiker's Guide to the Galaxy"
    );
    String[] HEADERS = { "author", "title"};
    @Test
    public void givenCSVFile_whenRead_thenContentsAsExpected() throws IOException {
        Reader in = new FileReader("src/test/java/resources/example1.csv");

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        Iterable<CSVRecord> records = csvFormat.parse(in);

        for (CSVRecord record : records) {
            String author = record.get("author");
            String title = record.get("title");
            assertEquals(AUTHOR_BOOK_MAP.get(author), title);
        }
    }

    @Test
    public void csvParserUtilCanParseContents() throws IOException {
        String filename = "src/test/java/resources/example2.csv";
        String[] headers = Arrays.stream(CsvParserUtil.Columns.values()).map(it -> it.name()).toArray(String[]::new);
        Iterable<CSVRecord> records = CsvParserUtil.getRecords(filename, headers);
        List<CSVRecord> recordList = StreamSupport.stream(records.spliterator(), false).toList();
        assertEquals(1, recordList.size());
        CSVRecord record = recordList.stream().findFirst().orElse(null);
        assertEquals("Brooklyn", record.get(CsvParserUtil.Columns.site.name()).trim());
    }
}
