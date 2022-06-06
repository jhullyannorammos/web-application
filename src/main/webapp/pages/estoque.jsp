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

<script type="text/javascript">
	$(document).ready(function() {
		$('#table-stock').DataTable( {
	        "lengthMenu": [[10], [10]]
	    });	
	});	
	function update(id){
		
	}
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
					<li class="active"><i class="fa fa-edit"></i> estoque</li>
				</ol>


				<button title="Novo" style="margin-left:90%;" onclick="window.location = '?pg=250';" 
					type="button" class="btn btn-primary btn-sm">
					<i class="fa fa-plus"> Novo</i>
				</button>
				<br />
				<br />
				<table id="table-stock" class="display">
					<thead>
						<tr>
							<th>Marca</th>
							<th>Modelo</th>
							<th>unidades</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<stock:forEach var="stck" items="${stockdao.findAll()}">
							<tr>
								<td>${stck.marca}</td>
								<td>${stck.modelo}</td>
								<td>${stck.quantidade}</td>
								<td>
									<button onclick="update(${stck.id});" title="Editar" type="button" class="btn btn-info">
										<i class="fa fa-edit"></i>
									</button>
									<button title="Remover" type="button" class="btn btn-danger">
										<i class="fa fa-trash-o"></i>
									</button>
								</td>
							</tr>
						</stock:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
