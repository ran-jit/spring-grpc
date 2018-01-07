package spring.grpc.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import spring.grpc.entry.Entry;

/**
 * Created by Ranjith @ 2 Jan' 2018
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response implements Serializable {

    private static final long serialVersionUID = -4051403797519631170L;

    private Boolean status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Entry entry;

}
