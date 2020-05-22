package com.ages.incuitech.backend.solucaodeproblemasservice.api.tag;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.stub.TagStub;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.Tag;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.TagService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags.TagRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


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
        Tag tag = TagStub.buildTagStub(1L, "TDAH");
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
