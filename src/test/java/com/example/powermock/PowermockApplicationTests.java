package com.example.powermock;

import lombok.val;
import org.apache.commons.io.IOUtils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@SpringBootTest // Indicates that the class is a Spring Boot test class
@RunWith(PowerMockRunner.class) // Specifies the test runner to be used
@PrepareForTest(IOUtils.class) // Prepares the specified class for static mocking
public class PowermockApplicationTests {

    @Test
    public void contextLoads() throws Exception {
        // Mocks the static methods of the IOUtils class
        PowerMockito.mockStatic(IOUtils.class);

        // Defines behavior for the mocked static method call
        Mockito.when(IOUtils.toString(Mockito.any(URI.class), Mockito.any(Charset.class))).thenReturn("test");

        // Retrieves the content of a fake file and assigns it to 'actualValue'
        val actualValue = IOUtils.toString(new ClassPathResource("fakeFile").getURI(), StandardCharsets.UTF_8);

        // Asserts that the retrieved content matches the expected value "test"
        Assert.assertEquals(actualValue, "test");

        // Verifies that the specified static method was called
        PowerMockito.verifyStatic(IOUtils.class);
        IOUtils.toString(Mockito.any(URI.class), Mockito.any(Charset.class));
    }
}
