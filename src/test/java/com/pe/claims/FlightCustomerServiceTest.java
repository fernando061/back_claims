package com.pe.claims;

import com.pe.claims.core.Entities.FlightCustomer;
import com.pe.claims.infraestructure.Repository.FlightCustomerRepository;
import com.pe.claims.infraestructure.Service.FlightCustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)
public class FlightCustomerServiceTest {

    @Mock
    private FlightCustomerRepository flightCustomerRepository;

    @InjectMocks
    private FlightCustomerService flightCustomerService;

    @Test
    public void testFindByFlightNumberAndCustomerDocumentNumber_Found() {
        // Crear un objeto FlightCustomer de ejemplo
        FlightCustomer flightCustomer = new FlightCustomer();
        flightCustomer.setFlightNumber("FL123");
        flightCustomer.setArrivalCity("Peru");


        Mockito.when(flightCustomerRepository.findByFlightNumberAndCustomerDocumentNumber("FL123", "DOC123"))
                .thenReturn(Optional.of(flightCustomer));

        // Llamar al método a probar
        FlightCustomer result = flightCustomerService.findByFlightNumberAndCustomerDocumentNumber("FL123", "DOC123");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("FL123", result.getFlightNumber());
        Assertions.assertEquals("Peru", result.getArrivalCity());
    }
}
