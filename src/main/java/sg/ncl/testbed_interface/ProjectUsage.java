package sg.ncl.testbed_interface;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Getter
@Setter
public class ProjectUsage implements Serializable {

    private Integer id;
    @NotEmpty
    @DateTimeFormat(pattern = "MMM-yyyy")
    private String month;
    private int usage;
}
