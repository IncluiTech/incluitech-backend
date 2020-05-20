package com.ages.incuitech.backend.solucaodeproblemasservice.api.tag;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorStub;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.Solucionador;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.SolucionadorService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.Tag;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.TagService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.solucionador.SolucionadorRepository;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags.TagRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TagServiceTest {

    @InjectMocks
    private TagService tagService;

    @Mock
    private TagRepository repository;

    @Captor
    ArgumentCaptor<Tag> captor;

    @Test
    public void saveTagShouldReturnTag() {
        Tag tag = TagStub.getModelStub();
        when(repository.findByNome(any())).thenReturn(Optional.empty());

        tagService.salvar(tag.getNome());
        Tag tagSalva = capturarTagSalva();

        assertEquals(tag.getNome(), tagSalva.getNome());
    }

    private Tag capturarTagSalva() {
        ArgumentCaptor<Tag> captor = ArgumentCaptor.forClass(Tag.class);
        Mockito.verify(repository, Mockito.atLeastOnce()).save(captor.capture());
        return captor.getValue();
    }
}
