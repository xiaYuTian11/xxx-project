# xxx-project
springboot 搭建的开发快速开发框架
```sql
CREATE TABLE "public"."sys_log" (
  "id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "account" varchar(100) COLLATE "pg_catalog"."default",
  "ip" varchar(20) COLLATE "pg_catalog"."default",
  "url" varchar(255) COLLATE "pg_catalog"."default",
  "method" varchar(255) COLLATE "pg_catalog"."default",
  "params" text COLLATE "pg_catalog"."default",
  "response" text COLLATE "pg_catalog"."default",
  "result_code" varchar(10) COLLATE "pg_catalog"."default",
  "oper_type" varchar(255) COLLATE "pg_catalog"."default",
  "oper_desc" text COLLATE "pg_catalog"."default",
  "module" varchar(255) COLLATE "pg_catalog"."default",
  "exception" text COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  CONSTRAINT "sys_log_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."sys_log" 
  OWNER TO "postgres";

COMMENT ON COLUMN "public"."sys_log"."id" IS '主键';

COMMENT ON COLUMN "public"."sys_log"."account" IS '用户账号';

COMMENT ON COLUMN "public"."sys_log"."ip" IS 'ip地址';

COMMENT ON COLUMN "public"."sys_log"."url" IS '访问URL';

COMMENT ON COLUMN "public"."sys_log"."method" IS '请求方法';

COMMENT ON COLUMN "public"."sys_log"."params" IS '请求参数';

COMMENT ON COLUMN "public"."sys_log"."response" IS '返回值';

COMMENT ON COLUMN "public"."sys_log"."result_code" IS '返回cde';

COMMENT ON COLUMN "public"."sys_log"."oper_type" IS '操作类型';

COMMENT ON COLUMN "public"."sys_log"."oper_desc" IS '操作描述';

COMMENT ON COLUMN "public"."sys_log"."module" IS '操作模块';

COMMENT ON COLUMN "public"."sys_log"."exception" IS '异常信息';

COMMENT ON COLUMN "public"."sys_log"."create_time" IS '创建时间';

COMMENT ON TABLE "public"."sys_log" IS '日志记录';
```