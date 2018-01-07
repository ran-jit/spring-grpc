package spring.grpc.entry;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Ranjith @ 2 Jan' 2018
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Entry implements Serializable {

    private static final long serialVersionUID = 1512113941494399022L;

    private Long id;

    private String name;

}
