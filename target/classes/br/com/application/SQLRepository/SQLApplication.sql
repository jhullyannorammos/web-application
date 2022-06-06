/* consulta: produtos e seus modelos  */
/* ================================================= */
select fornecedor, abrangencia, modelo, lancamento
       from application.fornecedores 
       join application.modelos 
       on modelos.fornecedor_id = fornecedores.codigo;
         
/* consulta: produto, modelo e fornecedor  */
/* ================================================= */
select produto, fornecedor, modelo, valor, lancamento
       from application.fornecedores 
       join application.produtos
       join application.modelos 
       on modelos.fornecedor_id = fornecedores.codigo
       and produtos.modelo_id = modelos.codigo;
/* consulta: dados das mercadorias  */
/* ================================================= */
select * from application.fornecedores 
         join application.produtos 
         join application.modelos 
         join application.mercadorias 
         on modelos.fornecedor_id = fornecedores.codigo
         and produtos.modelo_id = modelos.codigo
         and mercadorias.produto_id = produtos.codigo;
/* consulta: dados das mercadorias por id  */
/* ================================================= */
select * from application.fornecedores 
         join application.produtos 
         join application.modelos 
         join application.mercadorias 
         on modelos.fornecedor_id = fornecedores.codigo
         and produtos.modelo_id = modelos.codigo
         and mercadorias.produto_id = produtos.codigo
         where mercadorias.codigo = 1;
         
select fornecedor, modelo
         from application.fornecedores
         join application.modelos
         on fornecedores.codigo = modelos.fornecedor_id;

/* consulta: fornecedores  */
/* ================================================= */
select codigo, fornecedor from application.fornecedores;


/* consulta: modelos de fornecedor  */
/* ================================================= */
select modelos.codigo, fornecedor, modelo, lancamento
         from application.modelos
	     join application.fornecedores
         on modelos.fornecedor_id = fornecedores.codigo;

/* consulta: funcionarios de departamento  */
/* ================================================= */
select matricula, email, cargo, departamento, hierarquia, sigla 
		from application.colaboradores
         join application.departamentos 
         on colaboradores.departamento_id = departamentos.codigo;

/* consulta: fornecedor: modelo:produto  */
/* ================================================= */
select fornecedor, modelo, produto, valor
       from application.produtos
       join application.modelos
       join application.fornecedores
       on modelos.fornecedor_id = fornecedores.codigo
       on modelos.codigo = produtos.modelo_id;
/* consulta: fornecedor: modelo:produto  */
/* ================================================= */
select produtos.codigo, modelo, produto 
         from application.produtos
         join application.modelos
         on modelos.codigo = produtos.modelo_id;
/* ================================================= */
select codigo, modelo from application.modelos;

/* consulta: mercadorias by id  */
/* ================================================= */
select produtos.codigo, serialnumber, produto, modelo, fornecedor
       from application.mercadorias 
       join application.produtos
       join application.modelos
       join application.fornecedores
       on produtos.codigo = mercadorias.produto_id
       and modelos.codigo = produtos.modelo_id
       and fornecedores.codigo = modelos.fornecedor_id
       where produto_id = 1;
       
/* consulta: mercadorias  */
/* ================================================= */
select count(*) from application.mercadorias;
select produto, modelo, valor, fornecedor /* patrimonio, produto, nome_modelo, fornecedor */
       from application.mercadorias 
       join application.produtos
       join application.modelos
       join application.fornecedores
       on produtos.codigo = mercadorias.produto_id
       and modelos.codigo = produtos.modelo_id
       and fornecedores.codigo = modelos.fornecedor_id;

select departamento, patrimonio, serialnumber, produto, fornecedor, modelo 
       from application.fornecedores 
       join application.produtos
       join application.modelos 
       join application.mercadorias
       on modelos.fornecedor_id = fornecedores.codigo
       and mercadorias.produto_id = modelos.codigo
       and produtos.modelo_id = modelos.codigo;
/* consulta: quantidade de mercadoria em estoque  */
/* ================================================= */
select * from application.estoque;
select count(*) from application.estoque where modelo = 'optiplex 790';
select count(*) from application.estoque where modelo like 'optiplex%';
select produto, fornecedor, estoque.modelo, quantidade
       from application.estoque
       join application.mercadorias
       join application.produtos
       join application.modelos
       join application.fornecedores
       on estoque.codigo = mercadorias.estoque_id
       and produtos.codigo = mercadorias.produto_id
       and produtos.modelo_id = modelos.codigo
       and modelos.fornecedor_id = fornecedores.codigo
       where estocada = TRUE;
select produto, fornecedor, estoque.modelo, quantidade
       from application.estoque
       join application.mercadorias
       join application.produtos
       join application.modelos
       join application.fornecedores
       on estoque.codigo = mercadorias.estoque_id
       and produtos.codigo = mercadorias.produto_id
       and produtos.modelo_id = modelos.codigo
       and modelos.fornecedor_id = fornecedores.codigo
       where estocada = FALSE;
/* consulta: sobre as movimentações  */
/* ================================================= */
select mercadorias.codigo, periodo, movimentacao, estocada, serialnumber, produto, fornecedor, modelo
       from application.fornecedores 
       join application.modelos 
       join application.produtos
       join application.mercadorias
       join application.movimentacao 
       on mercadorias.produto_id = produtos.codigo
       and produtos.modelo_id = modelos.codigo
       and modelos.fornecedor_id = fornecedores.codigo
       and movimentacao.movimentacao = 'SAIDA'
       order by movimentacao.periodo;
/* ================================================================= */
select sistema, number_key, patrimonio 
	  from application.patrimonios
      join application.licenses
      on patrimonios.codigo = licenses.patrimonio_id;
/* ================================================================= */
select serialnumber, OS, estocada
      from application.mercadorias as mrcd
      join application.licenses as lcas
      join application.patrimonios as ptmn
      join application.estoque as stck
      on stck.codigo = mrcd.estoque_id
      where ptmn.sl_id = lcas.patrimonio_id and ptmn.mercadoria_id = mrcd.patrimonio_id;
/* ================================================================= */
select mov.movimentacao, mov.periodo, 
	   md.serialnumber, md.estocada,
       fbct.fornecedor, 
       prdt.produto, 
       mdls.modelo
       from application.movimentacao as mov
       join application.movimentacao_mercadorias as mm
       join application.mercadorias as md
       /*join application.estoque as stck*/
       join application.produtos as prdt
       join application.modelos as mdls
       join application.fornecedores as fbct
       on mov.codigo = mm.movimentacao_codigo and md.codigo = mm.mercadorias_codigo
       and md.produto_id = prdt.codigo
       /*and stck.codigo = md.estoque_id*/
       and prdt.modelo_id = mdls.codigo
       and mdls.fornecedor_id = fbct.codigo;
/* consulta simples */
/* ================================================= */
select * from application.usuarios;
insert into application.usuarios() values();


select * from application.departamentos;
select * from application.colaboradores;
select * from application.fornecedores;
select * from application.modelos;
select * from application.cidades;
select * from application.estoque;
select * from application.produtos;
select * from application.mercadorias;
select * from application.patrimonios;
select * from application.licenses;
select * from application.movimentacao;  
/* ================================================ */
create schema application;
drop schema application;