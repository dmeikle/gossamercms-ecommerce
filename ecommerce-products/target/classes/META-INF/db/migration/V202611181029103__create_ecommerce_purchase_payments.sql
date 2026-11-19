CREATE TABLE ecommerce_purchase_payments (
    "id" UUID PRIMARY KEY,
    "purchaseId" UUID,
    "provider" VARCHAR(50),
    "paymentMethod" VARCHAR(50),
    "providerPaymentId" VARCHAR(255),
    "providerCustomerId" VARCHAR(255),
    "amount" DECIMAL,
    "currency" VARCHAR(3),
    "status" VARCHAR(30),
    "failureCode" VARCHAR(100),
    "failureMessage" VARCHAR,
    "paidAt" TIMESTAMP,
    "idempotencyKey" VARCHAR(255),
    "metadata" JSONB,
    "createdAt" TIMESTAMP DEFAULT NOW(),
    "updatedAt" TIMESTAMP DEFAULT NOW(),
    CONSTRAINT "fk_ecommerce_purchase_payments_purchaseId" FOREIGN KEY ("purchaseId") REFERENCES "ecommerce_purchases" ("id")
);
CREATE INDEX "idx_ecommerce_purchase_payments_purchaseId" ON "ecommerce_purchase_payments" ("purchaseId");
