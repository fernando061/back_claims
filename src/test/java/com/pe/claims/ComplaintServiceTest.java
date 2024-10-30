package com.pe.claims;

import com.pe.claims.core.Entities.Complaint;
import com.pe.claims.infraestructure.Repository.ComplaintRepository;
import com.pe.claims.infraestructure.Service.ComplaintService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
public class ComplaintServiceTest {

    @Mock
    private ComplaintRepository complaintRepository;

    @InjectMocks
    private ComplaintService complaintService;

    @Test
    public void testExistsByClaimCode() {
        Mockito.when(complaintRepository.existsByClaimCode("CLM-CPI5626K2Q")).thenReturn(true);
        boolean result = complaintService.existsByClaimCode("CLM-CPI5626K2Q");
        Assertions.assertTrue(result);
    }

    @Test
    public void testNotExistsByClaimCode() {
        Mockito.when(complaintRepository.existsByClaimCode("CLM-CPI5626K2j")).thenReturn(false);
        boolean result = complaintService.existsByClaimCode("CLM-CPI5626K2j");
        Assertions.assertFalse(result);
    }

    @Test
    public void testComplaintSave() {
        Complaint complaint = new Complaint();
        complaint.setClaimCode("CLM-XYZ123");
        complaint.setDescription("This is a test complaint");

        complaintService.ComplaintSave(complaint);

        verify(complaintRepository, times(1)).save(complaint);
    }


}
