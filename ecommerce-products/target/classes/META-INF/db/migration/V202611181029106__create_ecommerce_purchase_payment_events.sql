CREATE TABLE ecommerce_purchase_payment_events (
    "id" UUID PRIMARY KEY,
    "paymentId" UUID,
    "provider" VARCHAR(50),
    "providerEventId" VARCHAR(255),
    "eventType" VARCHAR(150),
    "processingStatus" VARCHAR(20),
    "payload" JSONB,
    "processedAt" TIMESTAMP,
    "errorMessage" VARCHAR,
    "createdAt" TIMESTAMP DEFAULT NOW(),
    "updatedAt" TIMESTAMP DEFAULT NOW(),
    CONSTRAINT "fk_ecommerce_purchase_payment_events_paymentId" FOREIGN KEY ("paymentId") REFERENCES "ecommerce_purchase_payments" ("id")
);
CREATE INDEX "idx_ecommerce_purchase_payment_events_paymentId" ON "ecommerce_purchase_payment_events" ("paymentId");
