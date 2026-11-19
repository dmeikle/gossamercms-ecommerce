package com.gossamercms.products.data;

import com.gossamercms.mvc.data.DataSourceAdapter;
import com.gossamercms.mvc.data.DataSourceManager;
import com.gossamercms.products.models.PurchasePayment;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PurchasePaymentsDbServiceTest {
    @Test
    void updatesOnlyPaymentCompletionFields() {
        DataSourceManager manager = mock(DataSourceManager.class);
        DataSourceAdapter adapter = mock(DataSourceAdapter.class);
        NamedParameterJdbcTemplate jdbc = mock(NamedParameterJdbcTemplate.class);
        when(manager.get(PurchasePayment.META.datasourceKey())).thenReturn(adapter);
        when(adapter.getNamedParameterJdbcTemplate()).thenReturn(jdbc);
        when(jdbc.update(any(String.class), any(MapSqlParameterSource.class))).thenReturn(1);
        UUID id = UUID.randomUUID();

        new PurchasePaymentsDbService(manager).completePayment(id, "pi_123", "cus_123");

        ArgumentCaptor<String> sql = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<MapSqlParameterSource> values = ArgumentCaptor.forClass(MapSqlParameterSource.class);
        verify(jdbc).update(sql.capture(), values.capture());
        assertFalse(sql.getValue().contains("\"providerCheckoutId\""));
        assertFalse(sql.getValue().contains("\"idempotencyKey\""));
        assertEquals(id, values.getValue().getValue("id"));
        assertEquals("pi_123", values.getValue().getValue("providerPaymentId"));
        assertEquals("cus_123", values.getValue().getValue("providerCustomerId"));
    }
}
