ALTER TABLE ecommerce_purchase_items ADD COLUMN subtotal Decimal;
ALTER TABLE ecommerce_purchase_items ADD COLUMN "discountTotal" Decimal;
ALTER TABLE ecommerce_purchase_items ADD COLUMN "shippingTotal" Decimal;
ALTER TABLE ecommerce_purchase_items ADD COLUMN "taxTotal" Decimal;

