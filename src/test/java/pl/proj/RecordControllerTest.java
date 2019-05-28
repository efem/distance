package pl.proj;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.proj.domain.Record;
import pl.proj.service.RecordService;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class RecordControllerTest {


    @MockBean
    private RecordService recordService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /record/1 - OK")
    public void getGetRecordById() throws Exception {
        Record mockRecord = new Record();
        doReturn(Optional.of(mockRecord)).when(recordService).findById(1L);
       // when(recordService.findById(1L)).thenReturn(Optional.of(mockRecord));

        mockMvc.perform(get("/record/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
                .andExpect(header().string(HttpHeaders.LOCATION, "/record/1"))
                .andExpect(jsonPath("$.id", is(1)));

    }
}
