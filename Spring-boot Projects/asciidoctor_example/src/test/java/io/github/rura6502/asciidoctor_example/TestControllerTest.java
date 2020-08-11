package io.github.rura6502.asciidoctor_example;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TestController.class)
@AutoConfigureRestDocs(outputDir = "src/restdoc/snippets/test")

public class TestControllerTest {
  

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void test_test1() throws Exception {
    this.mockMvc.perform(get("/test1/testValue1")).andDo(print())
            .andExpect(content().string(equalTo("test1 is : testValue1")))
            .andExpect(status().isOk())
            .andDo(document("test1", preprocessResponse(prettyPrint())))
            ;
  }

  @Test
  public void test_test2() throws Exception {

    this.mockMvc.perform(post("/test2")
                                        .content("testValue2")
                                        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo("test2 is : testValue2")))
            .andDo(document("test2", preprocessResponse(prettyPrint())))
            ;
  }
}