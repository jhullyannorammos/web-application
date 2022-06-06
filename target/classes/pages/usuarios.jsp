<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- JQuery.ajax  -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="src/js/usuario.js"></script>

<!-- Bootstrap core CSS -->
<link href="sb-admin/css/bootstrap.css" rel="stylesheet">
<!-- Add custom CSS here -->
<link href="sb-admin/css/sb-admin.css" rel="stylesheet">
<link rel="stylesheet"
	href="sb-admin/font-awesome/css/font-awesome.min.css">

<!-- DataTable.net -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#table-users').DataTable( {
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
					<!-- Administrador de Redes --> <small><!--Pesquisa de usuarios--></small>
				</h1>
				<ol class="breadcrumb">
					<li><a href=""><i class="fa fa-user"></i> Administrador</a></li>
					<li class="active"><i class="fa fa-edit"></i> Usuarios</li>
				</ol>

				<button title="Novo"
					onclick="window.location = 'application.jsp?pg=206&op=222';"
					type="button" class="btn btn-default">Novo</button>
				<br /> <br />
				<table id="table-users" class="display">
					<thead>
						<tr>
							<th>Username</th>
							<th>Email</th>
							<th>Status</th>
							<th>Perfil</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<p:forEach items="${udao.findAll()}" var="usuario">
							<tr>
								<td>${usuario.username}</td>
								<td>${usuario.email}</td>
								<td>${usuario.status}</td>
								<td>${usuario.profile}"</td>
								<td>
									<button title="Editar" type="button" class="btn btn-default">
										<i class="fa fa-edit"></i>
									</button>
									<button title="Remover" type="button" class="btn btn-danger">
										<i class="fa fa-trash-o"></i>
									</button>
								</td>
							</tr>
						</p:forEach>
					</tbody>
				</table>

			</div>
			<!--  
                <div id="wrapper">
                    <h1>Administrador de Redes <small>Cadastro de usuarios</small></h1>
                    <ol class="breadcrumb">
                        <li><a href=""><i class="fa fa-user"></i> Administrador</a></li>
                        <li class="active"><i class="fa fa-edit"></i> Usuarios</li>
                    </ol>

                    <button title="Novo" type="button" class="btn btn-default">Enviar</button>
                    
                    <button title="teste" type="button" class="btn btn-default" id="teste">teste</button>
                    
                    <div id="message"></div>
                </div>
                -->
		</div>
	</div>
</body>
</html>
