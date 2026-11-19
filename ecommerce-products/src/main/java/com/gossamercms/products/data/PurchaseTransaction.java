package com.gossamercms.products.data;


import com.gossamercms.mvc.annotations.ModuleDbService;
import com.gossamercms.mvc.data.DataSourceManager;
import com.gossamercms.products.models.Purchase;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.function.Supplier;

@ModuleDbService
@RequiredArgsConstructor
public class PurchaseTransaction {
    private final DataSourceManager sources;

    public <T> T transaction(Supplier<T> work) {
        // Select the workflow datasource explicitly rather than a host's default transaction manager.
        var jdbc = sources.get(Purchase.META.datasourceKey()).getNamedParameterJdbcTemplate();
        var manager = new DataSourceTransactionManager(jdbc.getJdbcTemplate().getDataSource());
        var transaction = new TransactionTemplate(manager);
        transaction.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        return transaction.execute(status -> work.get());
    }
}

