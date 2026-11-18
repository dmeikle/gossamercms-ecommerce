CREATE TABLE ecommerce_cart_items (
    "id" UUID PRIMARY KEY,
    "cartId" UUID,
    "productId" UUID,
    "quantity" INTEGER,
    "createdAt" TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    "updatedAt" TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT "fk_ecommerce_cart_items_cartId" FOREIGN KEY ("cartId") REFERENCES "ecommerce_carts" ("id"),
    CONSTRAINT "fk_ecommerce_cart_items_productId" FOREIGN KEY ("productId") REFERENCES "ecommerce_products" ("id")
);
CREATE INDEX "idx_ecommerce_cart_items_cartId" ON "ecommerce_cart_items" ("cartId");
CREATE INDEX "idx_ecommerce_cart_items_productId" ON "ecommerce_cart_items" ("productId");
