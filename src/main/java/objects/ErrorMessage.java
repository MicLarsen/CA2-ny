package objects;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Michael
 */
@XmlRootElement // because I want these ErrorMessages class to be converted to JSON 
public class ErrorMessage {
    
    private String errorMessage;
    private int errorCode;
    private String documentation;
    
    public ErrorMessage() {}
    
    public ErrorMessage(String errorMessage, int errorCode, String documentaton) {
        super();
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.documentation = documentaton;       
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }
    
}
