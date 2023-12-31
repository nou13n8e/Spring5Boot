package nou.hello.boot.spring5boot.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
    private String mno;
    private String userid;
    private String passwd;
    private String name;
    private String secret;
    private String email;
    private String zipcode;
    private String addr1;
    private String addr2;
    private String phone;
    private String regdate;
}
