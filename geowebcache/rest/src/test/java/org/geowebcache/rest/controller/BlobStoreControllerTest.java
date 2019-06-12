package org.geowebcache.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration()
@ContextConfiguration({
    "file:../web/src/main/webapp/WEB-INF/geowebcache-rest-context.xml",
    "file:../web/src/main/webapp/WEB-INF/geowebcache-core-context.xml"
})
public class BlobStoreControllerTest {

    @Autowired private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /** Checks correct media type for RestException response handling. GET method. */
    @Test
    public void testBlobstoresGetContentType() throws Exception {
        mockMvc.perform(
                        get("/rest/blobstores/{blobStoreName}", "xxxp4z85")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().is4xxClientError());
    }
}
