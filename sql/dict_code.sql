CREATE TABLE "public"."dict_code"
(
    "code_value_seq"   int4                                        NOT NULL,
    "code_type"        varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
    "code_column_name" varchar(200) COLLATE "pg_catalog"."default",
    "code_level"       varchar(10) COLLATE "pg_catalog"."default",
    "code_value"       varchar(100) COLLATE "pg_catalog"."default",
    "sort"             int8,
    "sub_code_value"   varchar(10) COLLATE "pg_catalog"."default",
    "code_name"        varchar(200) COLLATE "pg_catalog"."default",
    "code_name2"       varchar(60) COLLATE "pg_catalog"."default",
    "code_name3"       varchar(60) COLLATE "pg_catalog"."default",
    "code_remark"      varchar(200) COLLATE "pg_catalog"."default",
    "code_spelling"    varchar(100) COLLATE "pg_catalog"."default",
    "is_customize"     char(1) COLLATE "pg_catalog"."default",
    "code_status"      char(1) COLLATE "pg_catalog"."default",
    "code_leaf"        char(1) COLLATE "pg_catalog"."default",
    CONSTRAINT "code_value_pkey" PRIMARY KEY ("code_value_seq")
)
;

ALTER TABLE "public"."dict_code"
    OWNER TO "postgres";

COMMENT
ON COLUMN "public"."dict_code"."code_value_seq" IS '代码项序号';

COMMENT
ON COLUMN "public"."dict_code"."code_type" IS '代码类别编码';

COMMENT
ON COLUMN "public"."dict_code"."code_column_name" IS '代码对应表字段';

COMMENT
ON COLUMN "public"."dict_code"."code_level" IS '代码等级';

COMMENT
ON COLUMN "public"."dict_code"."code_value" IS '代码值';

COMMENT
ON COLUMN "public"."dict_code"."sort" IS '排序';

COMMENT
ON COLUMN "public"."dict_code"."sub_code_value" IS '父代码值';

COMMENT
ON COLUMN "public"."dict_code"."code_name" IS '代码名称';

COMMENT
ON COLUMN "public"."dict_code"."code_name2" IS '代码名称2';

COMMENT
ON COLUMN "public"."dict_code"."code_name3" IS '代码名称3';

COMMENT
ON COLUMN "public"."dict_code"."code_remark" IS '代码注释';

COMMENT
ON COLUMN "public"."dict_code"."code_spelling" IS '代码名称首字母';

COMMENT
ON COLUMN "public"."dict_code"."is_customize" IS '是否为自定义代码(0否，1是)';

COMMENT
ON COLUMN "public"."dict_code"."code_status" IS '代码状态(0不显示，1显示)';

COMMENT
ON COLUMN "public"."dict_code"."code_leaf" IS '是否可选(0不可，1可以选择)';

COMMENT
ON TABLE "public"."dict_code" IS '[0079]业务代码明细表';