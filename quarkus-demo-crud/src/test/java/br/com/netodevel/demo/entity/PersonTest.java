package br.com.netodevel.demo.entity;


import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class PersonTest {

    @Test
    public void testPanacheMock() {
        PanacheMock.mock(Person.class);
        Assertions.assertEquals(0, Person.count());

        Mockito.when(Person.count()).thenReturn(20L);
        Assertions.assertEquals(20, Person.count());
    }

}