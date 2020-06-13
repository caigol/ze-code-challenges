package br.com.caique.zecodechallenges.service;

import br.com.caique.exception.ResourceNotFoundException;
import br.com.caique.model.Partner;
import br.com.caique.repository.PartnerRepository;
import br.com.caique.services.PartnerServices;
import br.com.caique.zecodechallenges.utils.PartnerUtils;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PartnerServiceTest {

    public static final Long ONE = 1L;
    public static final Long TWO = 2L;
    public static final Long THREE = 3L;

    private static final GeometryFactory GF = new GeometryFactory();

    @Mock
    private PartnerRepository partnerRepository;

    @InjectMocks
    private PartnerServices partnerServices;

    @Test
    public void contextLoads() {
        assertThat(partnerServices).isNotNull();
    }

    /**
     * GIVEN a new partner
     * WHEN {@link PartnerServices#create(Partner)} is called
     * THEN should the new partner should be created
     *
     * @throws Exception
     */
    @Test
    public void shouldCreateAPartner() {
        //given
        Partner partner = PartnerUtils.buildPartner(null);
        given(partnerRepository.save(eq(partner))).willReturn(PartnerUtils.buildPartner(ONE));

        //when
        Partner resultPartner = partnerServices.create(partner);

        //then
        assertThat(resultPartner).isNotNull();
        assertThat(resultPartner).isEqualTo(PartnerUtils.buildPartner(ONE));
        verify(partnerRepository, times(1)).save(partner);
    }

    /**
     * GIVEN a partner with id 1
     * WHEN {@link PartnerServices#findById(Long)} is called
     * THEN the partner with id 1 is returned correctly
     *
     * @throws Exception
     */
    @Test
    public void shouldFindById() {
        //given
        Partner partnerOne = PartnerUtils.buildPartner(ONE);
        given(partnerRepository.findById(ONE)).willReturn(Optional.of(partnerOne));

        //when
        Partner resultPartner = partnerServices.findById(ONE);

        //then
        assertThat(resultPartner).isNotNull();
        assertThat(resultPartner).isEqualTo(partnerOne);
    }

    /**
     * GIVEN no partner with id 2
     * WHEN {@link PartnerServices#findById(Long)} is called
     * THEN a {@link ResourceNotFoundException} should be thrown
     *
     * @throws Exception
     */
    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowsAnExceptionWhenThePartnerIsNotFoundById() {
        partnerServices.findById(TWO);
    }

    /**
     * GIVEN a list of partner
     * WHEN {@link PartnerServices#findAll()} is called
     * THEN all partner is correctly returned
     *
     * @throws Exception
     */
    @Test
    public void shouldFindAll() {
        //given
        Partner partnerOne = PartnerUtils.buildPartner(ONE);
        Partner partnerTwo = PartnerUtils.buildPartner(TWO);
        Partner partnerThree = PartnerUtils.buildPartner(THREE);
        given(partnerRepository.findAll()).willReturn(Arrays.asList(partnerOne, partnerTwo, partnerThree));

        //when
        List<Partner> partners = partnerServices.findAll();

        //then
        assertThat(partners).hasSize(THREE.intValue());
        assertThat(partners).containsExactly(partnerOne, partnerTwo, partnerThree);
    }

    /**
     * GIVEN a list of partner
     * WHEN {@link PartnerServices#search(Point)} is called with the coordinate {@link Coordinate(15, 10)}
     * THEN the closer partner should be returned
     *
     */
    @Test
    public void shouldFindTheCloserPartner() {
        //given
        Partner partnerOne = PartnerUtils.buildPartner(ONE);
        given(partnerRepository.findAll()).willReturn(Arrays.asList(partnerOne));

        //when
        Partner partner =  partnerServices
                .search(GF.createPoint(new Coordinate(15, 10)));

        //then
        assertThat(partner).isNotNull();
    }

    /**
     * GIVEN a list of partner
     * WHEN {@link PartnerServices#search(Point)} is called with the coordinate {@link Coordinate(150, 10)}
     * THEN no close partner should ne found
     *
     */
    @Test(expected = ResourceNotFoundException.class)
    public void shouldNotFindAClosePartner() {
        //given
        Partner partnerOne = PartnerUtils.buildPartner(ONE);
        given(partnerRepository.findAll()).willReturn(Arrays.asList(partnerOne));

        //when
        Partner partner =  partnerServices
                .search(GF.createPoint(new Coordinate(150, 10)));
    }
}
