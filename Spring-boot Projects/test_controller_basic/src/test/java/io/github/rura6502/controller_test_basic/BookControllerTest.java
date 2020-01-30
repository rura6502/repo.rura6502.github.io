package io.github.rura6502.controller_test_basic;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTest {

  private MockMvc mockMvc;

  @Autowired
  private BookController bCtrl;

  @Autowired
  ObjectMapper objMapper;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(bCtrl).build();
  }


  @Test
  public void create_Book() throws Exception {

    Book book = new Book(0L, "name", "author", 0);

    mockMvc.perform(post("/book")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objMapper.writeValueAsString(book)))
                                .andDo(print())
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.name").value("name"))
                  .andReturn()
                  
                  ;
                  
    
  }

}
