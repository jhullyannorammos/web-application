<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!--JQuery-->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap core CSS -->
<link href="../sb-admin/css/bootstrap.css" rel="stylesheet">
<!-- Add custom CSS here -->
<link href="../sb-admin/css/sb-admin.css" rel="stylesheet">
<link rel="stylesheet" href="../sb-admin/font-awesome/css/font-awesome.min.css">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>

<!-- Bootstrap core CSS -->
<link href="sb-admin/css/bootstrap.css" rel="stylesheet">
<!-- Add custom CSS here -->
<link href="sb-admin/css/sb-admin.css" rel="stylesheet">
<link rel="stylesheet" href="sb-admin/font-awesome/css/font-awesome.min.css">

<!-- DataTable.net -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>


<script>
	$(document).ready(function() {
		
		
		$('#table-produto').DataTable( {
	        "lengthMenu": [[7], [7]]
	    });
		
		
		$('#busca-produto').click({
			$.ajax({
				method : 'POST',
				url : 'Produtos?actionServlet=400',
				error : function(data) {
					$("#dialog").addClass('alert alert-danger alert-dismissable');
					$("#dialog").dialog("Erro no cadastro :" + data);
					$('#dsc').val("");
					$('#vlr').val("");
				},
				sucess : function(data) {
				    $("#").append();
				}
					
				
			});
		});
		
		$("#save-pdto").click(function() {
			var pdt = $('#pdt').val();
			var mdl = $('#mdl').val();
			var vlr = $('#vlr').val();
			$.ajax({
				method : 'POST',
				url : 'Produtos?actionServlet=100',
				data : {
					'pdt' : pdt,
					'idm' : mdl,
					'vlr' : vlr
				},
				error : function(data) {
					$("#dialog").addClass('alert alert-danger alert-dismissable');
					$("#dialog").dialog("Erro no cadastro :" + data);
					$('#dsc').val("");
					$('#vlr').val("");
				},
				success : function(data) {
					$.ajax({
						method : 'POST',
						url : 'Estoques?actionServlet=100',
						data : {
							'idm' : mdl
						},
					    sucess: function(data){
					    	$("#dialog").addClass('alert alert-success alert-dismissable');
							$("#dialog").text("Cadastro :" + data + " adicionado com sucesso");
							$('#dsc').val("");
							$('#vlr').val("");
					    },
						error : function(data) {
							$("#dialog").addClass('alert alert-danger alert-dismissable');
							$("#dialog").dialog("Erro :" + data  + " não foi criado no estoque");
							$('#dsc').val("");
							$('#vlr').val("");
						}
					});
				}
			});
		});
		$("#vlr").mask("00000");
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#table-produto').DataTable( {
	        "lengthMenu": [[7], [7]]
	    });
	});
</script>
</head>
<body>

	<div id="page-wrapper">
		<div class="row">

			<div id="wrapper">
				<h1>
					 <small></small>
				</h1>
				
				<ol class="breadcrumb">
					<li><a href=""><i class="fa fa-user"></i> Pesquisa</a></li>
					<li class="active"><i class="fa fa-edit"></i> produtos</li>
				</ol>
				<button title="Novo" style="margin-left:90%;"
					onclick="window.location = '?pg=235';" 
					type="button"
					class="btn btn-primary btn-sm">
					<i class="fa fa-plus"> Novo</i>
				</button>
				<br />
				<br />
				<table id="table-produto" class="display">
					<thead>
						<tr>
							<th>Produto</th>
							<th>Fornecedor</th>
							<th>Modelo</th>
							<th>Valor</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<produtos:forEach var="produto" items="${produtodao.findAll()}">
							<tr>
								<td>${produto.produto}</td>
								<td>${produto.modelo.fornecedor.fornecedor}</td>
								<td>${produto.modelo.modelo}</td>
								<td>${produto.valor}</td>
								<td>
									<button onclick="update(${produto.id});" title="Editar" type="button" class="btn btn-default"> <i class="fa fa-edit"></i> </button>
									<button title="Remover" type="button" class="btn btn-danger">
										<i class="fa fa-trash-o"></i>
									</button>
								</td>
							</tr>
						</produtos:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>

