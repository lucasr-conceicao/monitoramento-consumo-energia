INSERT INTO tb_tipo_casa_cnsm_enrg VALUES ('Casa Unifamiliar', 1);
INSERT INTO tb_tipo_casa_cnsm_enrg VALUES ('Apartamento', 2);

INSERT INTO tb_endereco_cnsm_enrg VALUES (2265,'370c5fc0-020b-4219-bc56-b1458400d71e', 'Jardim Paulista', '02569451', 'São Paulo', 'SP', 'Brasil', 'Avenida Paulista');
INSERT INTO tb_endereco_cnsm_enrg VALUES (26,'370c5fc0-020b-4219-bc56-b1458400d71f', 'Limao', '0250468', 'São Paulo', 'SP', 'Brasil', 'AV. Nossa senhora do Ó');

INSERT INTO tb_pessoa_cnsm_enrg VALUES (25, '1998-01-24 22:00:00', '52595066-2821-492f-a600-33d68ae430b5', '65874589745', 'lucas@hotmail.com', 'Masculino', 'Lucas Rocha');
INSERT INTO tb_pessoa_cnsm_enrg VALUES (51, '1972-08-12 22:00:00', 'c2366833-0aaf-4d29-ab35-5e729f6cbd13', '36547854125', 'Monica@hotmail.com', 'Feminino', 'Monica Rocha');

INSERT INTO tb_casa_cnsm_enrg VALUES ('004a324e-0169-4260-b3c5-8cc04670ddbD', '370c5fc0-020b-4219-bc56-b1458400d71e', 'c2366833-0aaf-4d29-ab35-5e729f6cbd13', 1);

INSERT INTO tb_eletrodomestico_cnsm_enrg VALUES ('75.00', '004a324e-0169-4260-b3c5-8cc04670ddbD', 'eb982177-dc0a-4781-914b-806a170c0061', 'Micro-ondas');
INSERT INTO tb_eletrodomestico_cnsm_enrg VALUES ('50.00', '004a324e-0169-4260-b3c5-8cc04670ddbD', 'fc98e05c-2948-45e1-a43f-cc1bbd52f8aa', 'TV');
INSERT INTO tb_eletrodomestico_cnsm_enrg VALUES ('350.00', '004a324e-0169-4260-b3c5-8cc04670ddbD', '698dd752-d699-41c9-9f04-9ed3f0bf8d80', 'Chuveiro');

INSERT INTO tb_parentesco_cnsm_enrg VALUES ('52595066-2821-492f-a600-33d68ae430b5', 'c2366833-0aaf-4d29-ab35-5e729f6cbd13', '48be8a94-7070-4e75-8ff4-b901bb2a22b2', 'Filho');
INSERT INTO tb_parentesco_cnsm_enrg VALUES ('c2366833-0aaf-4d29-ab35-5e729f6cbd13', '52595066-2821-492f-a600-33d68ae430b5', 'c7fb91c2-3d03-46d6-a622-88af152bc80d', 'Mãe');