alter table restaurante add ativo tinyint(1) not null;
update restaurante set ativo = true where id in (1,2,3,4,5,6);