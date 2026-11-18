CREATE TABLE ecommerce_purchases (
    "id" UUID PRIMARY KEY,
    "purchaseNumber" VARCHAR(50),
    "customerContextId" UUID,
    "status" VARCHAR(20),
    "totalAmount" DECIMAL,
    "currency" VARCHAR(3),
    "metadata" JSONB,
    "createdBy" UUID,
    "updatedBy" UUID,
    "deletedBy" UUID,
    "deletedAt" TIMESTAMP WITH TIME ZONE,
    "createdAt" TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    "updatedAt" TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT "fk_ecommerce_purchases_customerContextId" FOREIGN KEY ("customerContextId") REFERENCES "user_contexts" ("id")
);
CREATE INDEX "idx_ecommerce_purchases_customerContextId" ON "ecommerce_purchases" ("customerContextId");

