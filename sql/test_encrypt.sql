CREATE TABLE "public"."test_encrypt" (
  "id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "id_card" varchar(255) COLLATE "pg_catalog"."default",
  "address" varchar(255) COLLATE "pg_catalog"."default",
  CONSTRAINT "test_encrypt_pkey" PRIMARY KEY ("id")
)
;

COMMENT ON COLUMN "public"."test_encrypt"."id" IS 'id';