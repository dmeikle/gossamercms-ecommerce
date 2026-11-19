CREATE TABLE ecommerce_purchase_taxes (
    "id" UUID PRIMARY KEY,
    "purchaseId" UUID,
    "purchaseItemId" UUID,
    "taxName" VARCHAR(100),
    "taxType" VARCHAR(50),
    "jurisdiction" VARCHAR(150),
    "jurisdictionCode" VARCHAR(50),
    "taxRate" DECIMAL,
    "taxableAmount" DECIMAL,
    "taxAmount" DECIMAL,
    "provider" VARCHAR(50),
    "providerReference" VARCHAR(255),
    "createdAt" TIMESTAMP DEFAULT NOW(),
    "updatedAt" TIMESTAMP DEFAULT NOW(),
    CONSTRAINT "fk_ecommerce_purchase_taxes_purchaseId" FOREIGN KEY ("purchaseId") REFERENCES "ecommerce_purchases" ("id"),
    CONSTRAINT "fk_ecommerce_purchase_taxes_purchaseItemId" FOREIGN KEY ("purchaseItemId") REFERENCES "ecommerce_purchase_items" ("id")
);
CREATE INDEX "idx_ecommerce_purchase_taxes_purchaseId" ON "ecommerce_purchase_taxes" ("purchaseId");
CREATE INDEX "idx_ecommerce_purchase_taxes_purchaseItemId" ON "ecommerce_purchase_taxes" ("purchaseItemId");
