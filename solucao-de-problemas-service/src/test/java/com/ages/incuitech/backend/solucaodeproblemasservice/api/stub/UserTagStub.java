package com.ages.incuitech.backend.solucaodeproblemasservice.api.stub;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagcliente.UserTag;
import org.assertj.core.util.Lists;

import java.util.List;

public class UserTagStub {

    public static List<UserTag> getUserTagStub() {
        return Lists.newArrayList(
                UserTag.builder().tagName("TDAH").userId(1L).build(),
                UserTag.builder().tagName("CRINACAS").userId(1L).build(),
                UserTag.builder().tagName("ESCOLA").userId(1L).build()
        );
    }
}
