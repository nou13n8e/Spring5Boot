package nou.hello.boot.spring5boot.pds;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PdsControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("PdsController upload Test")
    void upload() throws Exception {
        // 1 저장할 경로 지정
        String fpath = "C:/Users/dat/Downloads/cry.png";
        FileInputStream fis=new FileInputStream(fpath);

        // 2 MockMultipartFile(폼, 파일명, MIME, 파일)
        MockMultipartFile attach=new MockMultipartFile(
                "attach", "cry.png", "image/png", fis);

        mvc.perform(multipart("/pds/write").file(attach)
                        .param("title", "오전 9시 45분, 비가 오지 않습니다.")
                        .param("userid", "abc123")
                        .param("contents", "오전 9시 45분, 비가 오지 않습니다.")
                        .param("ipaddr", "127.0.0.1"))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }

    @Test
    @DisplayName("PdsController download Test")
    void download() throws Exception {
        mvc.perform(get("/pds/down/8"))
                .andExpect(status().is(200))
                .andDo(print());
    }

    @Test
    @DisplayName("PdsController view Test")
    void view() throws Exception {
        mvc.perform(get("/pds/view/8"))
                .andExpect(status().is(200))
                .andDo(print());
    }
}
