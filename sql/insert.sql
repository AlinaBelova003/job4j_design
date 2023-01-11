insert into users(name) VALUES ('Alina');
insert into users(name) VALUES ('Ivan');

insert into role(name_role, users_id) VALUES ('Castomer1', 1);
insert into role(name_role) VALUES ('Castomer2', 2);

insert into rules(name_rules) VALUES ('freeze');
insert into rules(name_rules) VALUES ('do not throw');

insert into role_rules(role_id, rules_id) VALUES (1, 2);
insert into role_rules(role_id, rules_id) VALUES (1, 1);
insert into role_rules(role_id, rules_id) VALUES (1, 2);

insert into item(name_item, users_id) Values ('paint', 1);
insert into item(name_item, users_id) Values ('crystals', 2);

insert into comments(name_comments, item_id) VALUES ('no', 1);
insert into comments(name_comments, item_id) VALUES ('no', 2);

insert into attachs(name_attachs, item_id) VALUES ('no', 1);
insert into attachs(name_attachs, item_id) VALUES ('yes', 2);

insert into category(name_category, item_id) Values ('assembled', 1);
insert into category(name_category, item_id) Values ('send', 2);

insert into state(name_state, item_id) Values ('medicines', 2);
insert into state(name_state, item_id) Values ('design', 1);
