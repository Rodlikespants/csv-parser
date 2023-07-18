package csv_parser;

import app.App;
import models.ParkingTransaction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Metropolis instructions:
 *
 * Here are some instructions for your interview:
 * We will provide you a CSV file consisting of parking data (example: entry/exit times, total price, user ID, site, ID, etc..)
 * We will ask you to read the data into memory and answer questions about the data using the programming language of your choice
 * You can use any IDE or editor of your choice via screen share, and are free to use online resources, libraries, etc.
 * Communicate what you’re building and be open to suggestions and have fun (most important)
 * We will expect you to have a Hello World setup in the environment of your choice ready to go to parse the CSV file. That means having your favorite library/package ready to go for reading CSV files, i.e. we expect that you will already know how to use the library for CSV parsing and will need very little time, if any, looking up how to use it.
 */
public class CsvParserUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    // TODO use String constants instead
    public enum Columns {
        id("ID"),
        entryTime("entry time"),
        exitTime("exit time"),
        totalPrice("total price"),
        userId("user ID"),
        site("site");

        private String name;

        Columns(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     *
     * @param filename
     * @param headers an array of column names
     * @return
     */
    public static Iterable<CSVRecord> getRecords(String filename, String[] headers) throws IOException {
        Reader in = new FileReader(filename);
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(headers)
                .setSkipHeaderRecord(true)
                .build();

        return csvFormat.parse(in);
    }
    public static void main(String[] args) {
        String filename = "src/test/java/resources/example2.csv";
        String[] headers = Arrays.stream(Columns.values()).map(it -> it.name).toArray(String[]::new);
        Iterable<CSVRecord> records = new ArrayList<>();
        try {
            records = CsvParserUtil.getRecords(filename, headers);
        } catch (IOException e) {
            // ...
        }

        for (CSVRecord record: records) {
            LOGGER.info("Record: " + record);
            ParkingTransaction pt = new ParkingTransaction(
                    Long.parseLong(record.get(Columns.id.name)),
                    Long.parseLong(record.get(Columns.userId.name).trim()),
                    new DateTime(record.get(Columns.entryTime.name).trim()),
                    new DateTime(record.get(Columns.exitTime.name).trim()),
                    new BigDecimal(record.get(Columns.totalPrice.name).trim().replaceAll("[$]", "")),
                    record.get(Columns.site.name).trim()
            );
            LOGGER.info("Parking Transaction: " + pt);
        }
    }
}
