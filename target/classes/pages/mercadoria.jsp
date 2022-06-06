<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<!--JQuery-->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap core CSS -->
<link href="../sb-admin/css/bootstrap.css" rel="stylesheet">
<!-- Add custom CSS here -->
<link href="../sb-admin/css/sb-admin.css" rel="stylesheet">
<link rel="stylesheet" href="../sb-admin/font-awesome/css/font-awesome.min.css">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>

<script>
	$(document).ready(function() {
		$("#save-item").click(function() {

			var srl = $('#srl').val();
			var typ = $('#typ').val();
			var idp = $('#idp').val();
			var ptm = $('#ptm').val();
			var idpt = $('#idpt').val();
			var stk = $('#stk').val();

			$.ajax({
				method : 'POST',
				url : 'Mercadorias?actionServlet=100',
				data : {
					'srl' : srl,
					'typ' : typ,
					'idpt' : idpt,
					'idp' : idp,
					'ptm' : ptm,
					'stk' : stk
				},
				error : function(data) {
					$("#dialog").addClass('alert alert-danger alert-dismissable');
					$("#dialog").dialog("Erro: não foi possivel cadastrar mercadoria " + data);
					$('#srl').val("");
					$('#typ').val("");
					$('#tpm').val("");
				},
				success : function(data) {
					$.ajax({
						method : 'POST',
						url : 'Estoques?actionServlet=300',
						data : {'idm' : data},
						error : function(data) {
							$("#dialog").addClass('alert alert-danger alert-dismissable');
							$("#dialog").dialog("Erro: não foi possivel adicionar mercadoria " + data);
							$('#srl').val("");
							$('#typ').val("");
							$('#tpm').val("");
						},
						sucess: function(data){
							$("#dialog").addClass('alert alert-success alert-dismissable');
							$("#dialog").text("Mensagem : mercadoria " + data + " adicionada no estoque");
							$('#srl').val("");
							$('#typ').val("");
							$('#tpm').val("");
						}
					});
					
				}
			});
			
		});
		$("#srl").mask("AAA000-S0S");
		$("#typ").mask("9999-999");
		$("#ptm").mask("99999-9999");
	});
</script>

</head>
<body>
<div id="page-wrapper">
    <div class="row">
          <div id="wrapper">
                <h1>
					Administração de estoque <small></small>
				</h1>

				<ol class="breadcrumb">
					<li><a href=""><i class="fa fa-user"></i> Cadastro</a></li>
					<li class="active"><i class="fa fa-edit"></i> Mercadorias</li>
				</ol>
				<div class="col-lg-4">
				
				<form>
				<div id="dialog"></div>
				<h4>Serial number</h4>
				<input id="srl" class="form-control" name="srl" />
				
				<h4>Type</h4>
				<input id="typ" class="form-control" name="typ" />
				
				<h4>Patrimônio</h4>
				<input id="ptm" class="form-control" name="ptm" />
				
				<h4>Estocada</h4>
				<select class="form-control" id="stk" >
				        <option>SIM</option>
				        <option>NAO</option>
				</select>
				
				<h4>Departamento</h4>
				<select class="form-control" id="idpt" >
				    <produto:forEach var="departamentos" items="${dpdao.findAll()}">
				        <option value="${departamentos.id}">${departamentos.sigla} - ${departamentos.hierarquia} - ${departamentos.departamento}</option>
				    </produto:forEach>
				</select>
				
				<h4>Produto</h4>
				<select class="form-control" id="idp" >
				    <produto:forEach var="produtos" items="${ppdao.findByOrdenation('produto')}">
				        <option value="${produtos.id}">${produtos.produto} - ${produtos.modelo.fornecedor.fornecedor} - ${produtos.modelo.modelo}</option>
				    </produto:forEach>
				</select>
				<br />
				<button id="save-item" type="button" class="btn btn-success">Cadastrar</button>
				</form>
				</div>
          </div>
    </div>
</div>
</body>
</html>
