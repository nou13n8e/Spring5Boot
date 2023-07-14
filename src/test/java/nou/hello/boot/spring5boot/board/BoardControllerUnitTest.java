package nou.hello.boot.spring5boot.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class BoardControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("BoardController read Test")
    @Transactional
    void readBoard() throws Exception {
        mvc.perform(get("/board/list/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("BoardController view Test")
    @Transactional
    void view() throws Exception {
        mvc.perform(get("/board/view/300"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("BoardController save Test")
    @Transactional
    void write() throws Exception {
        mvc.perform(post("/board/write")
                        .param("title", "abc123")
                        .param("userid", "abc123")
                        .param("contents", "abc123")
                        .param("ipaddr", "127.0.0.1"))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }

    @Test
    @DisplayName("BoardController remove Test")
    @Transactional
    void remove() throws Exception {
        mvc.perform(get("/board/delete/1088"))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }
    @Test
    @DisplayName("BoardController find Test")
    @Transactional
    void findBoard() throws Exception {
        mvc.perform(get("/board/find/title/비가/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
