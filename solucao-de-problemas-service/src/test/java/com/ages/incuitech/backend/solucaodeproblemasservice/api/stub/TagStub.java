package com.ages.incuitech.backend.solucaodeproblemasservice.api.stub;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagStub {

	    public static Tag buildTagStub(Long id, String name) {
	        return Tag.builder()
					.id(id)
	                .nome(name)
	                .build();
	    }

	    public static List<Tag> buidListTagStup(String... nomes) {
			List<Tag> tags = new ArrayList<>();
	    	for (long i = 0; i < nomes.length; i++) {
				tags.add(buildTagStub(i + 1, nomes[(int) i]));
			}
	    	return tags;
		}
}
