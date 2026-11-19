CREATE TABLE ecommerce_shipping_methods (
    "id" UUID PRIMARY KEY,
    "name" VARCHAR(150),
    "description" VARCHAR,
    "methodType" VARCHAR(20),
    "price" DECIMAL,
    "currency" VARCHAR(3),
    "isActive" BOOLEAN,
    "metadata" JSONB,
    "createdAt" TIMESTAMP DEFAULT NOW(),
    "updatedAt" TIMESTAMP DEFAULT NOW()
);
