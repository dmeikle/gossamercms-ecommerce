CREATE TABLE ecommerce_categories (
    "id" UUID PRIMARY KEY,
    "name" VARCHAR(100),
    "slug" VARCHAR(100),
    "description" VARCHAR,
    "parentId" UUID,
    "status" VARCHAR(20),
    "createdBy" UUID,
    "updatedBy" UUID,
    "createdAt" TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    "updatedAt" TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT "fk_ecommerce_categories_parentId" FOREIGN KEY ("parentId") REFERENCES "ecommerce_categories" ("id")
);
CREATE INDEX "idx_ecommerce_categories_parentId" ON "ecommerce_categories" ("parentId");

