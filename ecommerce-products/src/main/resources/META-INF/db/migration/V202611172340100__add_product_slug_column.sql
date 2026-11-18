alter table ecommerce_products add column "slug" varchar(255);

create index if not exists idx_ecommerce_products_slug on ecommerce_products("slug");