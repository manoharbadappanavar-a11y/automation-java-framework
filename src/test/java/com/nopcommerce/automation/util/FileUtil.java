package com.nopcommerce.automation.util;

import java.io.File;

import com.poiji.option.PoijiOptions;
import com.poiji.option.PoijiOptions.PoijiOptionsBuilder;

/**
 * Simple utility class for managing our test Excel file and Poiji options.
 * Using a singleton here so we only load the file once for all tests.
 */
public class FileUtil {

    // Singleton instance – only one FileUtil needed
    public static FileUtil instance = null;

    // Reference to the Excel file with test data
    public File dataFile;

    // Poiji configuration for reading Excel sheets
    public PoijiOptions options;

    // Private constructor prevents creating multiple instances
    private FileUtil() {
        // Load the Excel file from resources folder
        // ClassLoader makes sure it works both in IDE and when packaged as JAR
        dataFile = new File(getClass().getClassLoader().getResource("testNopCommerceData.xlsx").getFile());

        // Setup Poiji options
        // preferNullOverDefault: empty cells become null
        // ignoreWhitespaces: trims leading/trailing spaces in cells
        options = PoijiOptionsBuilder.settings().preferNullOverDefault(true).ignoreWhitespaces(true).build();
    }

    // Returns the single instance of FileUtil
    // Lazy initialization – only creates instance when first called
    public static FileUtil getInstance() {
        if (instance == null) {
            instance = new FileUtil();
        }
        return instance;
    }

    // Getter for the Excel file
    public File getFile() {
        return dataFile;
    }

    // Getter for Poiji options
    public PoijiOptions getPoijiOptions() {
        return options;
    }
}
