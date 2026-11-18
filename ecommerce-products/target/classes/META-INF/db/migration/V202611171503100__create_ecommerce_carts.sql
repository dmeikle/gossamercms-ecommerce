CREATE TABLE ecommerce_carts (
    "id" UUID PRIMARY KEY,
    "userContextId" UUID,
    "sessionId" VARCHAR(100),
    "status" VARCHAR(20),
    "expiresAt" TIMESTAMP WITH TIME ZONE,
    "createdBy" UUID,
    "updatedBy" UUID,
    "createdAt" TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    "updatedAt" TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT "fk_ecommerce_carts_userContextId" FOREIGN KEY ("userContextId") REFERENCES "user_contexts" ("id")
);
CREATE INDEX "idx_ecommerce_carts_userContextId" ON "ecommerce_carts" ("userContextId");