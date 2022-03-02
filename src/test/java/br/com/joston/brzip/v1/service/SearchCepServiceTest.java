package br.com.joston.brzip.v1.service;

import br.com.joston.brzip.v1.domain.Address;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SearchCepServiceTest {

    private static SearchCepService service;

    @BeforeAll
    static void setUp(){
        service = new SearchCepService();
    }

    @ParameterizedTest
    @MethodSource("provideInvalidZipCodes")
    void expectedException(String cep) {
        assertThrows(IllegalArgumentException.class,()->service.execute(cep));
    }

    @ParameterizedTest
    @MethodSource("provideValidZipCodes")
    void expectedSuccess(String cep){
        assertNotNull(service.execute(cep));
    }

    @Test
    void cepNotFound(){
        assertEquals(new Address(),service.execute("18190001"),"Both objects should be empty!");
    }

    private static Stream<Arguments> provideInvalidZipCodes(){
        return Stream.of(
                Arguments.of(""),
                Arguments.of("1"),
                Arguments.of("12"),
                Arguments.of("123"),
                Arguments.of("1234"),
                Arguments.of("12345"),
                Arguments.of("123456"),
                Arguments.of("1234567"),
                Arguments.of("ABC"),
                Arguments.of("123abcd67d;&*()#$")
        );
    }

    private static Stream<Arguments> provideValidZipCodes(){
        return Stream.of(
                Arguments.of("69305-055"),
                Arguments.of("23914165"),
                Arguments.of("18190000")//Generic cep
        );
    }
}