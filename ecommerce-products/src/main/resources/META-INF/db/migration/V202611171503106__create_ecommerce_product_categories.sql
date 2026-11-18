CREATE TABLE ecommerce_product_categories (
    "id" UUID PRIMARY KEY,
    "productId" UUID,
    "categoryId" UUID,
    "createdAt" TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    "updatedAt" TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT "fk_ecommerce_product_categories_productId" FOREIGN KEY ("productId") REFERENCES "ecommerce_products" ("id"),
    CONSTRAINT "fk_ecommerce_product_categories_categoryId" FOREIGN KEY ("categoryId") REFERENCES "ecommerce_categories" ("id")
);
CREATE INDEX "idx_ecommerce_product_categories_productId" ON "ecommerce_product_categories" ("productId");
CREATE INDEX "idx_ecommerce_product_categories_categoryId" ON "ecommerce_product_categories" ("categoryId");
