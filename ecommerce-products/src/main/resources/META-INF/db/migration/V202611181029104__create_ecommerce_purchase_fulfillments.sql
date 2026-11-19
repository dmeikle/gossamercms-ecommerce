CREATE TABLE ecommerce_purchase_fulfillments (
    "id" UUID PRIMARY KEY,
    "purchaseId" UUID,
    "shippingMethodId" UUID,
    "fulfillmentType" VARCHAR(20),
    "status" VARCHAR(30),
    "trackingNumber" VARCHAR(150),
    "trackingUrl" VARCHAR(1000),
    "carrier" VARCHAR(100),
    "pickupReference" VARCHAR(100),
    "shippedAt" TIMESTAMP,
    "deliveredAt" TIMESTAMP,
    "collectedAt" TIMESTAMP,
    "completedAt" TIMESTAMP,
    "metadata" JSONB,
    "createdAt" TIMESTAMP DEFAULT NOW(),
    "updatedAt" TIMESTAMP DEFAULT NOW(),
    CONSTRAINT "fk_ecommerce_purchase_fulfillments_purchaseId" FOREIGN KEY ("purchaseId") REFERENCES "ecommerce_purchases" ("id"),
    CONSTRAINT "fk_ecommerce_purchase_fulfillments_shippingMethodId" FOREIGN KEY ("shippingMethodId") REFERENCES "ecommerce_shipping_methods" ("id")
);
CREATE INDEX "idx_ecommerce_purchase_fulfillments_purchaseId" ON "ecommerce_purchase_fulfillments" ("purchaseId");
CREATE INDEX "idx_ecommerce_purchase_fulfillments_shippingMethodId" ON "ecommerce_purchase_fulfillments" ("shippingMethodId");
