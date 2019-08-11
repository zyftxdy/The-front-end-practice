--------------------------------------------
-- Export file for user STORE             --
-- Created by 12443 on 2019/6/5, 20:14:18 --
--------------------------------------------

set define off
spool listStructure.log

prompt
prompt Creating table USERS
prompt ====================
prompt
create table STORE.USERS
(
  userid      VARCHAR2(10) not null,
  username    VARCHAR2(30),
  password    VARCHAR2(50),
  email       VARCHAR2(30),
  telephone   VARCHAR2(40),
  question    VARCHAR2(100),
  answer      VARCHAR2(100),
  status      VARCHAR2(10) default '正常',
  create_date DATE,
  update_date DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.USERS
  add constraint PK_USER_USERID primary key (USERID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.USERS
  add constraint UQ_USER_USERNAME unique (USERNAME)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ADDRESS
prompt ======================
prompt
create table STORE.ADDRESS
(
  addressid     VARCHAR2(10) not null,
  userid        VARCHAR2(10),
  addressname   VARCHAR2(10),
  province      VARCHAR2(20),
  city          VARCHAR2(30),
  addressdatils VARCHAR2(100),
  telephone     VARCHAR2(30),
  create_date   DATE,
  update_date   DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.ADDRESS
  add constraint PK_ADDRESS_ADDRESSID primary key (ADDRESSID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.ADDRESS
  add constraint FK_ADDRESS_USERID foreign key (USERID)
  references STORE.USERS (USERID);

prompt
prompt Creating table AUTHORITYS
prompt =========================
prompt
create table STORE.AUTHORITYS
(
  id          NUMBER(10) not null,
  adminname   VARCHAR2(10),
  adminpwd    VARCHAR2(20),
  authority   NUMBER(5),
  create_date DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.AUTHORITYS
  add constraint PK_ID primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table FIRSTTYPES
prompt =========================
prompt
create table STORE.FIRSTTYPES
(
  firsttypeid   VARCHAR2(10) not null,
  firsttypename VARCHAR2(20),
  create_date   DATE,
  update_date   DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.FIRSTTYPES
  add constraint PK_FIRSTTYPES_FIRSTTYPEID primary key (FIRSTTYPEID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.FIRSTTYPES
  add constraint UQ_FIRSTTYPES_FIRSTTYPENME unique (FIRSTTYPENAME)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table SECONDTYPES
prompt ==========================
prompt
create table STORE.SECONDTYPES
(
  secondtypeid   VARCHAR2(10) not null,
  secondtypename VARCHAR2(20),
  firsttypeid    VARCHAR2(10),
  create_date    DATE,
  update_date    DATE,
  picture        VARCHAR2(100)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.SECONDTYPES
  add constraint PK_SCEONDTYPES_SECONDTYPEID primary key (SECONDTYPEID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.SECONDTYPES
  add constraint UQ_SCEONDTYPES_SECONDTYPENAME unique (SECONDTYPENAME)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.SECONDTYPES
  add constraint FK_SCEONDTYPES_FIRSTTYPEID foreign key (FIRSTTYPEID)
  references STORE.FIRSTTYPES (FIRSTTYPEID);

prompt
prompt Creating table PRODUCTS
prompt =======================
prompt
create table STORE.PRODUCTS
(
  productid     VARCHAR2(10) not null,
  firsttypeid   VARCHAR2(10),
  secondtypeid  VARCHAR2(10),
  productname   VARCHAR2(200),
  productdesc   VARCHAR2(500),
  picture       VARCHAR2(50),
  productstatus VARCHAR2(20) default '在售',
  create_date   DATE,
  update_date   DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.PRODUCTS
  add constraint PK_PRODUCTS_PRODUCTID primary key (PRODUCTID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.PRODUCTS
  add constraint FK_PRODUCTS_FIRSTTYPEID foreign key (FIRSTTYPEID)
  references STORE.FIRSTTYPES (FIRSTTYPEID);
alter table STORE.PRODUCTS
  add constraint FK_PRODUCTS_SECONDTYPEID foreign key (SECONDTYPEID)
  references STORE.SECONDTYPES (SECONDTYPEID);

prompt
prompt Creating table COMMENTS
prompt =======================
prompt
create table STORE.COMMENTS
(
  commentid     NUMBER(10) not null,
  userid        VARCHAR2(10),
  productid     VARCHAR2(10),
  commentdatils VARCHAR2(500),
  create_date   DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.COMMENTS
  add constraint PK_COMMENTS_COMMENTID primary key (COMMENTID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.COMMENTS
  add constraint FK_COMMENTS_PRODUCTID foreign key (PRODUCTID)
  references STORE.PRODUCTS (PRODUCTID);
alter table STORE.COMMENTS
  add constraint FK_COMMENTS_USERID foreign key (USERID)
  references STORE.USERS (USERID);

prompt
prompt Creating table ORDERDATILS
prompt ==========================
prompt
create table STORE.ORDERDATILS
(
  orderdatilsid VARCHAR2(10) not null,
  orderno       VARCHAR2(30),
  datilsid      VARCHAR2(10),
  quantity      NUMBER(10),
  productname   VARCHAR2(50),
  userid        VARCHAR2(10)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.ORDERDATILS
  add constraint PK_ORDERDATILS_ORDERDATILSID primary key (ORDERDATILSID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.ORDERDATILS
  add constraint FK_ORDERDATILS_USERID foreign key (USERID)
  references STORE.USERS (USERID);

prompt
prompt Creating table ORDERS
prompt =====================
prompt
create table STORE.ORDERS
(
  orderid       VARCHAR2(10) not null,
  orderno       VARCHAR2(30),
  userid        VARCHAR2(10),
  orderstatus   NUMBER(10) default 1,
  total         NUMBER(10,2),
  pay_date      DATE,
  send_date     DATE,
  get_date      DATE,
  create_date   DATE,
  update_date   DATE,
  addressname   VARCHAR2(50),
  city          VARCHAR2(50),
  province      VARCHAR2(50),
  addressdatils VARCHAR2(100),
  telephone     VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.ORDERS
  add constraint PK_ORDERS_ORDERID primary key (ORDERID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.ORDERS
  add constraint FK_ORDERS_USERID foreign key (USERID)
  references STORE.USERS (USERID);

prompt
prompt Creating table PRODUCT_DATILS
prompt =============================
prompt
create table STORE.PRODUCT_DATILS
(
  productid    VARCHAR2(10),
  colour       VARCHAR2(10),
  shotsize     VARCHAR2(10),
  clothingsize VARCHAR2(10),
  nums         NUMBER(10),
  price        NUMBER(10,2),
  create_date  DATE,
  update_date  DATE,
  picture      VARCHAR2(100),
  datilsid     VARCHAR2(10) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.PRODUCT_DATILS
  add constraint PK_PRODUCT_DATILS_DATILSID primary key (DATILSID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.PRODUCT_DATILS
  add constraint FK_PRODUCT_DATILS_PRODUCTID foreign key (PRODUCTID)
  references STORE.PRODUCTS (PRODUCTID);

prompt
prompt Creating table TB_CART
prompt ======================
prompt
create table STORE.TB_CART
(
  id          VARCHAR2(10) not null,
  datilsid    VARCHAR2(10) not null,
  userid      VARCHAR2(10) not null,
  productname VARCHAR2(60),
  quantity    NUMBER(10),
  create_date DATE,
  checked     NUMBER(10) default 0
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.TB_CART
  add constraint PK_TB_CART_ID primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STORE.TB_CART
  add constraint FK_TB_CART_DATILSID foreign key (DATILSID)
  references STORE.PRODUCT_DATILS (DATILSID);
alter table STORE.TB_CART
  add constraint FK_TB_CART_USERID foreign key (USERID)
  references STORE.USERS (USERID);

prompt
prompt Creating sequence ADDRESSSEQUENCES
prompt ==================================
prompt
create sequence STORE.ADDRESSSEQUENCES
minvalue 1
maxvalue 9999
start with 61
increment by 1
cache 20;

prompt
prompt Creating sequence AUTHORITYSEQUENCES
prompt ====================================
prompt
create sequence STORE.AUTHORITYSEQUENCES
minvalue 1
maxvalue 999
start with 21
increment by 1
cache 20;

prompt
prompt Creating sequence COMMENTSEQUENCES
prompt ==================================
prompt
create sequence STORE.COMMENTSEQUENCES
minvalue 1
maxvalue 99999
start with 21
increment by 1
cache 20;

prompt
prompt Creating sequence DATILSSEQUENCES
prompt =================================
prompt
create sequence STORE.DATILSSEQUENCES
minvalue 1
maxvalue 9999
start with 21
increment by 1
cache 20;

prompt
prompt Creating sequence ORDERDATILSSEQUENCES
prompt ======================================
prompt
create sequence STORE.ORDERDATILSSEQUENCES
minvalue 1
maxvalue 99999
start with 81
increment by 1
cache 20;

prompt
prompt Creating sequence ORDERSEQUENCES
prompt ================================
prompt
create sequence STORE.ORDERSEQUENCES
minvalue 1
maxvalue 9999
start with 81
increment by 1
cache 20;

prompt
prompt Creating sequence PRODUCTSEQUENCES
prompt ==================================
prompt
create sequence STORE.PRODUCTSEQUENCES
minvalue 0
maxvalue 99999
start with 140
increment by 1
cache 20;

prompt
prompt Creating sequence SECONDTYPESEQUENCES
prompt =====================================
prompt
create sequence STORE.SECONDTYPESEQUENCES
minvalue 200001
maxvalue 299999
start with 200121
increment by 1
cache 20;

prompt
prompt Creating sequence TB_CARTSEQUENCES
prompt ==================================
prompt
create sequence STORE.TB_CARTSEQUENCES
minvalue 1
maxvalue 9999
start with 101
increment by 1
cache 20;

prompt
prompt Creating sequence USERSEQUENCES
prompt ===============================
prompt
create sequence STORE.USERSEQUENCES
minvalue 1
maxvalue 9999
start with 61
increment by 1
cache 20;


spool off
