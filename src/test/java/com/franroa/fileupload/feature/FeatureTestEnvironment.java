package com.franroa.fileupload.feature;

import com.franroa.fileupload.resources.MyResource;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.junit.ClassRule;

public class FeatureTestEnvironment
{
    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addProvider(MultiPartFeature.class)
            .addResource(new MyResource())
            .build();

}
