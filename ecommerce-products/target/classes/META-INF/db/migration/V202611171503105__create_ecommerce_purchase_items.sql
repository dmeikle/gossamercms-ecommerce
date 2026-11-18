CREATE TABLE ecommerce_purchase_items (
    "id" UUID PRIMARY KEY,
    "purchaseId" UUID,
    "productId" UUID,
    "quantity" INTEGER,
    "unitPrice" DECIMAL,
    "totalPrice" DECIMAL,
    "currency" VARCHAR(3),
    "metadata" JSONB,
    "createdAt" TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    "updatedAt" TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT "fk_ecommerce_purchase_items_purchaseId" FOREIGN KEY ("purchaseId") REFERENCES "ecommerce_purchases" ("id"),
    CONSTRAINT "fk_ecommerce_purchase_items_productId" FOREIGN KEY ("productId") REFERENCES "ecommerce_products" ("id")
);
CREATE INDEX "idx_ecommerce_purchase_items_purchaseId" ON "ecommerce_purchase_items" ("purchaseId");
CREATE INDEX "idx_ecommerce_purchase_items_productId" ON "ecommerce_purchase_items" ("productId");
