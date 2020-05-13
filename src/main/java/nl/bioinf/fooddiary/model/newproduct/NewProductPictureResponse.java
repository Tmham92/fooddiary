package nl.bioinf.fooddiary.model.newproduct;

/**
 * @Author Tobias
 */

public class NewProductPictureResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public NewProductPictureResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
}
