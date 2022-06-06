<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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

<script>
	$(document).ready(function() {
		
		$('#list_fncdrs').change(function(){
			$.ajax({
				method : 'GET',
				url: "Fornecedores?actionServlet=400",
				dataType: "json",
				success: function (fornecedores) {

				}
			});
		});
		
		$("#save-mod").click(function() {

			var ano = $('#ano').val();
			var mdl = $('#mdl').val();
			var list_fncdrs = $('#list_fncdrs').val();
            
			$.ajax({
				method : 'POST',
				url : 'Modelos?actionServlet=100',
				data : {
					'ano' : ano,
					'mdl' : mdl,
					'list_fncdrs' : list_fncdrs
				},
				error : function(data) {
					$("#dialog").addClass('alert alert-warning alert-dismissable');
					$("#dialog").dialog("Erro :" + data);
				},
				success : function(data) {
					$("#dialog").addClass('alert alert-success alert-dismissable');
					$("#dialog").text("Mensagem :" + data + " cadastrado com sucesso.");
				}
			});
			$('#ano').val("");
			$('#mdl').val("");
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
					<li><a href=""><i class="fa fa-user"></i> Cadastro</a></li>
					<li class="active"><i class="fa fa-edit"></i> modelos</li>
				</ol>

				<form>

					<div id="dialog"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button></div>
					<div class="col-lg-3">
						

                        
						<h4>Marca do modelo</h4>
						<select id="list_fncdrs" class="form-control">
						</select>

						<h4>Nome do modelo</h4>
						<input id="mdl" class="form-control" name="mdl" />
						
						<h4>Ano de lançamento</h4>
						<input id="ano" class="form-control" name="ano" />
                        
                        <br /> <br />
						<button id="save-mod" type="button" class="btn btn-success">Cadastrar</button>
						
						<button title="Novo" style="left, margin-left:15%;"
					         onclick="window.location = '?pg=252';" 
					         type="button"
					         class="btn btn-primary btn-sm">
					         <i class="fa fa-plus"> Adicionar marca</i>
				        </button>
					</div>

				</form>

			</div>
		</div>
	</div>
</body>

</html>