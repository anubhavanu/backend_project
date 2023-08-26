package com.example.fileupload.helper;


import com.example.fileupload.model.User;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVUserHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = {"Id", "password", "username"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<User> csvToUsers(InputStream is) {
        List<User> user;
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            user = new ArrayList<User>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                User user1 = new User(
                        Long.parseLong(csvRecord.get("Id")),
                        csvRecord.get("Title"),
                        csvRecord.get("Description")

                );

                user.add(user1);
            }
//


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public static ByteArrayInputStream usersToCSV(List<User> users) {
//        @Override
//        CSVFormat  format =new CSVFormat(',', Constants.DOUBLE_QUOTE_CHAR, (QuoteMode)null, (Character)null, (Character)null, false, true, "\r\n", (String)null, (Object[])null, (String[])null, false, false, false, false, false);

//        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        CSVFormat format = CSVFormat.EXCEL.withFirstRecordAsHeader().withDelimiter('|');//use to change the delimiter from , to |


        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (User u : users) {
                List<String> data = Arrays.asList(
                        String.valueOf(u.getId()),
                        u.getUsername(),
                        u.getPassword()

                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}