package nou.hello.boot.spring5boot.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PdsComment {
    private String cno;
    private String comments;
    private String userid;
    private String regdate;
    private String pno;
    private String ref;

}
