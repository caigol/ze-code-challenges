package br.com.caique.zecodechallenges.controller;

import br.com.caique.controller.PartnerController;
import br.com.caique.exception.ResourceNotFoundException;
import br.com.caique.model.Partner;
import br.com.caique.services.PartnerServices;
import br.com.caique.zecodechallenges.utils.PartnerUtils;
import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PartnerController.class)
public class PartnerControllerTest {

    public static final int ONCE = 1;
    public static final long ONE = 1L;
    public static final long TWO = 2L;
    public static final long THREE = 3L;
    public static final int FIFTEEN = 15;
    public static final int TEN = 10;

    private static final GeometryFactory GF = new GeometryFactory();

    @MockBean
    private PartnerServices partnerServices;

    @Autowired
    private PartnerController partnerController;

    private MockMvc mockMvc;

    private JacksonTester<Partner> partnerMapper;

    @Before
    public void before() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JtsModule());

        mockMvc = MockMvcBuilders
                .standaloneSetup(partnerController)
                .setMessageConverters(jacksonMessageConverter())
                .build();

        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void contextLoads() {
        assertThat(partnerController).isNotNull();
    }

    /**
     * GIVEN a new partner
     * WHEN send a post request to created the partner
     * THEN should the request be successfully executed
     *  AND the data is received correctly
     *
     * @throws Exception
     */
    @Test
    public void shouldCreateAPartner() throws Exception {
        //given
        Partner partner = PartnerUtils.buildPartner(null);

        //when
        ResultActions result = mockMvc
                .perform(post("/partner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(partnerMapper.write(partner).getJson()));

        //then
        result.andExpect(status().isOk());

        verify(partnerServices, times(ONCE)).create(partner);
    }

    /**
     * GIVEN a partner with id 1
     * WHEN send a get request to find the partner with id 1
     * THEN should the request be successfully executed
     *  AND the data is returned correctly
     *
     * @throws Exception
     */
    @Test
    public void shouldFindPartnerById() throws Exception {
        //given
        Partner partner = PartnerUtils.buildPartner(ONE);
        given(partnerServices.findById(ONE)).willReturn(partner);

        //when
        ResultActions result = mockMvc
                .perform(get("/partner/" + ONE));

        //then
        result.andExpect(status().isOk());

        Partner resultPartner = partnerMapper
                .parseObject(result.andReturn().getResponse().getContentAsString());

        assertThat(resultPartner).isEqualTo(partner);
    }

    /**
     * GIVEN no partner with id 2
     * WHEN send a get request to find the partner with id 2
     * THEN the HttpStatus returned should 404 - Not Found
     *
     * @throws Exception
     */
    @Test
    public void shouldThrowsAnExceptionWhenThePartnerIsNotFoundById() throws Exception {
        //given
        given(partnerServices.findById(TWO)).willThrow(new ResourceNotFoundException("No records found this ID"));

        //when
        ResultActions result = mockMvc
                .perform(get("/partner/" + TWO));

        //then
        result
                .andExpect(status().isNotFound());
    }

    /**
     * GIVEN a list of partner
     * WHEN send a get request to find all
     * THEN should the request be successfully executed
     *  AND all partners is returned correctly
     *
     * @throws Exception
     */
    @Test
    public void shouldFindAll() throws Exception {
        //given
        Partner partnerOne = PartnerUtils.buildPartner(ONE);
        Partner partnerTwo = PartnerUtils.buildPartner(TWO);
        Partner partnerThree = PartnerUtils.buildPartner(THREE);
        given(partnerServices.findAll()).willReturn(Arrays.asList(partnerOne, partnerTwo, partnerThree));

        //when
        ResultActions result = mockMvc
                .perform(get("/partner"));

        //then
        result.andExpect(status().isOk());
    }

    /**
     * GIVEN a list of partner
     * WHEN send a get request to find with the longitude=15 and latitude=10
     * THEN the HttpStatus returned should 404 - Not Found
     *
     * @throws Exception
     */
    @Test
    public void shouldFindTheCloserPartner() throws Exception {
        //given
        given(partnerServices.search(GF.createPoint(new Coordinate(FIFTEEN, TEN)))).willReturn(PartnerUtils.buildPartner(ONE));

        //when
        ResultActions result = mockMvc
                .perform(get(String.format("/partner/lng/%s/lat/%s/", FIFTEEN, TEN)));

        //then
        result
                .andExpect(status().isOk());
    }

    /**
     * GIVEN a list of partner
     * WHEN send a get request to find with the longitude=15 and latitude=10
     * THEN no close partner should ne found
     *
     * @throws Exception
     */
    @Test
    public void shouldNotFindAClosePartner() throws Exception {
        //given
        given(partnerServices.search(GF.createPoint(new Coordinate(FIFTEEN, TEN)))).willThrow(ResourceNotFoundException.class);

        //when
        ResultActions result = mockMvc
                .perform(get(String.format("/partner/lng/%s/lat/%s/", FIFTEEN, TEN)));

        //then
        result
                .andExpect(status().isNotFound());
    }

    private MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JtsModule());

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        return converter;
    }
}