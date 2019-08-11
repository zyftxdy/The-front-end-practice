prompt PL/SQL Developer import file
prompt Created on 2019年6月5日 by 12443
set feedback off
set define off
prompt Disabling triggers for USERS...
alter table USERS disable all triggers;
prompt Disabling triggers for ADDRESS...
alter table ADDRESS disable all triggers;
prompt Disabling triggers for AUTHORITYS...
alter table AUTHORITYS disable all triggers;
prompt Disabling triggers for FIRSTTYPES...
alter table FIRSTTYPES disable all triggers;
prompt Disabling triggers for SECONDTYPES...
alter table SECONDTYPES disable all triggers;
prompt Disabling triggers for PRODUCTS...
alter table PRODUCTS disable all triggers;
prompt Disabling triggers for COMMENTS...
alter table COMMENTS disable all triggers;
prompt Disabling triggers for ORDERDATILS...
alter table ORDERDATILS disable all triggers;
prompt Disabling triggers for ORDERS...
alter table ORDERS disable all triggers;
prompt Disabling triggers for PRODUCT_DATILS...
alter table PRODUCT_DATILS disable all triggers;
prompt Disabling triggers for TB_CART...
alter table TB_CART disable all triggers;
prompt Disabling foreign key constraints for ADDRESS...
alter table ADDRESS disable constraint FK_ADDRESS_USERID;
prompt Disabling foreign key constraints for SECONDTYPES...
alter table SECONDTYPES disable constraint FK_SCEONDTYPES_FIRSTTYPEID;
prompt Disabling foreign key constraints for PRODUCTS...
alter table PRODUCTS disable constraint FK_PRODUCTS_FIRSTTYPEID;
alter table PRODUCTS disable constraint FK_PRODUCTS_SECONDTYPEID;
prompt Disabling foreign key constraints for COMMENTS...
alter table COMMENTS disable constraint FK_COMMENTS_PRODUCTID;
alter table COMMENTS disable constraint FK_COMMENTS_USERID;
prompt Disabling foreign key constraints for ORDERDATILS...
alter table ORDERDATILS disable constraint FK_ORDERDATILS_USERID;
prompt Disabling foreign key constraints for ORDERS...
alter table ORDERS disable constraint FK_ORDERS_USERID;
prompt Disabling foreign key constraints for PRODUCT_DATILS...
alter table PRODUCT_DATILS disable constraint FK_PRODUCT_DATILS_PRODUCTID;
prompt Disabling foreign key constraints for TB_CART...
alter table TB_CART disable constraint FK_TB_CART_DATILSID;
alter table TB_CART disable constraint FK_TB_CART_USERID;
prompt Deleting TB_CART...
delete from TB_CART;
commit;
prompt Deleting PRODUCT_DATILS...
delete from PRODUCT_DATILS;
commit;
prompt Deleting ORDERS...
delete from ORDERS;
commit;
prompt Deleting ORDERDATILS...
delete from ORDERDATILS;
commit;
prompt Deleting COMMENTS...
delete from COMMENTS;
commit;
prompt Deleting PRODUCTS...
delete from PRODUCTS;
commit;
prompt Deleting SECONDTYPES...
delete from SECONDTYPES;
commit;
prompt Deleting FIRSTTYPES...
delete from FIRSTTYPES;
commit;
prompt Deleting AUTHORITYS...
delete from AUTHORITYS;
commit;
prompt Deleting ADDRESS...
delete from ADDRESS;
commit;
prompt Deleting USERS...
delete from USERS;
commit;
prompt Loading USERS...
insert into USERS (userid, username, password, email, telephone, question, answer, status, create_date, update_date)
values ('21', 'shuaishuai', 'E35CF7B66449DF565F93C607D5A81D09', 'zyftxdy@163.com', '13994996024', '456789', '456789', '正常', to_date('19-03-2019 14:51:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into USERS (userid, username, password, email, telephone, question, answer, status, create_date, update_date)
values ('41', 'lengleng', 'E10ADC3949BA59ABBE56E057F20F883E', '1244378510@qq.com', '15735154430', '12345', '12345', '正常', to_date('05-05-2019 21:12:43', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into USERS (userid, username, password, email, telephone, question, answer, status, create_date, update_date)
values ('22', 'zhazha', 'D6A9A933C8AAFC51E55AC0662B6E4D4A', '1244378510@qq.com', '13099019341', '0123', '0123', '正常', to_date('19-03-2019 14:56:08', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into USERS (userid, username, password, email, telephone, question, answer, status, create_date, update_date)
values ('3', 'huahua', 'E10ADC3949BA59ABBE56E057F20F883E', '1244378510@qq.com', '15735154430', '12345', '12345', '正常', to_date('16-03-2019 16:17:39', 'dd-mm-yyyy hh24:mi:ss'), to_date('31-03-2019 22:02:47', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 4 records loaded
prompt Loading ADDRESS...
insert into ADDRESS (addressid, userid, addressname, province, city, addressdatils, telephone, create_date, update_date)
values ('3', '22', '翟新帅', '山西省', '太原市', '太原理工大学', '13994996024', to_date('03-04-2019 19:58:07', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into ADDRESS (addressid, userid, addressname, province, city, addressdatils, telephone, create_date, update_date)
values ('22', '22', '耿文刚', '陕西省', '西安市', '西安交通大学', '15735154430', to_date('12-05-2019 21:45:22', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into ADDRESS (addressid, userid, addressname, province, city, addressdatils, telephone, create_date, update_date)
values ('23', '22', '张永峰', '福建省', '厦门市', '厦门大学', '15735154430', to_date('12-05-2019 21:46:33', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into ADDRESS (addressid, userid, addressname, province, city, addressdatils, telephone, create_date, update_date)
values ('2', '3', '张永峰', '山西省', '太原市', '太原理工大学', '15735154430', to_date('01-04-2019 19:29:12', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-04-2019 20:37:43', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 4 records loaded
prompt Loading AUTHORITYS...
insert into AUTHORITYS (id, adminname, adminpwd, authority, create_date)
values (2, 'huahua', '012345', 2, to_date('16-05-2019 21:45:56', 'dd-mm-yyyy hh24:mi:ss'));
insert into AUTHORITYS (id, adminname, adminpwd, authority, create_date)
values (1, 'admin', 'admin', 4, to_date('15-05-2019 20:42:31', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 2 records loaded
prompt Loading FIRSTTYPES...
insert into FIRSTTYPES (firsttypeid, firsttypename, create_date, update_date)
values ('100001', '网球装备', to_date('02-03-2019 20:15:09', 'dd-mm-yyyy hh24:mi:ss'), to_date('02-03-2019 21:01:22', 'dd-mm-yyyy hh24:mi:ss'));
insert into FIRSTTYPES (firsttypeid, firsttypename, create_date, update_date)
values ('100002', '羽毛球装备', to_date('02-03-2019 20:15:09', 'dd-mm-yyyy hh24:mi:ss'), to_date('02-03-2019 20:15:19', 'dd-mm-yyyy hh24:mi:ss'));
insert into FIRSTTYPES (firsttypeid, firsttypename, create_date, update_date)
values ('100003', '篮球装备', to_date('02-03-2019 20:15:09', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:54:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into FIRSTTYPES (firsttypeid, firsttypename, create_date, update_date)
values ('100004', '乒乓球装备', to_date('02-03-2019 20:15:09', 'dd-mm-yyyy hh24:mi:ss'), to_date('02-03-2019 20:15:19', 'dd-mm-yyyy hh24:mi:ss'));
insert into FIRSTTYPES (firsttypeid, firsttypename, create_date, update_date)
values ('100005', '户外装备', to_date('02-03-2019 20:15:09', 'dd-mm-yyyy hh24:mi:ss'), to_date('02-03-2019 20:15:19', 'dd-mm-yyyy hh24:mi:ss'));
insert into FIRSTTYPES (firsttypeid, firsttypename, create_date, update_date)
values ('100006', '滑雪装备', to_date('02-03-2019 20:15:09', 'dd-mm-yyyy hh24:mi:ss'), to_date('02-03-2019 20:15:19', 'dd-mm-yyyy hh24:mi:ss'));
insert into FIRSTTYPES (firsttypeid, firsttypename, create_date, update_date)
values ('100007', '跑步装备', to_date('02-03-2019 20:15:09', 'dd-mm-yyyy hh24:mi:ss'), to_date('02-03-2019 20:15:19', 'dd-mm-yyyy hh24:mi:ss'));
insert into FIRSTTYPES (firsttypeid, firsttypename, create_date, update_date)
values ('100008', '健身装备', to_date('02-03-2019 20:15:09', 'dd-mm-yyyy hh24:mi:ss'), to_date('02-03-2019 20:15:19', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 8 records loaded
prompt Loading SECONDTYPES...
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200001', '网球拍', '100001', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 20:36:37', 'dd-mm-yyyy hh24:mi:ss'), '2f1cbac8-712e-4af6-9d05-445676ba1089_pai.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200002', '网球服装', '100001', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:15:25', 'dd-mm-yyyy hh24:mi:ss'), 'fd52ff8b-cde1-4abb-9953-9ca9dcf5ab03_fu1.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200003', '网球鞋', '100001', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:16:43', 'dd-mm-yyyy hh24:mi:ss'), '0e55dd0f-c489-49b8-a895-ac4560a0060f_xie1.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200004', '网球包', '100001', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:17:32', 'dd-mm-yyyy hh24:mi:ss'), '841ba6c0-e077-4bcb-af44-b2979177798c_bao1.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200005', '网球线', '100001', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:18:20', 'dd-mm-yyyy hh24:mi:ss'), '02af2f72-0d3e-42cf-8fe4-028f0764a49a_xian.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200006', '羽毛球拍', '100002', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:36:13', 'dd-mm-yyyy hh24:mi:ss'), 'c9c6f508-e4bf-495d-a3da-85d5755af859_pai1.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200007', '羽毛球鞋', '100002', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:37:06', 'dd-mm-yyyy hh24:mi:ss'), '4ee921e3-287d-4d3b-8516-c575ba0627ea_xie2.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200008', '羽毛球服', '100002', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:37:44', 'dd-mm-yyyy hh24:mi:ss'), '1d1f94c2-bee9-47d2-b6f7-14f2dd08ec28_fu3.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200009', '羽毛球袜', '100002', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:38:23', 'dd-mm-yyyy hh24:mi:ss'), '5fd7757e-2684-40fd-bf38-1e8d961bcc25_wa.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200010', '羽毛球', '100002', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:39:02', 'dd-mm-yyyy hh24:mi:ss'), '02f3d025-50c2-405d-be0b-d0826282e99f_qiu.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200011', '篮球', '100003', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:54:20', 'dd-mm-yyyy hh24:mi:ss'), 'e27009e9-c15c-4274-9408-2e89c5bbb284_qiu.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200012', '篮球鞋', '100003', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:55:16', 'dd-mm-yyyy hh24:mi:ss'), '8d4f9a1b-e41a-4c09-92b7-01dbc765a491_lan.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200013', '篮球包', '100003', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:55:48', 'dd-mm-yyyy hh24:mi:ss'), '71b89a12-2875-4ceb-ab6c-3736e91a1c3d_bao.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200014', '篮球服', '100003', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:56:26', 'dd-mm-yyyy hh24:mi:ss'), 'd06f3442-87aa-472f-86fa-dfdbac46c519_fu.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200015', '篮球护具', '100003', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:57:06', 'dd-mm-yyyy hh24:mi:ss'), '4c4c5cb6-accc-4160-a14d-4942e955d5b6_hu.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200016', '乒乓球拍', '100004', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:57:44', 'dd-mm-yyyy hh24:mi:ss'), '586b031d-007e-40c2-b221-63f8f2872493_pai.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200017', '乒乓球发球机', '100004', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:58:27', 'dd-mm-yyyy hh24:mi:ss'), 'dafa2643-76e7-4741-a01c-f64523f2b1b1_fa.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200018', '乒乓球台', '100004', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:59:22', 'dd-mm-yyyy hh24:mi:ss'), '324efac6-9c0f-45d0-a01f-1801086666cf_tai.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200019', '乒乓球', '100004', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:59:50', 'dd-mm-yyyy hh24:mi:ss'), '733cc233-f8bd-4b63-b981-19c95e1979a7_qiu1.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200020', '乒乓球服', '100004', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 22:00:25', 'dd-mm-yyyy hh24:mi:ss'), '425c4410-7367-4ed8-86dd-d146ddf35830_fu3.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200021', '户外服装', '100005', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:50:42', 'dd-mm-yyyy hh24:mi:ss'), '32063b2c-9ecf-434c-9c3e-bdb7006d9bd4_fu1.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200022', '户外鞋类', '100005', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:51:11', 'dd-mm-yyyy hh24:mi:ss'), 'f8a85350-b87d-43f7-8a35-221ff347c4f6_xie1.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200023', '户外背包', '100005', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:51:45', 'dd-mm-yyyy hh24:mi:ss'), '7fbe1194-101a-485d-8325-ec0e5229166f_bao.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200024', '攀登器材', '100005', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:52:22', 'dd-mm-yyyy hh24:mi:ss'), '4db4b170-4bc1-4e7b-ae0d-7c6e694e447c_pan.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200025', '户外帐篷', '100005', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:53:05', 'dd-mm-yyyy hh24:mi:ss'), 'cfaf6a94-5d1a-49c3-8b8d-c8d9b420c1ae_za.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200026', '滑雪护镜', '100006', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:47:03', 'dd-mm-yyyy hh24:mi:ss'), '410a5f20-df81-4ee1-bb6f-f1579ea6834e_yan.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200027', '滑雪服装', '100006', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:47:44', 'dd-mm-yyyy hh24:mi:ss'), 'c8a3759d-f1b6-45f9-b14c-283f02451532_fu.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200028', '滑雪单板', '100006', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:50:12', 'dd-mm-yyyy hh24:mi:ss'), '8ea4dcb3-0c74-475a-a529-976ab1db99b1_ban.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200029', '滑雪头盔', '100006', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:48:59', 'dd-mm-yyyy hh24:mi:ss'), '75e8c6da-b1eb-4334-bc6e-f554284f7138_tou.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200030', '滑雪护具', '100006', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:49:32', 'dd-mm-yyyy hh24:mi:ss'), '219f5bc9-cd23-47ac-b6bf-a3c480250aef_hu.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200031', '运动鞋', '100007', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:43:38', 'dd-mm-yyyy hh24:mi:ss'), '4bfce827-a9d9-45f9-8d56-9b18f145b73a_xie.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200032', '运动上衣', '100007', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:44:14', 'dd-mm-yyyy hh24:mi:ss'), 'bd904aaa-67bb-4aca-a8d7-cfeed5f92fe6_yi.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200033', '运动手环', '100007', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:44:49', 'dd-mm-yyyy hh24:mi:ss'), 'a5196195-0169-4b68-bf99-385c17283f1f_huan.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200034', '运动内衣', '100007', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:45:32', 'dd-mm-yyyy hh24:mi:ss'), '4c0a99c6-19c8-4626-a6de-3b9ccee103f4_fu.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200035', '运动短裤', '100007', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:46:05', 'dd-mm-yyyy hh24:mi:ss'), '7e40e41c-a93f-401b-804c-e1a5f93e887d_ku1.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200036', '瑜伽垫', '100008', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:40:05', 'dd-mm-yyyy hh24:mi:ss'), '3ae48d24-1407-40e1-bb59-bf28e92c0fa6_yujia.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200037', '发带', '100008', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:40:34', 'dd-mm-yyyy hh24:mi:ss'), 'e061da9b-c58b-44d6-9a6e-50bcd037a978_dai.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200038', '跑步机', '100008', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:41:15', 'dd-mm-yyyy hh24:mi:ss'), '02ceeedd-efe8-45e2-b52d-269ddc062e86_ji.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200039', '手套', '100008', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:41:47', 'dd-mm-yyyy hh24:mi:ss'), '7204d9f7-11ba-4a64-8a97-e486adc07825_tao.png');
insert into SECONDTYPES (secondtypeid, secondtypename, firsttypeid, create_date, update_date, picture)
values ('200040', '负重腰带', '100008', to_date('02-03-2019 20:21:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-03-2019 21:42:47', 'dd-mm-yyyy hh24:mi:ss'), '29a9c250-2bc8-4ebf-8722-ca8f6e10bb60_yao.png');
commit;
prompt 40 records loaded
prompt Loading PRODUCTS...
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('9', '100001', '200001', '尤迪曼网球拍初学者正品', '独立的线控设计，破风式拍框，专业拍面，拍头减震保护系统。', 'b6b44365-c7f8-4daf-b7d7-60552e497a1b_tennis8.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('10', '100001', '200001', 'bonny波力网球拍单人全初学训练', '碳纤维一体拍，黄金拍面，轻质手感，舒适体验。', '7de462c1-6c7e-4b4d-8c34-1692cbf44e94_tennis9.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('44', '100001', '200005', 'Wilson/威尔胜网球拍线压纹线', 'Wilson/威尔胜 网球拍线压纹线力士浪网球硬线，威尔逊网球线，力士浪网球线。', '67c4227e-4e01-481b-8356-bd51365a0065_xian3.png', '在售', to_date('02-03-2019 21:25:47', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('16', '100001', '200001', '正品克洛斯威网球拍单人专业套装', '稳定耐打一体设计，凹式护线槽，三联式减震，平行独立线孔。', 'b4e6b004-e789-4ea8-9834-19adc4b1953c_tennis15.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('17', '100001', '200002', '短袖网球服定制', '对自己好点，买自己适合的，运动一样可以潮起来，柔软亲肤，轻量透气。', '7786cfe0-af2b-462e-b095-978d2daf95da_yifu1.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('18', '100001', '200002', '领胜夏季男女运动网球服', '时尚运动风系列，舒适运动面料，修身塑形，透气舒适。', 'f6c6de5e-d1ec-48b2-9b9b-6a3e9a049db3_yifu2.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('19', '100001', '200002', '鹰尔凯短袖文化衫网球服', '高透气速干面料，柔软贴体，四面弹力运动。', '52cf35f3-1946-45b2-afc9-f79a5616078e_yifu3.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('20', '100001', '200002', '男神女神必入春季网球运动衣', '你所钟爱的情侣装新品，完美修身版型。专业品牌设计师精心设计。', '12d2a1c8-97ff-4e65-9472-3b71c09cdff0_yifu4.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('21', '100001', '200002', '2019新款鹰尔凯男女网球服装', '透气速干，吸湿排汗，舒适亲肤环保，原纱吸湿排汗原料。', 'af1f33ea-08df-494f-8d59-106f38bf203d_yifu5.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('22', '100001', '200002', '韩国男女运动服速干短袖圆领网球服装', '韩国新款偶像定制服装。', 'fc58f7ec-6b0b-4606-aed2-4c90190bc2e5_yifu6.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('23', '100001', '200002', '金羽女款网球服运动装', '速干透气，柔软舒适，塑身而不束身，内置胸垫，丰胸造型。', 'be728a01-55d9-40b5-815a-31e37f2b8fd5_yifu7.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('24', '100001', '200002', '专柜正品Yonex尤尼克斯女款网球裙', '夏季新版，不仅仅是好看，更是品质的追求，百搭防走光。', '1bfa7027-6fce-4b38-83ec-5dcc04675b93_yifu8.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('25', '100001', '200002', '网球服男款速干短袖上衣', '逼真狼图腾印花，高技术转移印花，经久耐磨，心型领口。', '028b5633-4932-4c1c-a89e-f001cb026f7c_yifu9.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('26', '100001', '200002', 'Volkl沃克女士速干网球运动短袖服装', '团购 队服Volkl沃克女士速干 网球运动服装T恤VK-10W20-19。', 'b8cce798-9a5d-4331-8850-d0c9ea9aa9d0_yifu10.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('27', '100001', '200002', '大码网球服连衣裙女修身服装', '美极酷，3维立体修身设计，聚酯纤维舒适有弹性。', '68f3c414-4357-40a4-8800-34b5da04b952_yifu11.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('28', '100001', '200002', '唐盾网球服装夏季短袖男女', '时装版型，修身设计，速干透气，唐盾TODO夏季新款网球服。', '4f4d6c32-ab92-43c4-9e25-e731cde93dba_yifu12.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('29', '100001', '200002', '佩吉酷网球服短袖女款球服服装', '修身透气，舒爽，韩版DIY定制，印什么由你决定。', '5ab1e5cc-4c84-4102-9a8c-06d12afc21ec_yifu13.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('30', '100001', '200002', 'SKY女款上衣夏短袖运动服装速干网球服', '汤尤杯运动装备与你相约。', '7899877b-f8ec-4e8e-9832-a44d29f9463d_yifu14.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('31', '100001', '200002', 'jofit夏季薄款速干女士网球运动服装无袖背心上衣', '这个面料简直舒服的没话说，摸起来想婴儿的脸蛋，穿在身上又冰凉又亲肤。', 'ae73544e-90b0-4902-865c-cc60528771e1_yifu15.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('32', '100001', '200003', '正品wilson威尔胜网球鞋kaos系列', '疾速系列，专业网球运动鞋，疾速致胜，唯快不破。', '2d166557-37ba-45e0-9bde-458a73fb3144_xie1.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('33', '100001', '200003', '阿迪达斯官方Barricade Classic Bounce男网球鞋', 'Barricade Classic Bounce男子网球网球鞋，一款旨在为多种场地打造的网球鞋。', '32dafc4d-725c-4e92-abd1-3055b4f0d15d_xie2.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('34', '100001', '200003', 'Nike白银钩运动复古男女网球鞋', 'Nike Court Lite白银钩运动小白鞋老爹复古网球鞋。', '086e52d9-6b8d-496d-b84d-39a982ab64a7_xie3.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('35', '100001', '200003', 'HEAD海德男女儿童初学训练网球鞋', '防滑耐磨网球鞋，包裹性，贴合性，复合橡胶，鞋底更耐磨。', '2acb8cc9-452f-4513-aa03-614260276665_xie4.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('36', '100001', '200003', '鸿星尔克网球鞋男鞋新款春季', '换季清仓，限时大抢购，仿齿网球鞋，复古回潮。', '0eba3488-2cca-49e9-8cc3-571441d1aeda_xie5.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('37', '100001', '200004', '威尔胜0318网球包', '网球包特价促销，超优惠，费德勒同款，包包设计有长肩带，可单肩斜跨使用。', '478d920e-3502-4e20-a1cf-ccb98cddf575_bao1.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('38', '100001', '200004', 'Wilson威尔逊网球包男女情侣', '大容量,时尚多彩，网球包采用流线型设计，与Wilson畅销的网球拍系列的鲜艳色彩相呼应。', 'f1e0eaa1-9677-4baf-8e64-24108402833f_bao2.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('39', '100001', '200004', '迪卡侬双肩大容量网球包', '三种尺寸可选，分隔大容量 ，耐磨，携带方便。', 'c489328c-b977-4fab-8892-d22c8e16ff4b_bao3.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('40', '100001', '200004', '正品耐克网球包2019大容量双肩包', '正品耐克网球包2019年澳网新款背书包大容量双肩包BA5452 BA5451。', 'df3899e9-6ec9-485d-8c14-a3a47445d189_bao4.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('41', '100001', '200004', '掌柜推荐网球包单肩包绒布袋', '弗尔德恩绒布袋，摇粒绒布料，珊瑚绒布料。', '96902590-b088-4dca-bb51-572daabfd658_bao5.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('42', '100001', '200005', 'Luxilon力士浪4G网球线', 'Luxilon力士浪4G网球线Alu Power rough 125费德勒网球线聚酯硬线。', '2a8647da-f416-4b2f-a4b2-b8c9012bc64f_xian1.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('2', '100001', '200001', 'HEDA海德网球拍', '教练推荐 复合碳素一体网拍，大学生网球社选修课佳选。', 'ecbc72eb-1daf-4ffd-aaee-aca164b8d746_tennis.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-03-2019 16:17:03', 'dd-mm-yyyy hh24:mi:ss'));
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('3', '100001', '200001', 'Wilson威尔逊网球拍', '威尔逊单人男女初学者威尔胜大学生网球训练套装', '46b40d16-f7b2-4016-ace6-e789be320c86_tennis2.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('60', '100002', '200006', '红双喜羽毛球拍双拍耐打成人初学耐用', '耐打羽毛球拍，碳素材质，终生免费穿拉线。', '81c7b07d-b2fa-459b-afa9-1c534618ab18_yumao.png', '在售', to_date('06-03-2019 19:48:07', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('11', '100001', '200001', 'Topace网球拍初学者单人训练', '让你与众不同，真正全碳素，正品保障，高性价比。', '99a8a423-3d56-4bae-bd76-21e165edd1fa_tennis10.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('12', '100001', '200001', '天龙女士初学碳素网球拍', '少女系列初学网球拍，超大拍面，超轻重量，时尚涂装，破风拍框。', '4f56f219-1c14-4323-88aa-a0415cda819b_tennis11.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('13', '100001', '200001', '百动网球拍单人初学者碳纤维', '碳素一体，一体成型技术，碳素复合材料，强化减震手柄。', '623cdbac-5ca2-45ad-9117-3bbc5919e67f_tennis12.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('14', '100001', '200001', '川崎网球拍一体拍双人单人初学者装备', '初学碳素一体网拍，高性价比，入门初学佳选，给你更好的手感。', '17a59da4-f6d0-48b0-bfaf-45912a3224a7_tennis13.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('15', '100001', '200001', '红双喜网球拍正品一体套装', '国际赛事指定运动器材商，红双喜56年国货品牌，值得信赖。', '0a44402e-7b4f-44c7-88f7-7221089f0e28_tennis14.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('43', '100001', '200005', 'TAAN泰昂正品网球线', 'TAAN泰昂正品网球线TT5600 8600 5850大盘线聚酯线硬线高弹力。', '32769bf1-4ce9-49cc-8e93-51885d0d828e_xian2.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('46', '100001', '200005', '德国MSVFOCUSHEX网球线', '德国MSVFOCUSHEX网球线，大盘散线六角聚酯线硬线耐打1条包邮。', '78524246-add2-47fd-bd40-c02418211fd4_xian5.png', '在售', to_date('02-03-2019 21:33:25', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('4', '100001', '200001', 'Babolat百宝力李娜网球拍PD', '无所畏惧，开放式线床科技，菱形线孔科技，升级减震科技。', '61459de5-f34f-43eb-a83f-91cfcded2a63_tennis3.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('5', '100001', '200001', '迪可侬网球拍男女单人初学者运动球拍', '大拍面/轻松上手/两年质保，轻量碳素，教练推荐。', 'de23095a-ea45-48e2-b05a-fc5f5a6607cb_tennis4.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('6', '100001', '200001', 'Wilson威尔胜入门推荐初学网球拍', '轻量舒适，控制性佳，攻守兼备，稳定接发；大拍面。', '585ee069-3bb9-44bf-b348-40d74d5bbfd9_tennis5.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('7', '100001', '200001', '中国风网球拍单人初学者', '赠送精美拍包，避震器，手胶，青花古韵，独立线孔。', 'c44b5489-b831-424d-84e4-d94af7ddeb04_tennis6.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('8', '100001', '200001', '克洛斯威网球拍单人正品', '物超所值，适合初学者，轻巧，弹力极佳。学生选修课推荐。', '22b1a48e-2598-457a-8d12-1559439c0adf_tennis7.png', '在售', to_date('02-03-2019 21:19:15', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('80', '100002', '200007', '斯比德正品羽毛球鞋超轻透气时尚男女运动鞋', '做工精良，时尚轻凉透气，为羽毛球运动而生。', '92019e3d-2197-4bd5-bb16-cc8682d0adcc_xie1.png', '在售', to_date('13-03-2019 17:28:49', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('81', '100002', '200008', '尤尼克斯YONEX羽毛球服', '2019春夏新品羽毛球服，YY男女运动速干短袖队服。', 'e1e989a1-7498-43d9-bc60-ee746a6879b5_fu1.png', '在售', to_date('13-03-2019 17:32:43', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('82', '100002', '200009', 'VICTOR胜利羽毛球袜', 'SK236专业羽毛球袜，给你最舒适的体验。', 'e47ebcc7-b01e-4c0e-89a6-ea5d7b8e53ad_wa.png', '在售', to_date('13-03-2019 17:37:02', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('83', '100002', '200010', '尼龙塑料耐打王羽毛球', '包邮12只室外训练球打不烂尼龙耐打球，运动季大促销。', '783cd932-e49f-48b4-87a4-790a692715f8_qiu.png', '在售', to_date('13-03-2019 17:42:42', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('84', '100003', '200011', 'nike篮球aj耐克Jordan正品篮球', '炫彩，激情澎湃；多色可选，训练比赛室内外水泥地防滑耐磨。', '88767a85-0d64-4ba3-aaae-8060f588fa01_qiu2.png', '在售', to_date('13-03-2019 17:46:44', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('85', '100003', '200012', '耐克PG3篮球鞋', '耐克PG3 2.5代保罗乔治NASA黑白首发篮球鞋，正品保证。', '81525a62-3493-45d7-84db-89b8293cf861_鞋2.png', '在售', to_date('13-03-2019 17:50:40', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('86', '100003', '200013', '训练健身防水篮球包', '高清印花，防水涂料，光边袋口，内里加固。', 'bfe89566-c4ac-4ed1-8fde-30413ada038c_bao.png', '在售', to_date('13-03-2019 17:53:37', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('87', '100003', '200014', '烽火体育NIKENBA球星球衣篮球服', '舒适面料，凉爽透气，正品保证。', '7ef8def7-8693-40ef-850d-5f922855a555_fu2.png', '在售', to_date('13-03-2019 17:57:09', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('88', '100003', '200015', '李宁护肘男女保暖运动篮球护具', '品质保证 做工细致 运动必备 多种样式；弹性包裹，即刻运动。', 'a2e1dddb-1546-4cc7-840e-c186872ff583_hu.png', '在售', to_date('13-03-2019 18:04:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('89', '100004', '200019', '辉胜无标三星级新材料乒乓球', '行业销售排行第一，球速快，弹性好，耐打，性价比高。', 'c1cda108-ef8d-4bce-aabf-e0a8c2bd3fda_qiu3.png', '在售', to_date('13-03-2019 18:12:27', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('90', '100004', '200020', '莹恋STIGA圆领乒乓球服', 'STIGA新款畅销乒乓球服，高端大气，立体轮廓，高品质聚酯纤维。', '56498d0b-a027-4dda-8323-bf2a3fe5bde7_fu4.png', '在售', to_date('13-03-2019 18:16:02', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('91', '100004', '200018', '单位家用户外室内标准乒乓球台', '高质量面板，坚固耐用，环保公益漆面，网架采用圆滑设计。', '0cb89707-6d45-4a19-ae4e-be00b40c17fb_tai.png', '在售', to_date('13-03-2019 18:20:59', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('92', '100004', '200017', '第五代超级乒皇T288-5乒乓球发球机', '豪华版正品原超级教练可用40+，语音提示无规律发球自动长短球不转球计时器。', '8b6c8934-e0cb-47b5-8e19-9631d5fef07f_fa.png', '在售', to_date('13-03-2019 18:39:12', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('93', '100004', '200016', '红双喜乒乓球拍', '红双喜乒乓球拍适合初学入门级选手，高性价比，性能强。', '5dbffde2-48f6-4120-b637-d425752a54a5_pp.png', '在售', to_date('13-03-2019 18:46:09', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('94', '100005', '200021', '秋冬户外冲锋衣男女情侣软壳登山服户外服装', '保暖冲锋衣外套，防泼水科技面料，加绒舒适内里，精工细作，彰显品质。', '825bf27c-02ba-499a-b870-476fcf6b4b67_fu5.png', '在售', to_date('13-03-2019 18:58:52', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('95', '100005', '200022', '探路者徒步鞋男女新款户外鞋登山鞋', '鞋身采用牛剖层面+织物，穿着温暖舒适。鞋带耐磨性好，不易断裂。柔软透气鞋垫。', '55f61f14-aae0-46c4-a316-e6a48dad7262_xie2.png', '在售', to_date('13-03-2019 19:07:21', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('96', '100005', '200023', '安美路户外背包折叠皮肤背包', '户外运动折叠背包，防泼水，透气耐磨，大容量，轻盈。挑战不可能，说走就走的旅行。', '4d9e4938-16c5-4896-9363-8b30fc550c9b_bao2.png', '在售', to_date('13-03-2019 19:18:11', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('97', '100005', '200024', '安索户外专业攀登主锁D型', '自动攀岩速降主锁扣登山安全钩装备用品，大开口 底部凹槽型受力稳定结构 三种锁门。', '42790a4a-96c6-469e-92be-63109a870e7f_pandeng.png', '在售', to_date('13-03-2019 19:20:27', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('98', '100005', '200025', '骆驼全自动户外帐篷野外露营帐篷', '全自动帐篷，弹压式，一帐多用，多功能选择：外帐单用，内账单用，合并使用。', '5bde8fce-baec-4a2c-8ca7-0221ef00f5d0_zhangpeng.png', '在售', to_date('13-03-2019 19:23:05', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('99', '100006', '200026', '滑雪护目镜包邮', '透气户外双层滑雪镜，双层防雾球面大视野，抵挡紫外线，可卡近视，时刻保护你的眼睛！', '8dddc0e4-9b08-41c5-ba0a-6357757fbf8b_yanjing.png', '在售', to_date('13-03-2019 19:31:01', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('100', '100006', '200027', '零下二度男女滑雪服', '外御寒，内保温，蓄热强，防风阻隔空气流动，三层复合抗风结构。', 'ec65a948-0772-4405-9250-c32893dc19ba_FU6.png', '在售', to_date('13-03-2019 19:34:07', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('101', '100006', '200028', '瓷雪具AONE单板滑雪板全能板超轻高性能', '正品保证，无忧退款。', '49db464c-0e19-45be-9a51-b1c6b3d85c62_xieban.png', '在售', to_date('13-03-2019 19:39:46', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('102', '100006', '200029', 'VOLOCOVER专业滑雪头盔', '一体成型透气滑雪头盔，十四孔强透气及透气开关，镜带卡扣设计，防止滑落。', '7f9ae641-121a-4845-9df4-d1122ecd1112_tou2.png', '在售', to_date('13-03-2019 19:43:29', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('103', '100006', '200030', 'NOBADAY户外滑雪手套保暖滑雪护具装备', '并指滑雪手套，给您最安全的防护！', '0f59d049-d617-47f4-9b9a-a33798db24ba_ju.png', '在售', to_date('13-03-2019 19:46:02', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('104', '100007', '200031', 'Adidas Iniki Boost I-5923运动鞋跑鞋', '一双你不能抗拒的跑鞋，轻质体验，动能传感，柔软吸汗，给您最舒适的感受。', '731bfb67-b058-446b-9495-7c481f6ffa58_xie4.png', '在售', to_date('13-03-2019 19:51:14', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('105', '100007', '200032', '阿迪达斯外套男新款休闲跑步运动服装', '多款可选，每件都好看，防风保暖，实用耐磨，修身，精致面料。', '99c701b5-efa7-43b6-800b-ec1ca5e91f6f_fu7.png', '在售', to_date('13-03-2019 19:54:12', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('106', '100007', '200033', 'DFYOU智能运动手环防水计步测心率血压手环', '运动智能手环，血压测量，运动计步，心率监控，微信运动同步。', 'ae367f2f-e897-42c6-aced-212753fb1502_huan.png', '在售', to_date('13-03-2019 19:58:12', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('107', '100007', '200034', '无袖随心裁一片式男士运动内衣背心', '冰丝无痕随心裁剪，轻薄舒适，跑步健身必备用品！', 'f51af367-8814-4f0c-8c3f-655c78b3b6f6_neiyi.png', '在售', to_date('13-03-2019 20:00:46', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('108', '100007', '200035', '夏季休闲运动短裤男3分裤纯棉弹力修身健身短裤', '纯棉面料，吸湿排汗，夏季潮流新款。', 'ffaa6a2e-118a-44e3-81f0-908794f998f1_ku.png', '在售', to_date('13-03-2019 20:03:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('109', '100008', '200036', '瑜伽垫健身垫初学者运动垫瑜珈垫无味防滑瑜伽垫', '磨砂横纹，适合你的防滑健身垫，享睡眠般的舒适健身运动垫，加厚加长加宽。', 'd3b65fc1-3b8d-49f1-a95f-fa46c762f5ee_dian.png', '在售', to_date('13-03-2019 20:08:06', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('110', '100008', '200037', '运动发带健身吸汗纯棉头巾', '弹性强，面料舒适，强力吸汗，经久耐用，潮流时尚。', '00707a1b-b5f0-4018-abc0-1e19d26082c3_fadai.png', '在售', to_date('13-03-2019 20:11:17', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('111', '100008', '200038', '小乔A1跑步机家用款小型折叠式', '小乔智能A1跑步机，10秒轻松折叠，专利减震，燃脂畅快淋漓。', '43f5c796-f8bc-4385-b099-a7c380c37016_paobu.png', '在售', to_date('13-03-2019 20:13:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('112', '100008', '200039', '专业运动健身手套', '吸汗透气，防滑颗粒，适用于各种健身训练，选用弹力透气网格面料。', 'ab8fdf09-9899-474f-b773-567d671a50b6_shoutao.png', '在售', to_date('13-03-2019 21:02:38', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PRODUCTS (productid, firsttypeid, secondtypeid, productname, productdesc, picture, productstatus, create_date, update_date)
values ('113', '100008', '200040', '钢板可调节负重腰带铅块钢板3KG', '强粘性魔术贴，高针织密度，贴合力较强，进口透气网，集舒适、透气、修身于一身。', '1a9371f9-2946-438d-baf6-c083dfda0307_yaodai.png', '在售', to_date('13-03-2019 21:41:32', 'dd-mm-yyyy hh24:mi:ss'), null);
commit;
prompt 79 records loaded
prompt Loading COMMENTS...
insert into COMMENTS (commentid, userid, productid, commentdatils, create_date)
values (2, '22', '10', '该商品非常适合初学者练习，给孩子买的，孩子很喜欢，上手很快。', to_date('12-05-2019 22:12:36', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 1 records loaded
prompt Loading ORDERDATILS...
insert into ORDERDATILS (orderdatilsid, orderno, datilsid, quantity, productname, userid)
values ('62', '155937648862922', '2', 1, 'HEDA海德网球拍', '22');
insert into ORDERDATILS (orderdatilsid, orderno, datilsid, quantity, productname, userid)
values ('63', '155937746277222', '6', 1, '短袖网球服定制', '22');
insert into ORDERDATILS (orderdatilsid, orderno, datilsid, quantity, productname, userid)
values ('64', '155937746277222', '8', 1, '正品wilson威尔胜网球鞋kaos系列', '22');
commit;
prompt 3 records loaded
prompt Loading ORDERS...
insert into ORDERS (orderid, orderno, userid, orderstatus, total, pay_date, send_date, get_date, create_date, update_date, addressname, city, province, addressdatils, telephone)
values ('61', '155937648862922', '22', 0, 459, null, null, null, to_date('01-06-2019 16:08:08', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-06-2019 16:19:08', 'dd-mm-yyyy hh24:mi:ss'), '王乔', '昌平区', '北京市', '北京职业交通技术学院', '15735154430');
insert into ORDERS (orderid, orderno, userid, orderstatus, total, pay_date, send_date, get_date, create_date, update_date, addressname, city, province, addressdatils, telephone)
values ('62', '155937746277222', '22', 0, 705.5, null, null, null, to_date('01-06-2019 16:24:22', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-06-2019 16:27:39', 'dd-mm-yyyy hh24:mi:ss'), '耿文刚', '西安市', '陕西省', '西安交通大学', '15735154430');
commit;
prompt 2 records loaded
prompt Loading PRODUCT_DATILS...
insert into PRODUCT_DATILS (productid, colour, shotsize, clothingsize, nums, price, create_date, update_date, picture, datilsid)
values ('32', '红', '39', null, 300, 359, to_date('09-03-2019 19:18:25', 'dd-mm-yyyy hh24:mi:ss'), null, '0a9705e5-e049-4be6-8dc5-38530e420a00_xie1.png', '10');
insert into PRODUCT_DATILS (productid, colour, shotsize, clothingsize, nums, price, create_date, update_date, picture, datilsid)
values ('2', '红', null, null, 195, 249.5, to_date('08-03-2019 22:52:16', 'dd-mm-yyyy hh24:mi:ss'), null, 'c811ffec-8cb1-4fb7-8c84-9ce33f16795c_head.png', '1');
insert into PRODUCT_DATILS (productid, colour, shotsize, clothingsize, nums, price, create_date, update_date, picture, datilsid)
values ('32', '绿', '39', null, 199, 456, to_date('09-03-2019 19:15:10', 'dd-mm-yyyy hh24:mi:ss'), null, '1d064685-def2-4352-9fb8-7dc3ac23be31_xie2.png', '9');
insert into PRODUCT_DATILS (productid, colour, shotsize, clothingsize, nums, price, create_date, update_date, picture, datilsid)
values ('10', '红', null, null, 197, 189, to_date('27-03-2019 19:36:09', 'dd-mm-yyyy hh24:mi:ss'), to_date('27-03-2019 19:36:31', 'dd-mm-yyyy hh24:mi:ss'), '4684a857-f710-49d6-8064-d7dbfc3f199b_pai.png', '12');
insert into PRODUCT_DATILS (productid, colour, shotsize, clothingsize, nums, price, create_date, update_date, picture, datilsid)
values ('10', '蓝', null, null, 198, 235, to_date('11-03-2019 22:33:08', 'dd-mm-yyyy hh24:mi:ss'), to_date('12-03-2019 19:02:41', 'dd-mm-yyyy hh24:mi:ss'), '12b535ad-a2ae-4db1-83bf-aa286194351c_pai1.png', '3');
insert into PRODUCT_DATILS (productid, colour, shotsize, clothingsize, nums, price, create_date, update_date, picture, datilsid)
values ('17', '红', null, 'S', 200, 118, to_date('08-03-2019 23:40:11', 'dd-mm-yyyy hh24:mi:ss'), null, '6633df0f-d92f-465d-ba5f-ba75ddd2867e_yifu3.png', '4');
insert into PRODUCT_DATILS (productid, colour, shotsize, clothingsize, nums, price, create_date, update_date, picture, datilsid)
values ('17', '橙', null, 'L', 198, 249.5, to_date('08-03-2019 23:48:32', 'dd-mm-yyyy hh24:mi:ss'), null, '8f95781d-96ab-40e9-99f1-e3a4ee9a3a0b_yifu6.png', '6');
insert into PRODUCT_DATILS (productid, colour, shotsize, clothingsize, nums, price, create_date, update_date, picture, datilsid)
values ('32', '绿', '38', null, 198, 456, to_date('08-03-2019 23:52:45', 'dd-mm-yyyy hh24:mi:ss'), null, '38d4a314-587f-44e7-8c94-3faa95795bce_xiezi3.png', '8');
insert into PRODUCT_DATILS (productid, colour, shotsize, clothingsize, nums, price, create_date, update_date, picture, datilsid)
values ('2', '黄', null, null, 197, 459, to_date('08-03-2019 23:35:55', 'dd-mm-yyyy hh24:mi:ss'), null, '0a0618be-a9f4-4716-b779-935dc73464e3_head3.png', '2');
insert into PRODUCT_DATILS (productid, colour, shotsize, clothingsize, nums, price, create_date, update_date, picture, datilsid)
values ('17', '红', null, 'xl', 300, 159, to_date('12-03-2019 19:20:35', 'dd-mm-yyyy hh24:mi:ss'), to_date('12-03-2019 19:21:24', 'dd-mm-yyyy hh24:mi:ss'), '7d4f7eba-0ae0-47af-9096-da5afc5d3107_1.png', '5');
insert into PRODUCT_DATILS (productid, colour, shotsize, clothingsize, nums, price, create_date, update_date, picture, datilsid)
values ('18', '蓝', null, 'xl', 198, 179, to_date('11-03-2019 21:14:53', 'dd-mm-yyyy hh24:mi:ss'), null, 'ee3658fa-ff7f-401d-896a-0d59ed40cc9b_yifu.png', '7');
commit;
prompt 11 records loaded
prompt Loading TB_CART...
prompt Table is empty
prompt Enabling foreign key constraints for ADDRESS...
alter table ADDRESS enable constraint FK_ADDRESS_USERID;
prompt Enabling foreign key constraints for SECONDTYPES...
alter table SECONDTYPES enable constraint FK_SCEONDTYPES_FIRSTTYPEID;
prompt Enabling foreign key constraints for PRODUCTS...
alter table PRODUCTS enable constraint FK_PRODUCTS_FIRSTTYPEID;
alter table PRODUCTS enable constraint FK_PRODUCTS_SECONDTYPEID;
prompt Enabling foreign key constraints for COMMENTS...
alter table COMMENTS enable constraint FK_COMMENTS_PRODUCTID;
alter table COMMENTS enable constraint FK_COMMENTS_USERID;
prompt Enabling foreign key constraints for ORDERDATILS...
alter table ORDERDATILS enable constraint FK_ORDERDATILS_USERID;
prompt Enabling foreign key constraints for ORDERS...
alter table ORDERS enable constraint FK_ORDERS_USERID;
prompt Enabling foreign key constraints for PRODUCT_DATILS...
alter table PRODUCT_DATILS enable constraint FK_PRODUCT_DATILS_PRODUCTID;
prompt Enabling foreign key constraints for TB_CART...
alter table TB_CART enable constraint FK_TB_CART_DATILSID;
alter table TB_CART enable constraint FK_TB_CART_USERID;
prompt Enabling triggers for USERS...
alter table USERS enable all triggers;
prompt Enabling triggers for ADDRESS...
alter table ADDRESS enable all triggers;
prompt Enabling triggers for AUTHORITYS...
alter table AUTHORITYS enable all triggers;
prompt Enabling triggers for FIRSTTYPES...
alter table FIRSTTYPES enable all triggers;
prompt Enabling triggers for SECONDTYPES...
alter table SECONDTYPES enable all triggers;
prompt Enabling triggers for PRODUCTS...
alter table PRODUCTS enable all triggers;
prompt Enabling triggers for COMMENTS...
alter table COMMENTS enable all triggers;
prompt Enabling triggers for ORDERDATILS...
alter table ORDERDATILS enable all triggers;
prompt Enabling triggers for ORDERS...
alter table ORDERS enable all triggers;
prompt Enabling triggers for PRODUCT_DATILS...
alter table PRODUCT_DATILS enable all triggers;
prompt Enabling triggers for TB_CART...
alter table TB_CART enable all triggers;
set feedback on
set define on
prompt Done.
