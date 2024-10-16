insert into animal (id, dtype, adoptionstatus, age, basicprice, name, type, version) values ('666c005c-0d27-4187-a682-567e390a0639', 'Mammal', 1, 1, 20, 'Mruczek', 'cat', 2);
insert into animal (id, dtype, adoptionstatus, age, basicprice, name, type, version) values ('8c78a068-f42c-417a-b64a-646394a2d78d', 'Mammal', 0, 3, 20, 'Burek', 'dog', 1);
insert into animal (id, dtype, adoptionstatus, age, basicprice, name, type, version) values ('148214d6-786f-4a73-811a-886d91491b9e', 'Mammal', 0, 5, 20, 'Reksio', 'dog', 3);
insert into animal (id, dtype, adoptionstatus, age, basicprice, name, type, version, ) values ('435c2a0b-c930-4a30-81d7-41f5a1292e18', 'Mammal', 1, 8, 20, 'Pusia', 'cat', 1);
insert into animal (id, dtype, adoptionstatus, age, basicprice, name, type, version) values ('d2f0984d-8229-4d63-a49d-a58a6e348140', 'Mammal', 0, 2, 20, 'Luna', 'dog', 2);
insert into animal (id, dtype, adoptionstatus, age, basicprice, name, type, version) values  ('f0192792-1888-4290-8bda-94b18a1c9172', 'Reptile', 0, 10, 20, 'Tecza', 'lizard', 1);
insert into animal (id, dtype, adoptionstatus, age, basicprice, name, type, version) values  ('dbb114f6-c14c-4bbf-afe3-e6998a300d10', 'Reptile', 2, 2, 20, 'Bazyliszek', 'snake', 1);
insert into animal (id, dtype, adoptionstatus, age, basicprice, name, type, version, ) values ('435c2a0b-c930-4a30-81d7-41f5a1292e21', 'Mammal', 2, 5, 20, 'Bary', 'dog', 1);
insert into animal (id, dtype, adoptionstatus, age, basicprice, name, type, version) values  ('dbb114f6-c14c-4bbf-afe3-e6998a300d15', 'Reptile', 1, 3, 20, 'Lolek', 'snake', 1);
insert into animal (id, dtype, adoptionstatus, age, basicprice, name, type, version) values  ('dbb114f7-c14c-4bbf-afe3-e6998a300d16', 'Reptile', 2, 4, 20, 'Bays', 'snake', 1);


insert into reptile (id) values  ('f0192792-1888-4290-8bda-94b18a1c9172');
insert into reptile (id) values  ('dbb114f6-c14c-4bbf-afe3-e6998a300d10');
insert into reptile (id) values  ('dbb114f6-c14c-4bbf-afe3-e6998a300d15');
insert into reptile (id) values  ('dbb114f7-c14c-4bbf-afe3-e6998a300d16');

insert into mammal (id, castrated) values ('8c78a068-f42c-417a-b64a-646394a2d78d', true);
insert into mammal (id, castrated) values ('666c005c-0d27-4187-a682-567e390a0639', false);
insert into mammal (id, castrated) values ('148214d6-786f-4a73-811a-886d91491b9e', true);
insert into mammal (id, castrated) values ('435c2a0b-c930-4a30-81d7-41f5a1292e18', false);
insert into mammal (id, castrated) values ('d2f0984d-8229-4d63-a49d-a58a6e348140', true);
insert into mammal (id, castrated) values ('435c2a0b-c930-4a30-81d7-41f5a1292e21', true);

insert into public.address (id, city, streetname, streetnumber, version) values  ('972e6e44-b0b0-497a-81d0-c5190c5b4888', 'Lodz', 'Ulicaa', '123', 0);
insert into public.account (id, role, active, login, password, version) values  ('61ac53c8-cbaf-4b02-a501-e9c48798b174', 'ADOPTER', true, 'Adopter1', '4a8904ca9d6ed04b441c893a05956351a67363a18850b1c72465f698654956b7', 0);
insert into public.person (id, email, firstname, lastname, personalid) values  ('61ac53c8-cbaf-4b02-a501-e9c48798b174','adopter1@wp.pl', 'Jack', 'Russel', '12345678910');
insert into public.adopter (id, adoptertype, address_id) values  ('61ac53c8-cbaf-4b02-a501-e9c48798b174', 'STANDARD', '972e6e44-b0b0-497a-81d0-c5190c5b4888');

insert into public.address (id, city, streetname, streetnumber, version) values  ('972e6e44-b0b0-497a-81d0-c5190c5b4889', 'Lodz', 'Piotr', '123', 0);
insert into public.account (id, role, active, login, password, version) values  ('61ac53c8-cbaf-4b02-a501-e9c48798b175', 'ADOPTER', true, 'Adopter2', '123', 0);
insert into public.person (id, email, firstname, lastname, personalid) values  ('61ac53c8-cbaf-4b02-a501-e9c48798b175','adopte2@wp.pl', 'Jena', 'Smith', '12345679910');
insert into public.adopter (id, adoptertype, address_id) values  ('61ac53c8-cbaf-4b02-a501-e9c48798b175', 'STANDARD', '972e6e44-b0b0-497a-81d0-c5190c5b4889');

insert into public.address (id, city, streetname, streetnumber, version) values  ('972e6e44-b0b0-497a-81d0-c5190c5b4887', 'Wroclaw', 'Odrzanska', '123', 0);
insert into public.account (id, role, active, login, password, version) values  ('61ac53c8-cbaf-4b02-a501-e9c48798b176', 'ADOPTER', true, 'Adopter3', '123', 0);
insert into public.person (id, email, firstname, lastname, personalid) values  ('61ac53c8-cbaf-4b02-a501-e9c48798b176','adopter3@wp.pl', 'Kati', 'Black', '12345679510');
insert into public.adopter (id, adoptertype, address_id) values  ('61ac53c8-cbaf-4b02-a501-e9c48798b176', 'PREVIOUS_ADOPTER', '972e6e44-b0b0-497a-81d0-c5190c5b4887');

insert into public.address (id, city, streetname, streetnumber, version) values  ('972e6e44-b0b0-497a-81d0-c5190c5b4881', 'Wroclaw', 'Kusocinskiego', '14', 0);
insert into public.account (id, role, active, login, password, version) values  ('61ac53c8-cbaf-4b02-a501-e9c48798b171', 'ADOPTER', true, 'Adopter4', '14', 0);
insert into public.person (id, email, firstname, lastname, personalid) values  ('61ac53c8-cbaf-4b02-a501-e9c48798b171','adopterr@wp.pl', 'Leny', 'Manfjvn', '12345679511');
insert into public.adopter (id, adoptertype, address_id) values  ('61ac53c8-cbaf-4b02-a501-e9c48798b171', 'PREVIOUS_ADOPTER', '972e6e44-b0b0-497a-81d0-c5190c5b4881');

insert into public.address (id, city, streetname, streetnumber, version) values  ('972e6e44-b0b0-497a-81d0-c5190c5b4883', 'Wroclaw', 'Andrzejewska', '114', 0);
insert into public.account (id, role, active, login, password, version) values  ('61ac53c8-cbaf-4b02-a501-e9c48798b173', 'ADOPTER', true, 'Adopte54', '114', 0);
insert into public.person (id, email, firstname, lastname, personalid) values  ('61ac53c8-cbaf-4b02-a501-e9c48798b173','adopter54@wp.pl', 'Jan', 'Kowalski', '12335679511');
insert into public.adopter (id, adoptertype, address_id) values  ('61ac53c8-cbaf-4b02-a501-e9c48798b173', 'BLACKLISTED', '972e6e44-b0b0-497a-81d0-c5190c5b4883');

insert into public.account (id, role, active, login, password, version) values  ('61ac53c8-cbaf-4b02-a501-d9c48798b175', 'EMPLOYEE', true, 'Employee1', 'b9c788f973a89a414bda85516b5daafb7c9ac55fd9b1eb311135f71f777686dd', 0);
insert into public.person (id, email, firstname, lastname, personalid) values  ('61ac53c8-cbaf-4b02-a501-d9c48798b175','employee1@wp.pl', 'Emp', 'Loyee', '12345678911');

insert into public.account (id, role, active, login, password, version) values  ('d7b4688f-4fdc-496d-a651-08b904a29a75', 'ADMIN', true, 'admin1', '25f43b1486ad95a1398e3eeb3d83bc4010015fcc9bedb35b432e00298d5021f7', 1);
insert into public.person (id, email, firstname, lastname, personalid) values  ('d7b4688f-4fdc-496d-a651-08b904a29a75', 'admin1@wp.pl', 'Adek', 'Kowal', '12345671993');

insert into public.adoption (id, endadoptiontime, finaladoptioncost, startadoptiontime, version, adopter_id, animal_id) values  ('74fed3b7-d3f4-492b-814f-11dfaf358fc6', '2024-09-20', 20, '2024-09-17', 0, '61ac53c8-cbaf-4b02-a501-e9c48798b174', 'dbb114f6-c14c-4bbf-afe3-e6998a300d10');
insert into public.adoption (id, endadoptiontime, finaladoptioncost, startadoptiontime, version, adopter_id, animal_id) values  ('74fed3b7-d3f4-492b-814f-11dfaf358fc8', null, 20.12, '2024-09-25', 0, '61ac53c8-cbaf-4b02-a501-e9c48798b174', 'dbb114f6-c14c-4bbf-afe3-e6998a300d15');
insert into public.adoption (id, endadoptiontime, finaladoptioncost, startadoptiontime, version, adopter_id, animal_id) values  ('74fed3b7-d3f4-492b-814f-11dfaf358fc4', null, 40, '2024-09-28', 0, '61ac53c8-cbaf-4b02-a501-e9c48798b175', '666c005c-0d27-4187-a682-567e390a0639');
insert into public.adoption (id, endadoptiontime, finaladoptioncost, startadoptiontime, version, adopter_id, animal_id) values  ('74fed3b7-d3f4-492b-814f-11dfaf358fc3', '2023-11-05', 40, '2023-10-29', 0, '61ac53c8-cbaf-4b02-a501-e9c48798b171', 'dbb114f7-c14c-4bbf-afe3-e6998a300d16');

