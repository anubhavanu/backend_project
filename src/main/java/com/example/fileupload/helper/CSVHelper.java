package com.example.fileupload.helper;

import com.example.fileupload.model.Tutorial;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHelper extends Exception{
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Tutorial> csvToTutorials(InputStream is)  {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Tutorial> tutorials = new ArrayList<Tutorial>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();


            for (CSVRecord csvRecord : csvRecords) {
                if (csvRecord.get("Id").isEmpty()||csvRecord.get("Title").isEmpty()||csvRecord.get("Description").isEmpty()||csvRecord.get("Published").isEmpty())
                {
                   throw  new MyException("validation failed");
                }

                Tutorial tutorial = new Tutorial(

                        Long.parseLong(csvRecord.get("Id")),
                        csvRecord.get("Title"),
                        csvRecord.get("Description"),
                        Boolean.parseBoolean(csvRecord.get("Published"))
                );

                tutorials.add(tutorial);
            }

            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        } catch (MyException e) {
            throw new RuntimeException(e.getMessage());
        }
    }



    public static ByteArrayInputStream tutorialsToCSV(List<Tutorial> TutorialList) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (Tutorial developerTutorial : TutorialList) {
                List<String> data = Arrays.asList(
                        String.valueOf(developerTutorial.getId()),
                        developerTutorial.getTitle(),
                        developerTutorial.getDescription(),
                        String.valueOf(developerTutorial.isPublished())
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
