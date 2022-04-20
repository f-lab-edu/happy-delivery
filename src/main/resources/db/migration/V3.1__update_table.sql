alter table user_addresses drop address;

alter table user_addresses add longitude decimal(9,6) not null;

alter table user_addresses add latitude decimal(8,6) not null;