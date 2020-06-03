package com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.MailService;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.stub.SolucionadorStub;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.stub.TagStub;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.stub.UserTagStub;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.adm.AdministradoresService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.Solucionador;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.SolucionadorMapper;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.SolucionadorService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.TagService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.solucionador.SolucionadorRepository;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags.TagSolucionadorRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SolucionadorServiceTest {

    @InjectMocks
    private SolucionadorService solucionadorService;

    @Mock
    private SolucionadorRepository repository;
    @Mock
    private TagService tagService;
    @Mock
    private TagSolucionadorRepository tagSolucionadorRepository;
    @Mock
    private MailService mailService;
    @Mock
    private AdministradoresService admService;

    @Test
    public void findAllSolucionadoresShouldReturnSolucionador() {
        // arrange
        Solucionador solucionador = SolucionadorStub.getModelStub();
        when(repository.findAll()).thenReturn(Lists.newArrayList(solucionador));
        when(tagSolucionadorRepository.findAllLinkedTags()).thenReturn(UserTagStub.getUserTagStub());

        // act
        List<SolucionadorResponse> solucionadores = solucionadorService.findAllSolucionadores();

        // assert
        Optional<SolucionadorResponse> response = solucionadores.stream()
                .filter(solucionadorResponse -> solucionadorResponse.getId().equals(solucionador.getId()))
                .findFirst();
        assertTrue(response.isPresent());
        assertEquals(response.get().getEmail(), solucionador.getEmail());
        assertEquals(response.get().getNome(), solucionador.getNome());
        assertEquals(response.get().getTelefone(), solucionador.getTelefone());
        assertEquals(response.get().getStatusCadastro(), solucionador.getStatusCadastro());
        assertFalse(response.get().getTags().isEmpty());
        Assertions.assertEquals(response.get().getTags().size(), 3);
        Assertions.assertEquals(response.get().getTags().get(0), "TDAH");
        Assertions.assertEquals(response.get().getTags().get(1), "CRINACAS");
        Assertions.assertEquals(response.get().getTags().get(2), "ESCOLA");
        verify(repository).findAll();

    }


    @Test
    public void deveSalvarSolucionadorComSuasTags() {
        // arrange
        SolucionadorRequest solucionador = SolucionadorStub.getSolucionadorRequest();
        when(repository.save(any())).thenReturn(SolucionadorMapper.mapToModel(solucionador));
        when(tagService.salvar("ONG")).thenReturn(TagStub.buildTagStub(1L, "ONG"));
        when(tagService.salvar("ESCOLA")).thenReturn(TagStub.buildTagStub(2L, "ESCOLA"));

        // act
        SolucionadorResponse solucionadorSalvo = solucionadorService.salvar(solucionador);

        // assert
        assertEquals(solucionadorSalvo.getNome(), solucionador.getNome());
        assertEquals(solucionadorSalvo.getEmail(), solucionador.getEmail());
        assertEquals(solucionadorSalvo.getTelefone(), solucionador.getTelefone());
        assertFalse(solucionadorSalvo.getTags().isEmpty());
        assertEquals(solucionadorSalvo.getTags().size(), 2);
        assertEquals(solucionadorSalvo.getTags().get(0), "ONG");
        assertEquals(solucionadorSalvo.getTags().get(1), "ESCOLA");
    }

}