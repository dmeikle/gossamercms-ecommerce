CREATE TABLE ecommerce_purchase_refunds (
    "id" UUID PRIMARY KEY,
    "purchaseId" UUID,
    "paymentId" UUID,
    "providerRefundId" VARCHAR(255),
    "amount" DECIMAL,
    "currency" VARCHAR(3),
    "status" VARCHAR(30),
    "reason" VARCHAR(50),
    "description" VARCHAR,
    "provider" VARCHAR(50),
    "failureCode" VARCHAR(100),
    "failureMessage" VARCHAR,
    "refundedAt" TIMESTAMP,
    "requestedBy" UUID,
    "metadata" JSONB,
    "createdBy" UUID,
    "updatedBy" UUID,
    "createdAt" TIMESTAMP DEFAULT NOW(),
    "updatedAt" TIMESTAMP DEFAULT NOW(),
    CONSTRAINT "fk_ecommerce_purchase_refunds_purchaseId" FOREIGN KEY ("purchaseId") REFERENCES "ecommerce_purchases" ("id"),
    CONSTRAINT "fk_ecommerce_purchase_refunds_paymentId" FOREIGN KEY ("paymentId") REFERENCES "ecommerce_purchase_payments" ("id"),
    CONSTRAINT "fk_ecommerce_purchase_refunds_requestedBy" FOREIGN KEY ("requestedBy") REFERENCES "user_contexts" ("id"),
    CONSTRAINT "fk_ecommerce_purchase_refunds_createdBy" FOREIGN KEY ("createdBy") REFERENCES "user_contexts" ("id"),
    CONSTRAINT "fk_ecommerce_purchase_refunds_updatedBy" FOREIGN KEY ("updatedBy") REFERENCES "user_contexts" ("id")
);
CREATE INDEX "idx_ecommerce_purchase_refunds_purchaseId" ON "ecommerce_purchase_refunds" ("purchaseId");
CREATE INDEX "idx_ecommerce_purchase_refunds_paymentId" ON "ecommerce_purchase_refunds" ("paymentId");
CREATE INDEX "idx_ecommerce_purchase_refunds_requestedBy" ON "ecommerce_purchase_refunds" ("requestedBy");
CREATE INDEX "idx_ecommerce_purchase_refunds_createdBy" ON "ecommerce_purchase_refunds" ("createdBy");
CREATE INDEX "idx_ecommerce_purchase_refunds_updatedBy" ON "ecommerce_purchase_refunds" ("updatedBy");
