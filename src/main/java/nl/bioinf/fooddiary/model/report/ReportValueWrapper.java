package nl.bioinf.fooddiary.model.report;

import nl.bioinf.fooddiary.model.product.*;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Hans Zijlstra
 * A class that wrapps all the different product data together.
 * With this information a report can be created holding product information,
 * user information and nutrient values.
 */
public class ReportValueWrapper {
    private ProductEntry productEntry;
    private ProductGroup productGroup;
    private ProductDescription productDescription;
    private ProductMeasurement productMeasurement;
    private ProductInfoExtra productInfoExtra;
    private double nutrientValue;
    private static int na = -9999;

    public ReportValueWrapper(ProductEntry productEntry, ProductGroup productGroup, ProductDescription productDescription, ProductMeasurement productMeasurement, ProductInfoExtra productInfoExtra, double nutrientValue) {
        this.productEntry = productEntry;
        this.productGroup = productGroup;
        this.productDescription = productDescription;
        this.productMeasurement = productMeasurement;
        this.productInfoExtra = productInfoExtra;
        this.nutrientValue = nutrientValue;
    }

    public ProductEntry getProductEntry() {
        return productEntry;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public ProductMeasurement getProductMeasurement() {
        return productMeasurement;
    }

    public ProductInfoExtra getProductInfoExtra() {
        return productInfoExtra;
    }

    public double getNutrientValue() {
        return nutrientValue;
    }

    /**
     * Method that collects all the diary reports of users in a certain project.
     * For all food products in the user his food diary the nutrient values are collected
     * additional information containing user information and product information are too noted
     * and written to a single string seperated by commas representing one diary entry
     * @param userDiaryReports List of of reportValueWrappers
     * @return List<String>
     *
     */
    public static List<String> collectDiaryReports(List<ReportValueWrapper> userDiaryReports) {
        List<String> diaryReport = new ArrayList<>();
        StringJoiner productInfo = new StringJoiner(",");

        for (int i = 0; i < userDiaryReports.size(); i++) {
            ReportValueWrapper reportValues = userDiaryReports.get(i);
            double calculatedNutrientValue = roundValue(multiplyNutrientValueByQuantity(reportValues));

            int numberOfNutrientValues = 133;
            if (i > 0 && i % numberOfNutrientValues == 0) {
                diaryReport.add(productInfo.toString());
                productInfo = new StringJoiner(",");
                productInfo
                        .add(Integer.toString(reportValues.productEntry.getUserId()))
                        .add(Integer.toString(reportValues.getProductGroup().getGroupCode()))
                        .add(reportValues.getProductGroup().getGroupCodeDescription())
                        .add(Integer.toString(reportValues.productEntry.getProductId()))
                        .add(reportValues.getProductDescription().getDescriptionDutch())
                        .add(reportValues.getProductDescription().getDescriptionEnglish())
                        .add(reportValues.getProductDescription().getSynonymous())
                        .add(reportValues.productEntry.getMealtime())
                        .add(Double.toString(reportValues.productEntry.getQuantity()))
                        .add(reportValues.productEntry.getUnit())
                        .add(reportValues.productEntry.getDate())
                        .add(reportValues.productEntry.getTime())
                        .add(Double.toString(calculatedNutrientValue));

            } else if (i == 0) {
                productInfo = new StringJoiner(",");
                productInfo.add(createHeader());
                productInfo
                        .add(Integer.toString(reportValues.productEntry.getUserId()))
                        .add(Integer.toString(reportValues.getProductGroup().getGroupCode()))
                        .add(reportValues.getProductGroup().getGroupCodeDescription())
                        .add(Integer.toString(reportValues.productEntry.getProductId()))
                        .add(reportValues.getProductDescription().getDescriptionDutch())
                        .add(reportValues.getProductDescription().getDescriptionEnglish())
                        .add(reportValues.getProductDescription().getSynonymous())
                        .add(reportValues.productEntry.getMealtime())
                        .add(Double.toString(reportValues.productEntry.getQuantity()))
                        .add(reportValues.productEntry.getUnit())
                        .add(reportValues.productEntry.getDate())
                        .add(reportValues.productEntry.getTime())
                        .add(Double.toString(calculatedNutrientValue));

            } else if (i == userDiaryReports.size() - 1) {
                diaryReport.add(productInfo.toString());
            }
            else {
                productInfo.add(Double.toString(calculatedNutrientValue));
            }
        }
        return diaryReport;
    }

    /**
     * Method that creates a report in csv format. Takes a list with strings as parameter
     * that can be written to a csv fileuses the timestamp of creation as title.
     */
    public static void createDiaryReportCsv(List<String> diaryReport) throws IOException {
        String fileName = new SimpleDateFormat("yyyy-MM-dd-HHmm'.csv'").format(new Date());
        File file = new File("diary_reports/" + fileName);
        FileUtils.writeLines(file, "UTF-8", diaryReport);

    }

    /**
     * Method that rounds a nutrient value up based on one decimal
     * if the value is equal to -9999 indicating a nutrient has not been meassured for
     * -9999 otherwise the rounded double is returned
     * @param nutrientValue
     * @return double
     */
    public static double roundValue(double nutrientValue) {
        DecimalFormat df = new DecimalFormat("#.#", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setRoundingMode(RoundingMode.CEILING);
        if (nutrientValue == na) {
            return na;
        } else
            return Double.parseDouble(df.format(nutrientValue));
    }

    /**
     * Method that calculates the  total nutrient value for a consumed product based on the nutrient value for the given product
     * and the quantity a person has taken of that product
     * @param reportValues
     * @return double
     */
    private static double multiplyNutrientValueByQuantity(ReportValueWrapper reportValues) {
        if (reportValues.getNutrientValue() != na) {
            return (reportValues.getNutrientValue() / reportValues.productMeasurement.getMeasurementQuantity()) * reportValues.getProductEntry().getQuantity();
        } else
            return na;

    }
    /**
     * Creates a header string for the different categories in the CSV file,
     * each word seperated by comma represents a column in the csv file
     * @return String
     */
    private static  String createHeader() {
        return "USER_ID,GROUP_COUDE,GROUP_DESCRIPTION,PRODUCT_ID,DESCRIPTION_DUTCH,DESCRIPTION_ENGLISH,SYNONYMOUS,MEALTIME,QUANTITY,MEASUREMENT_UNIT,DATE,TIME,ENERCJ,ENERCC,PROT,PROTPL,PROTAN,NT,CHO,SUGAR,STARCH,POLYL,FIBT,ALC,WATER,OA,FAT,FACID,FASAT,FAMSCIS,FAPU,FAPUN3,FAPUN6,FATRS,F4:0,F6:0,F8:0,F10:0,F11:0,F12:0,F13:0,F14:0,F15:0,F16:0,F17:0,F18:0,F19:0,F20:0,F21:0,F22:0,F23:0,F24:0,F25:0,F26:0,FASATXR,F10:1CIS,F12:1CIS,F14:1CIS,F16:1CIS,F18:1CIS,F20:1CIS,F22:1CIS,F24:1CIS,FAMSCXR,F18:2CN6,F18:2CN9,F18:2CT,F18:2TC,F18:2R,F18:3CN3,F18:3CN6,F18:4CN3,F20:2CN6,F20:3CN9,F20:3CN6,F20:3CN3,F20:4CN6,F20:4CN3,F20:5CN3,F21:5CN3,F22:2CN6,F22:2CN3,F22:3CN3,F22:4CN6,F22:5CN6,F22:5CN3,F22:6CN3,F24:2CN6,FAPUXR,F10:1TRS,F12:1TRS,F14:1TRS,F16:1TRS,F18:1TRS,F18:2TTN6,F18:3TTTN3,F20:1TRS,F20:2TT,F22:1TRS,F24:1TRS,FAMSTXR,FAUN,CHORL,NA,K,CA,P,MG,FE,HAEM,NHAEM,CU,SE,ZN,ID,ASH,VITA_RAE,VITA_RE,RETOL,CARTBTOT,CARTA,LUTN,ZEA,CRYPXB,LYCPN,VITD,CHOCALOH,CHOCAL,VITE,TOCPHA,TOCPHB,TOCPHG,TOCPHD,VITK,VITK1,VITK2,THIA,RIBF,VITB6,VITB12,NIA,FOL,FOLFD,FOLAC,VITC\n";

    }
}

