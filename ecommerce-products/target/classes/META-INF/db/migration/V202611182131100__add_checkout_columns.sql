ALTER TABLE ecommerce_purchase_items DROP COLUMN subtotal;
ALTER TABLE ecommerce_purchase_items DROP COLUMN "discountTotal";
ALTER TABLE ecommerce_purchase_items DROP COLUMN "shippingTotal";
ALTER TABLE ecommerce_purchase_items DROP COLUMN "taxTotal";

ALTER TABLE ecommerce_purchases ADD COLUMN subtotal Decimal;
ALTER TABLE ecommerce_purchases ADD COLUMN "discountTotal" Decimal;
ALTER TABLE ecommerce_purchases ADD COLUMN "shippingTotal" Decimal;
ALTER TABLE ecommerce_purchases ADD COLUMN "taxTotal" Decimal;

