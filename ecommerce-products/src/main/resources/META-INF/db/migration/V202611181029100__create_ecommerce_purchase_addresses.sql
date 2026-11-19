CREATE TABLE ecommerce_purchase_addresses (
    "id" UUID PRIMARY KEY,
    "purchaseId" UUID,
    "addressType" VARCHAR(20),
    "firstName" VARCHAR(100),
    "lastName" VARCHAR(100),
    "company" VARCHAR(255),
    "addressLine1" VARCHAR(255),
    "addressLine2" VARCHAR(255),
    "city" VARCHAR(100),
    "province" VARCHAR(100),
    "postalCode" VARCHAR(20),
    "country" VARCHAR(2),
    "telephone" VARCHAR(30),
    "createdAt" TIMESTAMP DEFAULT NOW(),
    "updatedAt" TIMESTAMP DEFAULT NOW(),
    CONSTRAINT "fk_ecommerce_purchase_addresses_purchaseId" FOREIGN KEY ("purchaseId") REFERENCES "ecommerce_purchases" ("id")
);
CREATE INDEX "idx_ecommerce_purchase_addresses_purchaseId" ON "ecommerce_purchase_addresses" ("purchaseId");
