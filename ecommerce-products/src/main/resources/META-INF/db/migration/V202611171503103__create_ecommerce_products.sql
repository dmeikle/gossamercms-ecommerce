CREATE TABLE ecommerce_products (
    "id" UUID PRIMARY KEY,
    "sku" VARCHAR(50),
    "name" VARCHAR(255),
    "description" VARCHAR,
    "price" DECIMAL,
    "currency" VARCHAR(3),
    "status" VARCHAR(20),
    "metadata" JSONB,
    "thumbnailUrl" VARCHAR,
    "createdBy" UUID,
    "updatedBy" UUID,
    "createdAt" TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    "updatedAt" TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

