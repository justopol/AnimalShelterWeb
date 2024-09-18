insert into animal (id, dtype, adoptionstatus, age, available, basicprice, name, type, version) values ('8c78a068-f42c-417a-b64a-646394a2d78d', 'Mammal', 0, 3, true, 20, 'Burek', 'dog', 1);
insert into animal (id, dtype, adoptionstatus, age, available, basicprice, name, type, version) values ('666c005c-0d27-4187-a682-567e390a0639', 'Mammal', 1, 1, false, 20, 'Mruczek', 'cat', 2);
insert into animal (id, dtype, adoptionstatus, age, available, basicprice, name, type, version) values ('148214d6-786f-4a73-811a-886d91491b9e', 'Mammal', 0, 5, true, 20, 'Reksio', 'dog', 3);
insert into animal (id, dtype, adoptionstatus, age, available, basicprice, name, type, version, ) values ('435c2a0b-c930-4a30-81d7-41f5a1292e18', 'Mammal', 1, 8, false, 20, 'Pusia', 'cat', 1);
insert into animal (id, dtype, adoptionstatus, age, available, basicprice, name, type, version) values ('d2f0984d-8229-4d63-a49d-a58a6e348140', 'Mammal', 0, 2, true, 20, 'Luna', 'dog', 2);
insert into animal (id, dtype, adoptionstatus, age, available, basicprice, name, type, version) values  ('f0192792-1888-4290-8bda-94b18a1c9172', 'Reptile', 0, 10, false, 20, 'Tecza', 'lizard', 1);
insert into animal (id, dtype, adoptionstatus, age, available, basicprice, name, type, version) values  ('dbb114f6-c14c-4bbf-afe3-e6998a300d10', 'Reptile', 2, 2, false, 20, 'Bazyliszek', 'snake', 1);

insert into reptile (id) values  ('f0192792-1888-4290-8bda-94b18a1c9172');
insert into reptile (id) values  ('dbb114f6-c14c-4bbf-afe3-e6998a300d10');

insert into mammal (id, castrated) values ('8c78a068-f42c-417a-b64a-646394a2d78d', true);
insert into mammal (id, castrated) values ('666c005c-0d27-4187-a682-567e390a0639', false);
insert into mammal (id, castrated) values ('148214d6-786f-4a73-811a-886d91491b9e', true);
insert into mammal (id, castrated) values ('435c2a0b-c930-4a30-81d7-41f5a1292e18', false);
insert into mammal (id, castrated) values ('d2f0984d-8229-4d63-a49d-a58a6e348140', true);

insert into public.address (id, city, streetname, streetnumber, version) values  ('972e6e44-b0b0-497a-81d0-c5190c5b4888', 'Lodz', 'Ulicaa', '123', 0);
insert into public.account (id, role, active, login, password, version) values  ('61ac53c8-cbaf-4b02-a501-e9c48798b174', 'ADOPTER', true, 'Adopter1', '123', 0);
insert into public.person (id, email, firstname, lastname, personalid) values  ('61ac53c8-cbaf-4b02-a501-e9c48798b174','adopter1@wp.pl', 'Jack', 'Russel', '12345678910');
insert into public.adopter (id, adoptertype, address_id) values  ('61ac53c8-cbaf-4b02-a501-e9c48798b174', 'STANDARD', '972e6e44-b0b0-497a-81d0-c5190c5b4888');
insert into public.adoption (id, endadoptiontime, finaladoptioncost, startadoptiontime, version, adopter_id, animal_id) values  ('74fed3b7-d3f4-492b-814f-11dfaf358fc6', null, null, '2024-09-17', 0, '61ac53c8-cbaf-4b02-a501-e9c48798b174', 'dbb114f6-c14c-4bbf-afe3-e6998a300d10');

insert into public.account (id, role, active, login, password, version) values  ('61ac53c8-cbaf-4b02-a501-d9c48798b175', 'EMPLOYEE', true, 'Employee1', '123', 0);
insert into public.person (id, email, firstname, lastname, personalid) values  ('61ac53c8-cbaf-4b02-a501-d9c48798b175','employee1@wp.pl', 'Emp', 'Loyee', '12345678911');