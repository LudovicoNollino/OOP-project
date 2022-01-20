package it.univpm.TwitterHashtagAnalytics.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AppExceptionHandler.class)
public class ExceptionControllerUnitTest{

    @Autowired
    private MockMvc mvc;
    
    @Test
    public void InternalServerError() throws Exception {
        String exceptionParam = "dummy";

        mvc.perform(get("/exception/{exception_id}", exceptionParam)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError())
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof NullPointerException))
            .andExpect(result -> assertEquals("internal error", result.getResolvedException().getMessage()));
    }
}    