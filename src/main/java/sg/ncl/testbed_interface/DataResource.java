package sg.ncl.testbed_interface;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by jng on 20/10/16.
 */
@Getter
@Setter
public class DataResource implements Serializable {

    private Long id;
    private String uri;
    private String maliciousFlag;
    private boolean malicious;
    private boolean scanned;


    // override the malicious flag method
    // to display the list of dropdown selection for admin to update malicious status of a data resource
    // Note: values must be equivalent to those option values defined in the dropdown list under admin_data_resources_update.html
    public String getMaliciousFlag() {
        if (malicious && scanned) {
            maliciousFlag = "malicious";
        } else if (!malicious && scanned) {
            maliciousFlag = "clean";
        } else {
            maliciousFlag = "not scanned";
        }
        return maliciousFlag;
    }

    // override the malicious flag method
    // to update the respective malicious and scanned flag
    // Note: values must be equivalent to those define in the dropdown list under admin_data_resources_update.html
    public void setMaliciousFlag(String flag) {
        switch (flag) {
            case "malicious":
                malicious = true;
                scanned = true;
                break;
            case "clean":
                malicious = false;
                scanned = true;
                break;
            case "not scanned":
            default:
                malicious = false;
                scanned = false;
                break;
        }
    }

}
