CREATE TABLE ecommerce_purchase_fulfillment_items (
    "id" UUID PRIMARY KEY,
    "fulfillmentId" UUID,
    "purchaseItemId" UUID,
    "quantity" DECIMAL,
    "createdAt" TIMESTAMP DEFAULT NOW(),
    "updatedAt" TIMESTAMP DEFAULT NOW(),
    CONSTRAINT "fk_ecommerce_purchase_fulfillment_items_fulfillmentId" FOREIGN KEY ("fulfillmentId") REFERENCES "ecommerce_purchase_fulfillments" ("id"),
    CONSTRAINT "fk_ecommerce_purchase_fulfillment_items_purchaseItemId" FOREIGN KEY ("purchaseItemId") REFERENCES "ecommerce_purchase_items" ("id")
);
CREATE INDEX "idx_ecommerce_purchase_fulfillment_items_fulfillmentId" ON "ecommerce_purchase_fulfillment_items" ("fulfillmentId");
CREATE INDEX "idx_ecommerce_purchase_fulfillment_items_purchaseItemId" ON "ecommerce_purchase_fulfillment_items" ("purchaseItemId");
