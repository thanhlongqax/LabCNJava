package org.example;

import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.routines.UrlValidator;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class Program {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please specify an URL to a file");
            return;
        }
        String fileUrl = args[0];

        UrlValidator urlValidator = new UrlValidator();

        if (!urlValidator.isValid(fileUrl)) {
            System.err.println("This is not a valid URL");
            return;
        }

        try {
            URL url = new URL(fileUrl);

            String fileName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
            File destinationFile = new File(fileName);

            FileUtils.copyURLToFile(url, destinationFile);

            System.out.println("Downloaded file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error downloading file: " + e.getMessage());
        }
    }
}

