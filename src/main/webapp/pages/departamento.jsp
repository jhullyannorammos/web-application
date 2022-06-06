<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Bootstrap core CSS -->
<link href="../sb-admin/css/bootstrap.css" rel="stylesheet">
<!-- Add custom CSS here -->
<link href="../sb-admin/css/sb-admin.css" rel="stylesheet">
<link rel="stylesheet" href="../sb-admin/font-awesome/css/font-awesome.min.css">

<!-- DataTable.net -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#table-departaments').DataTable( {
			"ajax": {
				"url":"Departamentos?actionServlet=400",
				"type": "POST"
			},
	        "lengthMenu": [[8], [8]]
	    });
	});

	function adicionar(){

		$('#table-departaments tbody').append(
           "<tr>" + 
           "<td>" + "<input id='nomeCompleto' type='text'/>" + "</td>" +
           "<td>" + "<input id='cpf' type='text'/>" + "</td>" +
           "<td>" + "<input id='cnh' type='text'/>" + "</td>" +
           "<td>" + "<img src='src/update.png' class='btnUpdate'>" +
                    "<img src='src/remove.png' class='btnRemove'>" +
           "</td>"
           "</tr>"
		);

		$(".btnUpdate").bind("click", Update);      
        $(".btnRemove").bind("click", Remove);
	}

	function Salvar(){
	    var par = $(this).parent().parent(); //tr
	    var tdNome = par.children("td:nth-child(1)");
	    var tdTelefone = par.children("td:nth-child(2)");
	    var tdEmail = par.children("td:nth-child(3)");
	    var tdBotoes = par.children("td:nth-child(4)");
	 
	    tdNome.html(tdNome.children("input[type=text]").val());
	    tdTelefone.html(tdTelefone.children("input[type=text]").val());
	    tdEmail.html(tdEmail.children("input[type=text]").val());
	    tdBotoes.html("<img src='images/delete.png'
	           class='btnExcluir'/><img src='images/pencil.png' class='btnEditar'/>");
	 
	    $(".btnEditar").bind("click", Editar);
	    $(".btnExcluir").bind("click", Excluir);
	};


	function Editar(){
		 var par = $(this).parent().parent(); //tr
		 var tdNome = par.children("td:nth-child(1)");
		 var tdTelefone = par.children("td:nth-child(2)");
		 var tdEmail = par.children("td:nth-child(3)");
		 var tdBotoes = par.children("td:nth-child(4)");
		 
		 tdNome.html("<input type='text' id='txtNome' value='"+tdNome.html()+"'/>");
		 tdTelefone.html("<input type='text'
		                  id='txtTelefone' value='"+tdTelefone.html()+"'/>");
		 tdEmail.html("<input type='text' id='txtEmail' value='"+tdEmail.html()+"'/>");
		 tdBotoes.html("<img src='images/disk.png' class='btnSalvar'/>");
		 
		 $(".btnSalvar").bind("click", Salvar);
		 $(".btnEditar").bind("click", Editar);
		 $(".btnExcluir").bind("click", Excluir);
		};


		function Excluir(){
		    var par = $(this).parent().parent(); //tr
		    par.remove();
		};


		$(".btnEditar").bind("click", Editar);
	    $(".btnExcluir").bind("click", Excluir);
	    $("#btnAdicionar").bind("click", Adicionar);
	
</script>

</head>

<body>
	<div id="page-wrapper">
		<div class="row">

			<div id="wrapper">

				<h2>
					<small></small>
				</h2>
				<ol class="breadcrumb">
					<li><a href=""><i class="fa fa-user"></i> Pesquisa</a></li>
					<li class="active"><i class="fa fa-edit"></i> Departamentos</li>
				</ol>
                <!-- 
				<button title="Novo" style="margin-left:90%;"
					onclick="window.location = '?pg=201';" 
					type="button"
					class="btn btn-primary btn-sm">
					<i class="fa fa-plus"> Novo</i>
				</button>
				-->


				<table id="table-departaments" class="display">
					<thead>
						<tr>
							<th>Nível</th>
							<th>Departamento</th>
							<th>Sigla</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>

		</div>
	</div>
</body>

</html>